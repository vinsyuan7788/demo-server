package com.demo.server.application.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.base.common.exception.bean.BusinessException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vince Yuan
 * @date 09/06/2021
 */
@Slf4j
public class BaiduMapUtils {
    /**
     *  地理编码
     */
    private static final String BAIDU_GEO_CODING_URL = "https://api.map.baidu.com/geocoding/v3/";
    private static final String BAIDU_GEO_CODING_URL_RESULT_LOCATION = "location";
    private static final String BAIDU_GEO_CODING_URL_RESULT_LOCATION_LONGITUDE = "lng";
    private static final String BAIDU_GEO_CODING_URL_RESULT_LOCATION_LATITUDE = "lat";

    /**
     *  批量算路（驾车）
     */
    private static final String BAIDU_ROUTE_MATRIX_DRIVING_URL = "https://api.map.baidu.com/routematrix/v2/driving";
    private static final String BAIDU_ROUTE_MATRIX_DRIVING_URL_RESULT_DISTANCE = "distance";
    private static final String BAIDU_ROUTE_MATRIX_DRIVING_URL_RESULT_DISTANCE_VALUE = "value";

    /**
     *  坐标转换
     */
    private static final String BAIDU_GEO_CONV_URL = "https://api.map.baidu.com/geoconv/v1/";
    private static final String BAIDU_GEO_CONV_URL_RESULT_LONGITUDE = "x";
    private static final String BAIDU_GEO_CONV_URL_RESULT_LATITUDE = "y";

    /**
     *  通用响应字段
     */
    private static final String BAIDU_URL_RESPONSE_STATUS = "status";
    private static final String BAIDU_URL_RESPONSE_STATUS_OK = "0";
    private static final String BAIDU_URL_RESPONSE_STATUS_SERVER_INTERNAL_ERROR = "1";
    private static final String BAIDU_URL_RESPONSE_RESULT = "result";

    public static final String BAIDU_URL_RESPONSE_MESSAGE_SERVER_INTERNAL_ERROR = "百度地图服务器内部错误";

    private static final String ENG_COMMA = ",";
    private static final String ENG_VERTICAL_LINE = "|";
    private static final String ENG_SEMI_COLON = ";";

    private BaiduMapUtils() { }

    /**
     *  获取地理信息
     *
     * @param ak 申请 key
     * @param address 需要查询的地址
     * @param restTemplate restTemplate
     * @return
     */
    public static GeoCoordinateInfo getGeoCoordinateInfo(String ak, String address, RestTemplate restTemplate) {
        String urlOfGeoCoding = getUrlOfGeoCoding(ak, address, SupportedCoordinateTypeEnum.BD09LL, OutputFormatEnum.JSON);
        String response = restTemplate.getForObject(urlOfGeoCoding, String.class);
        return parseResponseOfGeoCoding(response, OutputFormatEnum.JSON);
    }

    /**
     *  获取始发地和目的地之间的距离（米）
     *
     * @param ak 申请 key
     * @param origins 始发地坐标
     * @param destinations 目的地坐标
     * @param restTemplate restTemplate
     * @return
     */
    public static List<GeoCoordinateInfo> getDistanceInbetweeen(String ak, List<GeoCoordinateInfo> origins, List<GeoCoordinateInfo> destinations, RestTemplate restTemplate) {
        String urlOfRouteMatrixOfDriving = getUrlOfRouteMatrixOfDriving(ak, origins, destinations, SupportedCoordinateTypeEnum.BD09LL, OutputFormatEnum.JSON);
        String response = restTemplate.getForObject(urlOfRouteMatrixOfDriving, String.class);
        return parseResponseOfRouteMatrixOfDriving(response, OutputFormatEnum.JSON);
    }

    /**
     *  获取百度坐标系的经纬度
     *
     * @param ak 申请 key
     * @param coordinates 其它坐标系的经纬度
     * @param originalCoordinateType 需要转为百度坐标系的其它坐标系
     * @param restTemplate restTemplate
     * @return
     */
    public static List<GeoCoordinateInfo> getBaiduCoordinate(String ak, List<GeoCoordinateInfo> coordinates, SupportedCoordinateTypeEnum originalCoordinateType, RestTemplate restTemplate) {
        String urlOfGeoConv = getUrlOfGeoConv(ak, coordinates, originalCoordinateType, SupportedCoordinateTypeEnum.BD09LL, OutputFormatEnum.JSON);
        String response = restTemplate.getForObject(urlOfGeoConv, String.class);
        return parseResponseOfGeoConv(response, OutputFormatEnum.JSON);
    }

