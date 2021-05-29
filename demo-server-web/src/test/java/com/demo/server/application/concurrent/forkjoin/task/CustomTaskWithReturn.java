package com.demo.server.application.concurrent.forkjoin.task;

import java.util.List;
import java.util.concurrent.ForkJoinTask;

public class CustomTaskWithReturn extends ForkJoinTask<Long> {

    private Long result;
    private List<Long> loadsToBeProcessed;
    private long id;
    private long layer;

    public CustomTaskWithReturn(long id, List<Long> loadsToBeProcessed, long layer) {
        this.id = id;
        this.layer = layer;
        this.loadsToBeProcessed = loadsToBeProcessed;
    }

    @Override
    public Long getRawResult() {
        System.out.println("CustomTask | Id: " + id + " | Layer: " + layer + " | LoadsToBeProcessed: " + loadsToBeProcessed + " | Result: " + result);
        return result;
    }

    @Override
    protected void setRawResult(Long value) {
        this.result = value;
    }

    @Override
    protected boolean exec() {
        try {
            if (canPerformTask()) {
                performTask();
            } else {
                splitTask();
            }
            // Thread.sleep(1000L);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean canPerformTask() {
        return loadsToBeProcessed.size() == 1;
    }

    private void performTask() {
        this.result = this.loadsToBeProcessed.get(0);
    }

    private void splitTask() {
        List<Long> lowerList =  loadsToBeProcessed.subList(0, loadsToBeProcessed.size() / 2);
        List<Long> higherList = loadsToBeProcessed.subList(loadsToBeProcessed.size() / 2, loadsToBeProcessed.size());
        CustomTaskWithReturn lowerTask = new CustomTaskWithReturn(id - 1, lowerList, layer + 1);
        CustomTaskWithReturn higherTask = new CustomTaskWithReturn(id + 1, higherList, layer + 1);
        lowerTask.fork();
        higherTask.fork();
        this.result = lowerTask.join() + higherTask.join();
    }
}
