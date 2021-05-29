package com.demo.server.application.concurrent.forkjoin;

import com.demo.server.application.concurrent.forkjoin.task.CustomTaskWithReturn;
import com.demo.server.application.concurrent.forkjoin.task.CustomTaskWithNoReturn;
import com.demo.server.web.DemoServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoServerApplication.class)
public class TestForkJoin {

    @Test
    public void test() throws Exception {
        System.out.println("testForkJoinWithReturn:");
        testForkJoinWithReturn();
        System.out.println("testForkJoinWithNoReturn:");
        testForkJoinWithNoReturn();
    }

    private void testForkJoinWithReturn() throws Exception {

        // Input
        List<Long> billIds = Arrays.asList(1l, 2l, 3l, 4l, 5l, 6l, 7l, 8l, 9l, 10l);

        ForkJoinPool threadPool = ForkJoinPool.commonPool();
        CustomTaskWithReturn customTask = new CustomTaskWithReturn(0, billIds, 0);
        ForkJoinTask<Long> futureResult = threadPool.submit(customTask);

        // Output
        Long result = futureResult.get();
        System.out.println(result);
    }

    private void testForkJoinWithNoReturn() throws Exception {

        // Input
        List<Long> billIds = Arrays.asList(1l, 2l, 3l, 4l, 5l, 6l, 7l, 8l, 9l, 10l);
        // Output
        List<List<Long>> finalBillIds = new ArrayList<>();
        Long finalSum = new Long(0L);

        ForkJoinPool threadPool = ForkJoinPool.commonPool();
        CustomTaskWithNoReturn customTask = new CustomTaskWithNoReturn(0, billIds, finalBillIds, finalSum, 0);
        ForkJoinTask<Void> futureResult = threadPool.submit(customTask);
        futureResult.get();

        System.out.println(finalBillIds.size() + " | " + finalBillIds);
        System.out.println(finalSum);
    }
}
