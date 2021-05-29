package com.demo.spring.boot.application.concurrent.cyclicbarrier.task;

import java.util.List;

public class ActionTask extends Thread {

    private long id;
    private List<List<Long>> finalResult;

    public ActionTask(long id, List<List<Long>> finalResult) {
        this.id = id;
        this.finalResult = finalResult;
    }

    @Override
    public void run() {
        System.out.println("ActionTask " + id + " | Perform Action...");
        System.out.println("ActionTask " + id + " | finalResult | 大小: " + finalResult.size() + " | 内容: " + finalResult);
    }
}
