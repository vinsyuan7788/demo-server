package com.demo.spring.boot.application.concurrent.countdownlatch.task;

import java.util.concurrent.CountDownLatch;

public class CustomTask extends Thread {

    private CountDownLatch countDownLatch;
    private long timeoutToWait;
    private long id;

    public CustomTask(CountDownLatch countDownLatch, long id, long timeoutToWait) {
        this.countDownLatch = countDownLatch;
        this.id = id;
        this.timeoutToWait = timeoutToWait;
    }

    @Override
    public void run() {
        try {
            System.out.println("CustomTask " + id + " | Start...");
            Thread.sleep(timeoutToWait);
            System.out.println("CustomTask " + id + " | Waiting...");
            countDownLatch.countDown();
            countDownLatch.await();
            System.out.println("CustomTask " + id + " | Done...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
