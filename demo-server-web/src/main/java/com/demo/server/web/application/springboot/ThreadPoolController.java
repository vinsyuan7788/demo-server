package com.demo.server.web.application.springboot;

import com.demo.base.common.response.bean.CommonResponse;
import com.demo.base.common.response.enums.ResponseEnum;
import com.demo.base.common.utils.LogUtils;
import com.demo.base.common.utils.utils.ParametersToLog;
import com.demo.server.common.utils.SystemUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;

/**
 * @author Vince Yuan
 * @date 04/20/2021
 */
@Api(value = "应用-SpringBoot-线程池", tags = "应用-SpringBoot-线程池")
@Slf4j
@RestController
@RequestMapping("/spring/boot/thread/pool")
public class ThreadPoolController {

    @Resource(name = "ioIntensiveThreadPool")
    private ExecutorService threadPool1;

    @Resource(name = "cpuIntensiveThreadPool")
    private ExecutorService threadPool2;

    @Resource(name = "customerThreadPool1")
    private ExecutorService threadPool3;

    @Resource(name = "customerThreadPool2")
    private ExecutorService threadPool4;

    @Resource(name = "customerThreadPool3")
    private ExecutorService threadPool5;

    @Resource(name = "customerThreadPool4")
    private ExecutorService threadPool6;

    @ApiOperation(value = "获取线程池", notes = "获取通过SpringBoot注入的线程池")
    @PostMapping("/getThreadPool")
    public CommonResponse getThreadPool() {
        log.info(LogUtils.getLogMessage("getThreadPool", new ParametersToLog()
                .addParameter("threadPool1", threadPool1)
                .addParameter("threadPool2", threadPool2)
                .addParameter("threadPool3", threadPool3)
                .addParameter("threadPool4", threadPool4)
                .addParameter("threadPool5", threadPool5)
                .addParameter("threadPool6", threadPool6)));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(null)
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }
}
