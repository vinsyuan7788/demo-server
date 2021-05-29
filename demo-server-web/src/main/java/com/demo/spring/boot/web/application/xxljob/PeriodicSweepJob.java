package com.demo.spring.boot.web.application.xxljob;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Vince Yuan
 * @date 04/01/2021
 */
@Slf4j
@Component
@JobHandler(value = "PeriodicSweepJob")
public class PeriodicSweepJob extends IJobHandler {

    @Override
    public ReturnT<String> execute(String param) {
        log.info("=== 执行调度任务 | 入参: {} ===", param);
        return ReturnT.SUCCESS;
    }
}
