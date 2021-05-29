package com.demo.server.biz.application.springboot.impl;

import com.demo.server.biz.application.springboot.SchedulingService;
import com.demo.server.common.utils.SystemUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Vince Yuan
 * @date 03/01/2021
 */
@Slf4j
@Service
public class SchedulingServiceImpl implements SchedulingService {

    @Override
    public Long currentTimeMillis() {
        return SystemUtils.currentTimeMillis();
    }

    @Override
    public String currentDateTimeString() {
        return SystemUtils.currentDateTimeString();
    }
}