    /**
     *  获取地理编码 URL
     *
     * @param ak 申请 key
     * @param address 需要查询的地址
     * @param returnCoordinateType 返回的坐标类型
     * @param outputFormatEnum 返回格式
     * @return
     */
    private static String getUrlOfGeoCoding(String ak, String address, SupportedCoordinateTypeEnum returnCoordinateType, OutputFormatEnum outputFormatEnum) {
        if (StringUtils.isBlank(ak) || StringUtils.isBlank(address)) {
            throw new BusinessException("发现有入参为空，无法拼接请求链接");
        }
        String url = BAIDU_GEO_CODING_URL
                // 开发者账号的申请 key
                + "?ak=" + ak
                // 默认 JSON 格式
                + "&output=" + parseOutput(outputFormatEnum)
                // 返回坐标类型
                + "&ret_coordtype=" + returnCoordinateType.getName()
                // 需要查询的地址
                + "&address=" + address;
        log.info("=== getUrlOfGeoCoding | 获取请求链接 | 内容: {} ===", url);
        return url;
    }

    /**
     *  获取批量算路（步行）URL
     *
     * @param ak 申请 key
     * @param origins 源坐标
     * @param destinations 目标坐标
     * @param coordinateTypeEnum 坐标类型
     * @param outputFormatEnum 返回格式
     * @return
     */
    private static String getUrlOfRouteMatrixOfDriving(String ak, List<GeoCoordinateInfo> origins, List<GeoCoordinateInfo> destinations, SupportedCoordinateTypeEnum coordinateTypeEnum, OutputFormatEnum outputFormatEnum) {
        if (StringUtils.isBlank(ak) || CollectionUtils.isEmpty(origins) || CollectionUtils.isEmpty(destinations)) {
            throw new BusinessException("发现有入参为空，无法拼接请求链接");
        }
        String url = BAIDU_ROUTE_MATRIX_DRIVING_URL
                // 开发者账号的申请 key
                + "?ak=" + ak
                // 默认 JSON 格式
                + "&output=" + parseOutput(outputFormatEnum)
                // 坐标类型
                + "&coord_type=" + coordinateTypeEnum.getName()
                // 起点的纬度和经度
                + "&origins=" + parseLatitudesAndLongitudes(origins)
                // 终点的纬度和经度
                + "&destinations=" + parseLatitudesAndLongitudes(destinations);
        log.info("=== getUrlOfRouteMatrixOfDriving | 获取请求链接 | 内容: {} ===", url);
        return url;
    }

    /**
     *  获取坐标转换 URL
     *
     * @param ak 申请 key
     * @param coordinates 坐标
     * @param from 原始坐标系
     * @param to 目标坐标系
     * @param outputFormatEnum 返回格式
     * @return
     */
    private static String getUrlOfGeoConv(String ak, List<GeoCoordinateInfo> coordinates, SupportedCoordinateTypeEnum from, SupportedCoordinateTypeEnum to, OutputFormatEnum outputFormatEnum) {
        if (StringUtils.isBlank(ak) || CollectionUtils.isEmpty(coordinates) || from == null || to == null) {
            throw new BusinessException("发现有入参为空，无法拼接请求链接");
        }
        String url = BAIDU_GEO_CONV_URL
                // 开发者账号的申请 key
                + "?ak=" + ak
                // 默认 JSON 格式
                + "&output=" + parseOutput(outputFormatEnum)
                // 原始坐标系
                + "&from=" + from.getCode()
                // 目标坐标系
                + "&to=" + to.getCode()
                // 待转换的坐标
                + "&coords=" + parseCoordinates(coordinates);
        log.info("=== getUrlOfGeoConv | 获取请求链接 | 内容: {} ===", url);
        return url;
    }

