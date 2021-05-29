package com.demo.spring.boot.application.concurrent.forkjoin.task;

import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;

public class CustomTaskWithNoReturn extends ForkJoinTask<Void> {

    private long id;
    private List<Long> loadsToBeProcessed;
    private List<List<Long>> finalResult;
    private Long finalSum;
    private long layer;

    public CustomTaskWithNoReturn(long id, List<Long> loadsToBeProcessed, List<List<Long>> finalResult, Long finalSum, long layer) {
        this.id = id;
        this.layer = layer;
        this.loadsToBeProcessed = loadsToBeProcessed;
        this.finalSum = finalSum;
        this.finalResult = finalResult;
    }

    @Override
    public Void getRawResult() {
        System.out.println("CustomTask | Id: " + id + " | Layer: " + layer + " | LoadsToBeProcessed: " + loadsToBeProcessed + " | FinalResult: " + finalResult + " | FinalSum: " + finalSum);
        return null;
    }

    @Override
    protected void setRawResult(Void value) {  }

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
        return loadsToBeProcessed.size() <= 2;
    }

    private void performTask() {
        List<Long> result = loadsToBeProcessed.stream().map(element -> { return element * 2; }).collect(Collectors.toList());
        Long sum = 0L;
        for (Long loadToBeProcessed : loadsToBeProcessed) {
            sum += loadToBeProcessed;
        }
        addToFinalResult(result);
        addToFinalSum(sum);
    }

    private void splitTask() {
        List<Long> lowerList =  loadsToBeProcessed.subList(0, loadsToBeProcessed.size() / 2);
        List<Long> higherList = loadsToBeProcessed.subList(loadsToBeProcessed.size() / 2, loadsToBeProcessed.size());
        CustomTaskWithNoReturn lowerTask = new CustomTaskWithNoReturn(id - 1, lowerList, finalResult, finalSum, layer + 1);
        CustomTaskWithNoReturn higherTask = new CustomTaskWithNoReturn(id + 1, higherList, finalResult, finalSum, layer + 1);
        lowerTask.fork();
        higherTask.fork();
        lowerTask.join();
        higherTask.join();
        // 【】finalSum 的结果带不回来的原因
        System.out.println("CustomTask | Id: " + id + " | Layer: " + layer + " | LoadsToBeProcessed: " + loadsToBeProcessed + " | FinalSumFromChildren: " + finalSum);
    }

    private void addToFinalResult(List<Long> result) {
        synchronized (loadsToBeProcessed.getClass()) {
            finalResult.add(result);
        }
    }

    private void addToFinalSum(Long sum) {
        synchronized (loadsToBeProcessed.getClass()) {
            finalSum += sum;
        }
    }
}
