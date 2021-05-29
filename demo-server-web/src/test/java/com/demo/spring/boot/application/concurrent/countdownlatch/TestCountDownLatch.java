package com.demo.spring.boot.application.concurrent.countdownlatch;

import com.demo.spring.boot.application.concurrent.countdownlatch.task.CustomTask;
import com.demo.spring.boot.web.DemoSpringBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoSpringBootApplication.class)
public class TestCountDownLatch {

    @Test
    public void test() {
        testCountDownLatch();
    }

    private void testCountDownLatch() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        int numberOfThread = 3;
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThread);
        for (int i = 0; i < numberOfThread; i++) {
            CustomTask customTask = new CustomTask(countDownLatch, (i + 1), 3000 + (i * 2) * 1000);
            threadPool.execute(customTask);
        }
        threadPool.shutdown();
    }
}
