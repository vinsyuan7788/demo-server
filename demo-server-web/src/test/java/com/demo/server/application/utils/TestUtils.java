package com.demo.server.application.utils;

import com.demo.server.web.DemoServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoServerApplication.class)
public class TestUtils {

    @Test
    public void test() throws Exception {
//        System.out.println("Test MatchUtils:");
//        testMatchUtils();
//        System.out.println("TestMatchUtils:");
//        testTimeFormatUtils();
        System.out.println("TestGeoPositionUtils:");
        testGeoPositionUtils();
    }

    private void testMatchUtils() throws Exception {
        MatchUtils.findLinesContainedInBothFiles("C:\\Users\\yuanxu\\Desktop\\text1.txt",
                "C:\\Users\\yuanxu\\Desktop\\text2.txt",
                "C:\\Users\\yuanxu\\Desktop\\output.txt");
    }
    
    private void testTimeFormatUtils() {
        long seconds = 10000L;
        String timeString = TimeFormatUtils.parseSecondsToHMS(seconds);
        System.out.println("Time string: " + timeString);
    }

    private void testGeoPositionUtils() {
//        double lon1 = 116.308150D; double lat1 = 40.056878D;
        double lon2 = 113.95337027043473; double lat2 = 22.552806094742175;
        double lon1 = 113.94765896307762; double lat1 = 22.555837270118194;
//        double lon2 = 116.510842D; double lat2 = 39.90777D;
        double dist1 = GeoPositionUtils.distanceV1(lon1, lat1, lon2, lat2);
        double dist2 = GeoPositionUtils.distanceV2(lon1, lat1, lon2, lat2);
        double dist3 = GeoPositionUtils.distanceV3(lon1, lat1, lon2, lat2);
        double dist4 = GeoPositionUtils.distanceV4(lon1, lat1, lon2, lat2);
        double dist5 = GeoPositionUtils.distanceV5(lon1, lat1, lon2, lat2);
        System.out.println("dist1: " + dist1);
        System.out.println("dist2: " + dist2);
        System.out.println("dist3: " + dist3);
        System.out.println("dist4: " + dist4);
        System.out.println("dist5: " + dist5);
    }
}