    /**
     *  解析地理编码 URL 的响应
     *
     * @param response 响应
     * @param outputFormatEnum 返回格式
     * @return
     */
    private static GeoCoordinateInfo parseResponseOfGeoCoding(String response, OutputFormatEnum outputFormatEnum) {
        if (outputFormatEnum == null) {
            throw new BusinessException("响应格式为空，无法解析");
        }
        log.info("=== parseResponseOfGeoCoding | 获取响应信息 | 内容: {} ===", response);
        switch (outputFormatEnum) {
            case JSON:
                GeoCoordinateInfo geoCoordinateInfo = new GeoCoordinateInfo();
                JSONObject jsonResponse = JSONObject.parseObject(response);
                checkResponseStatus(jsonResponse);
                JSONObject result = jsonResponse.getJSONObject(BAIDU_URL_RESPONSE_RESULT);
                checkNotNull(result);
                JSONObject location = result.getJSONObject(BAIDU_GEO_CODING_URL_RESULT_LOCATION);
                checkNotNull(location);
                BigDecimal longitude = location.getBigDecimal(BAIDU_GEO_CODING_URL_RESULT_LOCATION_LONGITUDE);
                geoCoordinateInfo.setLongitude(longitude);
                BigDecimal latitude = location.getBigDecimal(BAIDU_GEO_CODING_URL_RESULT_LOCATION_LATITUDE);
                geoCoordinateInfo.setLatitude(latitude);
                return geoCoordinateInfo;

//                GeoCoordinateInfo geoCoordinateInfo = new GeoCoordinateInfo();
//                JSONObject jsonObject = JSONObject.parseObject(response);
//                if (jsonObject.containsKey(BAIDU_URL_RESPONSE_STATUS)) {
//                    String status = jsonObject.getString(BAIDU_URL_RESPONSE_STATUS);
//                    if (BAIDU_URL_RESPONSE_STATUS_OK.equals(status)) {
//                        if (jsonObject.containsKey(BAIDU_URL_RESPONSE_RESULT)) {
//                            JSONObject result = jsonObject.getJSONObject(BAIDU_URL_RESPONSE_RESULT);
//                            if (result.containsKey(BAIDU_GEO_CODING_URL_RESULT_LOCATION)) {
//                                JSONObject location = result.getJSONObject(BAIDU_GEO_CODING_URL_RESULT_LOCATION);
//                                if (location.containsKey(BAIDU_GEO_CODING_URL_RESULT_LOCATION_LONGITUDE)) {
//                                    String longitude = location.getString(BAIDU_GEO_CODING_URL_RESULT_LOCATION_LONGITUDE);
//                                    if (StringUtils.isNotBlank(longitude)) {
//                                        geoCoordinateInfo.setLongitude(new BigDecimal(longitude));
//                                    } else {
//                                        log.info("=== parseResponseOfGeoCoding | 字段{}的值为空 ===", BAIDU_GEO_CODING_URL_RESULT_LOCATION_LONGITUDE);
//                                    }
//                                } else {
//                                    log.error("=== parseResponseOfGeoCoding | 不存在字段{} ===", BAIDU_GEO_CODING_URL_RESULT_LOCATION_LONGITUDE);
//                                    throw new BusinessException("不存在字段" + BAIDU_GEO_CODING_URL_RESULT_LOCATION_LONGITUDE);
//                                }
//                                if (location.containsKey(BAIDU_GEO_CODING_URL_RESULT_LOCATION_LATITUDE)) {
//                                    String latitude = location.getString(BAIDU_GEO_CODING_URL_RESULT_LOCATION_LATITUDE);
//                                    if (StringUtils.isNotBlank(latitude)) {
//                                        geoCoordinateInfo.setLatitude(new BigDecimal(latitude));
//                                    } else {
//                                        log.info("=== parseResponseOfGeoCoding | 字段{}的值为空 ===", BAIDU_GEO_CODING_URL_RESULT_LOCATION_LATITUDE);
//                                    }
//                                } else {
//                                    log.error("=== parseResponseOfGeoCoding | 不存在字段{} ===", BAIDU_GEO_CODING_URL_RESULT_LOCATION_LATITUDE);
//                                    throw new BusinessException("不存在字段" + BAIDU_GEO_CODING_URL_RESULT_LOCATION_LATITUDE);
//                                }
//                            } else {
//                                log.error("=== parseResponseOfGeoCoding | 不存在字段{} ===", BAIDU_GEO_CODING_URL_RESULT_LOCATION);
//                                throw new BusinessException("不存在字段" + BAIDU_GEO_CODING_URL_RESULT_LOCATION);
//                            }
//                        } else {
//                            log.error("=== parseResponseOfGeoCoding | 不存在字段{} ===", BAIDU_URL_RESPONSE_RESULT);
//                            throw new BusinessException("不存在字段" + BAIDU_URL_RESPONSE_RESULT);
//                        }
//                    } else {
//                        log.error("=== parseResponseOfGeoCoding | 响应状态不正常 | 状态: {} ===", status);
//                        throw new BusinessException("响应状态不正常（状态为" + status + "）");
//                    }
//                } else {
//                    log.error("=== parseResponseOfGeoCoding | 不存在字段{} ===", BAIDU_URL_RESPONSE_STATUS);
//                    throw new BusinessException("不存在字段" + BAIDU_URL_RESPONSE_STATUS);
//                }
//                return geoCoordinateInfo;

            case XML:
                throw new BusinessException("该方式暂未实现");
            default:
                throw new BusinessException("未知的响应格式");
        }
    }

