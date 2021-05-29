package com.demo.server.application.concurrent.cyclicbarrier;

import com.demo.server.application.concurrent.cyclicbarrier.task.ActionTask;
import com.demo.server.application.concurrent.cyclicbarrier.task.CustomTask;
import com.demo.server.web.DemoServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoServerApplication.class)
public class TestCyclicBarrier {

    @Test
    public void test() {
        testCyclicBarrier();
    }

    private void testCyclicBarrier() {
        List<List<Long>> finalResult = new ArrayList<>();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        int numberOfThread = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(numberOfThread, new ActionTask(0, finalResult));
        for (int i = 0; i < numberOfThread; i++) {
            CustomTask customTask = new CustomTask(cyclicBarrier, (i + 1), 3000 + (i * 2) * 1000, finalResult);
            threadPool.execute(customTask);
        }
        threadPool.shutdown();
    }
}
