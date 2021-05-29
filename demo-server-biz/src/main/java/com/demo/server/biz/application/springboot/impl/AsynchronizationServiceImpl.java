package com.demo.server.biz.application.springboot.impl;

import com.demo.server.biz.application.springboot.AsynchronizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author Vince Yuan
 * @date 03/01/2021
 */
@Slf4j
@Service
public class AsynchronizationServiceImpl implements AsynchronizationService {

    @Async
    @Override
    public void countDown() {
        int start = 1000;
        int end = 1;
        int step = 100;
        for (int i = start; i >= end; i--) {
            if (i % step == 0) {
                log.info("=== {}: count to {} ===", Thread.currentThread().getId(), i);
            }
        }
    }

    @Async
    @Override
    public Future<Boolean> countUp() {
        int start = 1;
        int end = 1000;
        int step = 100;
        for (int i = start; i <= end; i++) {
            if (i % step == 0) {
                log.info("=== {}: count to {} ===", Thread.currentThread().getId(), i);
            }
        }
        return new AsyncResult<>(true);
    }
}
