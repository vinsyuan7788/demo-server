package com.demo.server.web.application.springboot;

import com.demo.base.common.response.bean.CommonResponse;
import com.demo.base.common.response.enums.ResponseEnum;
import com.demo.server.biz.application.springboot.SchedulingService;
import com.demo.server.common.utils.SystemUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author Vince Yuan
 * @date 03/01/2021
 */
@Api(value = "应用-SpringBoot-调度执行", tags = "应用-SpringBoot-调度执行")
@Slf4j
@RestController
@RequestMapping("/spring/boot/scheduling")
public class SchedulingController {

    @Autowired
    private SchedulingService schedulingService;

    @ApiOperation(value = "获取调度任务的结果", notes = "获取通过SpringBoot调度的任务的结果")
    @PostMapping("/getResultOfScheduledTask")
    public CommonResponse getResultOfScheduledTask() {
        long currentTime = schedulingService.currentTimeMillis();
        String currentDateTimeString = schedulingService.currentDateTimeString();
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList("系统时间 | 毫秒格式: " + currentTime + " | 字符串格式: " + currentDateTimeString))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }
}