    /**
     *  解析批量算路（步行）URL 的响应
     *
     * @param response 响应
     * @param outputFormatEnum 返回格式
     * @return
     */
    private static List<GeoCoordinateInfo> parseResponseOfRouteMatrixOfDriving(String response, OutputFormatEnum outputFormatEnum) {
        if (response == null || outputFormatEnum == null) {
            throw new BusinessException("响应内容或响应格式为空，无法解析");
        }
        log.info("=== parseResponseOfRouteMatrixOfDriving | 获取响应信息 | 内容: {} ===", response);
        switch (outputFormatEnum) {
            case JSON:
                List<GeoCoordinateInfo> geoCoordinateInfoList = new ArrayList<>();
                JSONObject jsonResponse = JSONObject.parseObject(response);
                checkResponseStatus(jsonResponse);
                JSONArray result = jsonResponse.getJSONArray(BAIDU_URL_RESPONSE_RESULT);
                checkNotNull(result);
                for (int i = 0; i < result.size(); i++) {
                    GeoCoordinateInfo geoCoordinateInfo = new GeoCoordinateInfo();
                    JSONObject distanceAndDurationInfo = result.getJSONObject(i);
                    JSONObject distanceInfo = distanceAndDurationInfo.getJSONObject(BAIDU_ROUTE_MATRIX_DRIVING_URL_RESULT_DISTANCE);
                    checkNotNull(distanceInfo);
                    BigDecimal distanceMeters = distanceInfo.getBigDecimal(BAIDU_ROUTE_MATRIX_DRIVING_URL_RESULT_DISTANCE_VALUE);
                    geoCoordinateInfo.setDistance(distanceMeters);
                    geoCoordinateInfoList.add(geoCoordinateInfo);
                }
                return geoCoordinateInfoList;

//                List<GeoCoordinateInfo> geoCoordinateInfoList = new ArrayList<>();
//                JSONObject jsonObject = JSONObject.parseObject(response);
//                if (jsonObject.containsKey(BAIDU_URL_RESPONSE_STATUS)) {
//                    String status = jsonObject.getString(BAIDU_URL_RESPONSE_STATUS);
//                    if (BAIDU_URL_RESPONSE_STATUS_OK.equals(status)) {
//                        if (jsonObject.containsKey(BAIDU_URL_RESPONSE_RESULT)) {
//                            JSONArray result = jsonObject.getJSONArray(BAIDU_URL_RESPONSE_RESULT);
//                            int size = result.size();
//                            if (size > 0) {
//                                for (int i = 0; i < size; i++) {
//                                    GeoCoordinateInfo geoCoordinateInfo = new GeoCoordinateInfo();
//                                    JSONObject distanceAndDurationInfo = result.getJSONObject(i);
//                                    if (distanceAndDurationInfo.containsKey(BAIDU_ROUTE_MATRIX_DRIVING_URL_RESULT_DISTANCE)) {
//                                        JSONObject distanceInfo = distanceAndDurationInfo.getJSONObject(BAIDU_ROUTE_MATRIX_DRIVING_URL_RESULT_DISTANCE);
//                                        if (distanceInfo.containsKey(BAIDU_ROUTE_MATRIX_DRIVING_URL_RESULT_DISTANCE_VALUE)) {
//                                            String distanceMeters = distanceInfo.getString(BAIDU_ROUTE_MATRIX_DRIVING_URL_RESULT_DISTANCE_VALUE);
//                                            if (StringUtils.isNotBlank(distanceMeters)) {
//                                                geoCoordinateInfo.setDistance(new BigDecimal(distanceMeters));
//                                            } else {
//                                                log.info("=== parseResponseOfRouteMatrixOfDriving | 字段{}的值为空 ===", BAIDU_ROUTE_MATRIX_DRIVING_URL_RESULT_DISTANCE_VALUE);
//                                            }
//                                        } else {
//                                            log.error("=== parseResponseOfRouteMatrixOfDriving | 不存在字段{} ===", BAIDU_ROUTE_MATRIX_DRIVING_URL_RESULT_DISTANCE_VALUE);
//                                            throw new BusinessException("不存在字段" + BAIDU_ROUTE_MATRIX_DRIVING_URL_RESULT_DISTANCE_VALUE);
//                                        }
//                                    } else {
//                                        log.error("=== parseResponseOfRouteMatrixOfDriving | 不存在字段{} ===", BAIDU_ROUTE_MATRIX_DRIVING_URL_RESULT_DISTANCE);
//                                        throw new BusinessException("不存在字段" + BAIDU_ROUTE_MATRIX_DRIVING_URL_RESULT_DISTANCE);
//                                    }
//                                    geoCoordinateInfoList.add(geoCoordinateInfo);
//                                }
//                            } else {
//                                log.info("=== parseResponseOfRouteMatrixOfDriving | 结果集为空 ===");
//                            }
//                        } else {
//                            log.error("=== parseResponseOfRouteMatrixOfDriving | 不存在字段{} ===", BAIDU_URL_RESPONSE_RESULT);
//                            throw new BusinessException("不存在字段" + BAIDU_URL_RESPONSE_RESULT);
//                        }
//                    } else {
//                        log.error("=== parseResponseOfRouteMatrixOfDriving | 响应状态不正常 | 状态: {} ===", status);
//                        throw new BusinessException("响应状态不正常（状态为" + status + "）");
//                    }
//                } else {
//                    log.error("=== parseResponseOfRouteMatrixOfDriving | 不存在字段{} ===", BAIDU_URL_RESPONSE_STATUS);
//                    throw new BusinessException("不存在字段" + BAIDU_URL_RESPONSE_STATUS);
//                }
//                return geoCoordinateInfoList;

            case XML:
                throw new BusinessException("该方式暂未实现");
            default:
                throw new BusinessException("未知的响应格式");
        }
    }

