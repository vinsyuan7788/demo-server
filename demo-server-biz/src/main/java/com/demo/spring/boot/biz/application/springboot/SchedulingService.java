package com.demo.spring.boot.biz.application.springboot;

/**
 * @author Vince Yuan
 * @date 03/01/2021
 */
public interface SchedulingService {

    /**
     *  Get current time (in millisecond)
     *
     * @return
     */
    Long currentTimeMillis();

    /**
     *  Get current date-time string (with format "yyyy-MM-dd HH:mm:ss.SSS")
     *
     * @return
     */
    String currentDateTimeString();
}
