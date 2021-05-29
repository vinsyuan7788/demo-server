package com.demo.spring.boot.biz.application.springboot;

import java.util.concurrent.Future;

/**
 * @author Vince Yuan
 * @date 03/01/2021
 */
public interface AsynchronizationService {

    /**
     *  Count down
     */
    void countDown();

    /**
     *  Count up
     *
     * @return
     */
    Future<Boolean> countUp();
}