    /**
     *  解析坐标转换 URL 的响应
     *
     * @param response
     * @param outputFormatEnum
     * @return
     */
    private static List<GeoCoordinateInfo> parseResponseOfGeoConv(String response, OutputFormatEnum outputFormatEnum) {
        if (outputFormatEnum == null) {
            throw new BusinessException("响应格式为空，无法解析");
        }
        log.info("=== parseResponseOfGeoConv | 获取响应信息 | 内容: {} ===", response);
        switch (outputFormatEnum) {
            case JSON:
                List<GeoCoordinateInfo> geoCoordinateInfoList = new ArrayList<>();
                JSONObject jsonResponse = JSONObject.parseObject(response);
                checkResponseStatus(jsonResponse);
                JSONArray result = jsonResponse.getJSONArray(BAIDU_URL_RESPONSE_RESULT);
                checkNotNull(result);
                for (int i = 0; i < result.size(); i++) {
                    GeoCoordinateInfo geoCoordinateInfo = new GeoCoordinateInfo();
                    JSONObject coordinates = result.getJSONObject(i);
                    BigDecimal longitude = coordinates.getBigDecimal(BAIDU_GEO_CONV_URL_RESULT_LONGITUDE);
                    geoCoordinateInfo.setLongitude(longitude);
                    BigDecimal latitude = coordinates.getBigDecimal(BAIDU_GEO_CONV_URL_RESULT_LATITUDE);
                    geoCoordinateInfo.setLatitude(latitude);
                    geoCoordinateInfoList.add(geoCoordinateInfo);
                }
                return geoCoordinateInfoList;

//                List<GeoCoordinateInfo> geoCoordinateInfoList = new ArrayList<>();
//                JSONObject jsonObject = JSONObject.parseObject(response);
//                if (jsonObject.containsKey(BAIDU_URL_RESPONSE_STATUS)) {
//                    String status = jsonObject.getString(BAIDU_URL_RESPONSE_STATUS);
//                    if (BAIDU_URL_RESPONSE_STATUS_OK.equals(status)) {
//                        if (jsonObject.containsKey(BAIDU_URL_RESPONSE_RESULT)) {
//                            JSONArray result = jsonObject.getJSONArray(BAIDU_URL_RESPONSE_RESULT);
//                            int size = result.size();
//                            if (size > 0) {
//                                for (int i = 0; i < size; i++) {
//                                    GeoCoordinateInfo geoCoordinateInfo = new GeoCoordinateInfo();
//                                    JSONObject coordinates = result.getJSONObject(i);
//                                    if (coordinates.containsKey(BAIDU_GEO_CONV_URL_RESULT_LONGITUDE)) {
//                                        String longitude = coordinates.getString(BAIDU_GEO_CONV_URL_RESULT_LONGITUDE);
//                                        if (StringUtils.isNotBlank(longitude)) {
//                                            geoCoordinateInfo.setLongitude(new BigDecimal(longitude));
//                                        } else {
//                                            log.info("=== parseResponseOfGeoConv | 字段{}的值为空 ===", BAIDU_GEO_CONV_URL_RESULT_LONGITUDE);
//                                        }
//                                    } else {
//                                        log.error("=== parseResponseOfGeoConv | 不存在字段{} ===", BAIDU_GEO_CONV_URL_RESULT_LONGITUDE);
//                                        throw new BusinessException("不存在字段" + BAIDU_GEO_CONV_URL_RESULT_LONGITUDE);
//                                    }
//                                    if (coordinates.containsKey(BAIDU_GEO_CONV_URL_RESULT_LATITUDE)) {
//                                        String latitude = coordinates.getString(BAIDU_GEO_CONV_URL_RESULT_LATITUDE);
//                                        if (StringUtils.isNotBlank(latitude)) {
//                                            geoCoordinateInfo.setLatitude(new BigDecimal(latitude));
//                                        } else {
//                                            log.info("=== parseResponseOfGeoConv | 字段{}的值为空 ===", BAIDU_GEO_CONV_URL_RESULT_LATITUDE);
//                                        }
//                                    } else {
//                                        log.error("=== parseResponseOfGeoConv | 不存在字段{} ===", BAIDU_GEO_CONV_URL_RESULT_LATITUDE);
//                                        throw new BusinessException("不存在字段" + BAIDU_GEO_CONV_URL_RESULT_LATITUDE);
//                                    }
//                                    geoCoordinateInfoList.add(geoCoordinateInfo);
//                                }
//                            } else {
//                                log.info("=== parseResponseOfGeoConv | 结果集为空 ===");
//                            }
//                        } else {
//                            log.error("=== parseResponseOfGeoConv | 不存在字段{} ===", BAIDU_URL_RESPONSE_RESULT);
//                            throw new BusinessException("不存在字段" + BAIDU_URL_RESPONSE_RESULT);
//                        }
//                    } else {
//                        log.error("=== parseResponseOfGeoConv | 响应状态不正常 | 状态: {} ===", status);
//                        throw new BusinessException("响应状态不正常（状态为" + status + "）");
//                    }
//                } else {
//                    log.error("=== parseResponseOfGeoConv | 不存在字段{} ===", BAIDU_URL_RESPONSE_STATUS);
//                    throw new BusinessException("不存在字段" + BAIDU_URL_RESPONSE_STATUS);
//                }
//                return geoCoordinateInfoList;

            case XML:
                throw new BusinessException("该方式暂未实现");
            default:
                throw new BusinessException("未知的响应格式");
        }
    }

