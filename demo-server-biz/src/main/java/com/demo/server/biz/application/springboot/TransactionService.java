package com.demo.server.biz.application.springboot;

/**
 * @author Vince Yuan
 * @date 03/02/2021
 */
public interface TransactionService {

    /**
     *  Trigger the roll-back of transaction
     *
     * @return
     */
    void triggerRollback();
}
