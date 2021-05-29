package com.demo.spring.boot.application.concurrent.app;

import com.demo.spring.boot.application.concurrent.app.systemconcurrenttimemillis.SystemClock;
import com.demo.spring.boot.web.DemoSpringBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoSpringBootApplication.class)
public class TestConcurrentApp {

    @Test
    public void test() {
        testSystemClock();
    }

    private void testSystemClock() {
        long testStartTime = System.currentTimeMillis();
        int concurrency = Integer.MAX_VALUE;
        System.out.println("concurrency: " + concurrency);
        long start = System.currentTimeMillis();
        for (long i = 0; i < concurrency; i++) {
            SystemClock.now();
        }
        long end = System.currentTimeMillis();
        System.out.println("SystemClock Time:" + (end - start) + "毫秒");
        long start2 = System.currentTimeMillis();
        for (long i = 0; i < concurrency; i++) {
            System.currentTimeMillis();
        }
        long end2 = System.currentTimeMillis();
        System.out.println("currentTimeMillis Time:" + (end2 - start2) + "毫秒");
        System.out.println("=== testSystemClock() | TimeCost: " + (SystemClock.now() - testStartTime) + "ms ===");
    }

}