    /**
     *  解析返回格式
     *
     * @param outputFormatEnum
     * @return
     */
    private static String parseOutput(OutputFormatEnum outputFormatEnum) {
        return outputFormatEnum == null ? OutputFormatEnum.JSON.getCode() : outputFormatEnum.getCode();
    }

    /**
     *  解析地图坐标
     *
     * @param coordinates
     * @return
     */
    private static String parseLatitudesAndLongitudes(List<GeoCoordinateInfo> coordinates) {
        if (CollectionUtils.isEmpty(coordinates)) {
            throw new BusinessException("入参为空，无法解析");
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < coordinates.size(); i++) {
            GeoCoordinateInfo geoCoordinateInfo = coordinates.get(i);
            BigDecimal latitude = geoCoordinateInfo.getLatitude();
            BigDecimal longitude = geoCoordinateInfo.getLongitude();
            if (latitude == null || longitude == null) {
                throw new BusinessException("经纬度为空，无法解析");
            }
            stringBuffer.append(latitude).append(ENG_COMMA).append(longitude);
            if (i < coordinates.size() - 1) {
                stringBuffer.append(ENG_VERTICAL_LINE);
            }
        }
        return stringBuffer.toString();
    }

    /**
     *  解析地图坐标
     *
     * @param coordinates
     * @return
     */
    private static String parseCoordinates(List<GeoCoordinateInfo> coordinates) {
        if (CollectionUtils.isEmpty(coordinates)) {
            throw new BusinessException("入参为空，无法解析");
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < coordinates.size(); i++) {
            GeoCoordinateInfo geoCoordinateInfo = coordinates.get(i);
            BigDecimal longitude = geoCoordinateInfo.getLongitude();
            BigDecimal latitude = geoCoordinateInfo.getLatitude();
            if (latitude == null || longitude == null) {
                throw new BusinessException("经纬度为空，无法解析");
            }
            stringBuffer.append(longitude).append(ENG_COMMA).append(latitude);
            if (i < coordinates.size() - 1) {
                stringBuffer.append(ENG_SEMI_COLON);
            }
        }
        return stringBuffer.toString();
    }

