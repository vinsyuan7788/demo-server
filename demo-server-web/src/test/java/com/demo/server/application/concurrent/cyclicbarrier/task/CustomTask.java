package com.demo.server.application.concurrent.cyclicbarrier.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class CustomTask extends Thread {

    private CyclicBarrier cyclicBarrier;
    private long timeoutToWait;
    private long id;
    private List<List<Long>> finalResult;

    public CustomTask(CyclicBarrier cyclicBarrier, long id, long timeoutToWait, List<List<Long>> finalResult) {
        this.cyclicBarrier = cyclicBarrier;
        this.id = id;
        this.timeoutToWait = timeoutToWait;
        this.finalResult = finalResult;
    }

    @Override
    public void run() {
        try {
            System.out.println("CustomTask " + id + " | Start...");

            List<Long> eachResult = new ArrayList<>();
            for (long i = id; i < (id + 5); i++) {
                eachResult.add(i);
            }
            addFinalResult(eachResult);

            Thread.sleep(timeoutToWait);
            System.out.println("CustomTask " + id + " | Waiting...");
            cyclicBarrier.await();
            System.out.println("CustomTask " + id + " | Done...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addFinalResult(List<Long> eachResult) {
        synchronized (finalResult.getClass()) {
            finalResult.add(eachResult);
        }
    }
}
