package com.demo.server.application.utils;

/**
 * @author Vince Yuan
 * @date 09/03/2021
 */
public class GeoPositionUtils {

    /**
     *  地球半径（米）
     */
    private static final double EARTH_RADIUS_METERS_V2 = 6371000D;
    private static final double EARTH_RADIUS_METERS_V3 = 6378137D;
    private static final double EARTH_RADIUS_METERS_V4 = 6370996.81D;
    private static final double EARTH_RADIUS_METERS_V5 = 6371000D;
    /**
     *  π
     */
    private static final double PI_V5 = 3.14169D;
    /**
     *  每经度单位（米）
     */
    private static final double METER_PER_LONGITUDE = 102834.74258026089786013677476285D;
    /**
     *  每纬度单位（米）
     */
    private static final double METER_PER_LATITUDE = 111712.69150641055729984301412873D;

    private GeoPositionUtils() {}

    /**
     *  计算两个坐标之间的距离（米）<br/>
     *  -- 五种方式中，该方式下相对接近百度的计算结果
     *
     * @param sourceLongitude 源经度
     * @param sourceLatitude 源纬度
     * @param targetLongitude 目标经度
     * @param targetLatitude 目标纬度
     * @return 两个坐标间的距离（米）
     */
    public static double distanceV1(double sourceLongitude, double sourceLatitude, double targetLongitude, double targetLatitude) {
        double b = Math.abs((sourceLatitude - targetLatitude) * METER_PER_LONGITUDE);
        double a = Math.abs((sourceLongitude - targetLongitude) * METER_PER_LATITUDE);
        return Math.sqrt((a * a + b * b));
    }

    /**
     *  计算两个坐标之间的距离（米）<br/>
     *  -- 基于 Google 地图的计算方式
     *
     * @param sourceLongitude 源经度
     * @param sourceLatitude 源纬度
     * @param targetLongitude 目标经度
     * @param targetLatitude 目标纬度
     * @return 两个坐标间的距离（米）
     */
    public static double distanceV2(double sourceLongitude, double sourceLatitude, double targetLongitude, double targetLatitude) {
        return distanceByGoogle(sourceLongitude, sourceLatitude, targetLongitude, targetLatitude, EARTH_RADIUS_METERS_V2);
    }

    /**
     *  计算两个坐标之间的距离（米）<br/>
     *  -- 基于 Google 地图的计算方式
     *
     * @param sourceLongitude 源经度
     * @param sourceLatitude 源纬度
     * @param targetLongitude 目标经度
     * @param targetLatitude 目标纬度
     * @return 两个坐标间的距离（米）
     */
    public static double distanceV3(double sourceLongitude, double sourceLatitude, double targetLongitude, double targetLatitude) {
        return distanceByGoogle(sourceLongitude, sourceLatitude, targetLongitude, targetLatitude, EARTH_RADIUS_METERS_V3);
    }

    /**
     *  计算两个坐标之间的距离（米）<br/>
     *  -- 基于百度地图的计算方式
     *
     * @param sourceLongitude 源经度
     * @param sourceLatitude 源纬度
     * @param targetLongitude 目标经度
     * @param targetLatitude 目标纬度
     * @return 两个坐标间的距离（米）
     */
    public static double distanceV4(double sourceLongitude, double sourceLatitude, double targetLongitude, double targetLatitude){
        return distanceByBaidu(sourceLongitude, sourceLatitude, targetLongitude, targetLatitude, EARTH_RADIUS_METERS_V4, Math.PI);
    }

    /**
     *  计算两个坐标之间的距离（米）<br/>
     *  -- 基于百度地图的计算方式
     *
     * @param sourceLongitude 源经度
     * @param sourceLatitude 源纬度
     * @param targetLongitude 目标经度
     * @param targetLatitude 目标纬度
     * @return 两个坐标间的距离（米）
     */
    public static double distanceV5(double sourceLongitude, double sourceLatitude, double targetLongitude, double targetLatitude){
        return distanceByBaidu(sourceLongitude, sourceLatitude, targetLongitude, targetLatitude, EARTH_RADIUS_METERS_V5, PI_V5);
    }

    /**
     *  计算两个坐标之间的距离（米）<br/>
     *  -- 基于百度地图的计算方式
     *
     * @param sourceLongitude 源经度
     * @param sourceLatitude 源纬度
     * @param targetLongitude 目标经度
     * @param targetLatitude 目标纬度
     * @param earthRadiusMeters 地球半径（米）
     * @param pi π
     * @return 两个坐标间的距离（米）
     */
    private static double distanceByBaidu(double sourceLongitude, double sourceLatitude, double targetLongitude, double targetLatitude, double earthRadiusMeters, double pi){
        double pk = 180 / pi;
        double a1 = sourceLatitude / pk;
        double a2 = sourceLongitude / pk;
        double b1 = targetLatitude / pk;
        double b2 = targetLongitude / pk;
        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
        double t3 = Math.sin(a1) * Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);
        return earthRadiusMeters * tt;
    }

    /**
     *  计算两个坐标之间的距离（米）<br/>
     *  -- 基于 Google 地图的计算方式
     *
     * @param sourceLongitude 源经度
     * @param sourceLatitude 源纬度
     * @param targetLongitude 目标经度
     * @param targetLatitude 目标纬度
     * @param earthRadiusMeters 地球半径（米）
     * @return 两个坐标间的距离（米）
     */
    private static double distanceByGoogle(double sourceLongitude, double sourceLatitude, double targetLongitude, double targetLatitude, double earthRadiusMeters) {
        double radLat1 = rad(sourceLatitude);
        double radLat2 = rad(targetLatitude);
        double a = radLat1 - radLat2;
        double b = rad(sourceLongitude) - rad(targetLongitude);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * earthRadiusMeters;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    /**
     *  转化为弧度(rad)
     *
     * @param d 经纬度
     * @return 弧度
     */
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static double distanceV6(double sourceLongitude, double sourceLatitude, double targetLongitude, double targetLatitude) {
        double b = Math.abs((sourceLatitude - targetLatitude) * METER_PER_LATITUDE);
        double a = Math.abs((sourceLongitude - targetLongitude) * METER_PER_LONGITUDE);
        return Math.sqrt((a * a + b * b));
    }
}