    /**
     *  地理坐标信息
     */
    @Data
    public static class GeoCoordinateInfo implements Serializable {

        private static final long serialVersionUID = 3846504336507131957L;
        /**
         *  经度
         */
        private BigDecimal longitude;

        /**
         *  纬度
         */
        private BigDecimal latitude;

        /**
         *  距离差（米）
         */
        private BigDecimal distance;

        @Override
        public String toString() {
            return "GeoCoordinateInfo{" +
                    "longitude=" + longitude +
                    ", latitude=" + latitude +
                    ", distance=" + distance +
                    '}';
        }
    }

    /**
     *  百度支持的地理坐标系
     */
    public enum SupportedCoordinateTypeEnum implements Serializable {

        WGS84("1", "wgs84", "GPS标准坐标"),
        SOGOU("2", "", "搜狗地图坐标"),
        GCJ02("3", "gcj02", "火星坐标，即高德地图、腾讯地图和MapABC等地图使用的坐标"),
        GCJ02MC("4", "", "GCJ02对应的墨卡托平面坐标"),
        BD09LL("5", "bd09ll", "百度地图采用的经纬度坐标"),
        BD09MC("6", "bd09mc", "百度地图采用的墨卡托平面坐标"),
        TUBA("7", "", "图吧地图坐标"),
        FIVEONE("8", "", "51地图坐标"),
        ;
        private String code;

        private String name;

        private String description;

        SupportedCoordinateTypeEnum(String code, String name, String description) {
            this.code = code;
            this.name = name;
            this.description = description;
        }

        public String getCode() {
            return this.code;
        }

        public String getName() {
            return this.name;
        }

        public String getDescription() {
            return this.description;
        }

        public static SupportedCoordinateTypeEnum findByCode(String code) {
            for (SupportedCoordinateTypeEnum value : values()) {
                if (value.getCode().equals(code)) {
                    return value;
                }
            }
            return null;
        }
    }

    /**
     *  百度接口返回格式枚举
     */
    enum OutputFormatEnum implements Serializable {

        JSON("json", "JSON格式"),
        XML("xml", "XML格式"),
        ;
        private String code;

        private String description;

        OutputFormatEnum(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return this.code;
        }

        public String getDescription() {
            return this.description;
        }

        public static OutputFormatEnum findByCode(String code) {
            for (OutputFormatEnum value : values()) {
                if (value.getCode().equals(code)) {
                    return value;
                }
            }
            return null;
        }
    }

    /**
     *  检查百度 API 响应
     *
     * @param jsonResponse
     */
    private static void checkResponseStatus(JSONObject jsonResponse) {
        if (!jsonResponse.containsKey(BAIDU_URL_RESPONSE_STATUS)) {
            throw new BusinessException("没有表征响应状态的字段");
        }
        String status = jsonResponse.getString(BAIDU_URL_RESPONSE_STATUS);
        if (!BAIDU_URL_RESPONSE_STATUS_OK.equals(status)) {
            if (BAIDU_URL_RESPONSE_STATUS_SERVER_INTERNAL_ERROR.equals(status)) {
                throw new BusinessException(BAIDU_URL_RESPONSE_MESSAGE_SERVER_INTERNAL_ERROR);
            } else {
                throw new BusinessException("响应状态异常（" + status + "）");
            }
        }
    }

    /**
     *  检查非空
     *
     * @param object
     */
    private static void checkNotNull(Object object) {
        if (object == null) {
            throw new BusinessException("发现空值");
        }
    }
}
