package com.demo.server.web.application.springboot;

import com.demo.base.common.response.bean.CommonResponse;
import com.demo.base.common.response.enums.ResponseEnum;
import com.demo.base.common.utils.LogUtils;
import com.demo.server.biz.application.springboot.AsynchronizationService;
import com.demo.server.common.utils.SystemUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.concurrent.Future;

/**
 * @author Vince Yuan
 * @date 02/26/2021
 */
@Api(value = "应用-SpringBoot-异步执行", tags = "应用-SpringBoot-异步执行")
@Slf4j
@RestController
@RequestMapping("/spring/boot/asynchronization")
public class AsynchronizationController {

    @Autowired
    private AsynchronizationService asynchronizationService;

    @ApiOperation(value = "执行异步任务", notes = "通过SpringBoot执行异步任务")
    @PostMapping("/executeAsynchronousTask")
    public CommonResponse executeAsynchronousTask() throws Exception {
        log.info(LogUtils.getLogMessage("executeAsynchronousTask", "API Starts"));
        asynchronizationService.countDown();
        Future<Boolean> futureResult = asynchronizationService.countUp();
        Boolean result = futureResult.get();
        log.info(LogUtils.getLogMessage("executeAsynchronousTask", "API ends"));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(result))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }
}
