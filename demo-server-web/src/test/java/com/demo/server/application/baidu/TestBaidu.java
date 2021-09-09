package com.demo.server.application.baidu;

import com.demo.server.application.utils.BaiduMapUtils;
import com.demo.server.application.utils.GeoPositionUtils;
import com.demo.server.web.DemoServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vince Yuan
 * @date 09/06/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoServerApplication.class)
public class TestBaidu {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    private final String ak = "GrYRbGMjgvDBgIeL6pkrKfrem8ylzKCs";

    @Test
    public void test() throws Exception {
        testBaiduMap();
    }

    private void testBaiduMap() {
//        String address = "深圳市南山区粤海街道科技园社区科苑路16号东方科技大厦2211";
        String address = "深圳市南山区科苑路16号";
//        String address = "北京市海淀区上地十街10号";
        RestTemplate restTemplate = restTemplateBuilder.build();
        BaiduMapUtils.GeoCoordinateInfo geoCoordinateInfo = BaiduMapUtils.getGeoCoordinateInfo(ak, address, restTemplate);
        log.info("=== XXX | 坐标信息: {} ===", geoCoordinateInfo);
        try {
            List<BaiduMapUtils.GeoCoordinateInfo> distanceInbetweeenList = BaiduMapUtils.getDistanceInbetweeen(ak, buildOrigins(), buildDestinations(), restTemplate);
            log.info("=== XXX | 距离列表: {} ===", distanceInbetweeenList);
        } catch (Exception e) {
            log.error("=== XXX | 计算距离异常 ===", e);
            double distance = GeoPositionUtils.distanceV1(116.308150d, 40.056878d, 180.308151d, 50.056879d);
            log.info("=== XXX | 距离: {} ===", distance);
        }
//        double lon1 = 116.308150D; double lat1 = 40.056878D;
//        double lon2 = 116.308151D; double lat2 = 40.056879D;
////        double lon1 = 116.364973D; double lat1 = 40.063597D;
////        double lon2 = 116.510842D; double lat2 = 39.90777D;
//        double dist1 = GeoPositionUtils.distanceV1(lon1, lat1, lon2, lat2);
//        double dist2 = GeoPositionUtils.distanceV2(lon1, lat1, lon2, lat2);
//        double dist3 = GeoPositionUtils.distanceV3(lon1, lat1, lon2, lat2);
//        double dist4 = GeoPositionUtils.distanceV4(lon1, lat1, lon2, lat2);
//        double dist5 = GeoPositionUtils.distanceV5(lon1, lat1, lon2, lat2);
//        log.info("=== XXX | dist1: {} ===", dist1);
//        log.info("=== XXX | dist2: {} ===", dist2);
//        log.info("=== XXX | dist3: {} ===", dist3);
//        log.info("=== XXX | dist4: {} ===", dist4);
//        log.info("=== XXX | dist5: {} ===", dist5);

        List<BaiduMapUtils.GeoCoordinateInfo> baiduCoordinate = BaiduMapUtils.getBaiduCoordinate(ak, buildOrigins(), BaiduMapUtils.SupportedCoordinateTypeEnum.GCJ02, restTemplate);
        log.info("=== XXX | 百度坐标: {} ===", baiduCoordinate);
    }

    private List<BaiduMapUtils.GeoCoordinateInfo> buildOrigins() {
        List<BaiduMapUtils.GeoCoordinateInfo> origins = new ArrayList<>();
        BaiduMapUtils.GeoCoordinateInfo geoCoordinateInfo = new BaiduMapUtils.GeoCoordinateInfo();
//        geoCoordinateInfo.setLongitude(BigDecimal.valueOf(113.95122062535008));
//        geoCoordinateInfo.setLatitude(BigDecimal.valueOf(22.551832727671536));
        geoCoordinateInfo.setLongitude(BigDecimal.valueOf(116.308150));
        geoCoordinateInfo.setLatitude(BigDecimal.valueOf(40.056878));
        origins.add(geoCoordinateInfo);
        return origins;
    }

    private List<BaiduMapUtils.GeoCoordinateInfo> buildDestinations() {
        List<BaiduMapUtils.GeoCoordinateInfo> destinations = new ArrayList<>();
        BaiduMapUtils.GeoCoordinateInfo geoCoordinateInfo = new BaiduMapUtils.GeoCoordinateInfo();
        geoCoordinateInfo.setLongitude(BigDecimal.valueOf(116.308151));
        geoCoordinateInfo.setLatitude(BigDecimal.valueOf(40.056879));
        destinations.add(geoCoordinateInfo);
        geoCoordinateInfo = new BaiduMapUtils.GeoCoordinateInfo();
        geoCoordinateInfo.setLongitude(BigDecimal.valueOf(180.308151));
        geoCoordinateInfo.setLatitude(BigDecimal.valueOf(50.056879));
        destinations.add(geoCoordinateInfo);
        return destinations;
    }
}
