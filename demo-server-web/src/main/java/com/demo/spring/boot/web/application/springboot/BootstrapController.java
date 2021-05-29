package com.demo.spring.boot.web.application.springboot;

import com.demo.base.common.response.bean.CommonResponse;
import com.demo.base.common.response.enums.ResponseEnum;
import com.demo.base.common.utils.LogUtils;
import com.demo.base.common.utils.utils.ParametersToLog;
import com.demo.spring.boot.common.utils.SystemUtils;
import com.demo.spring.boot.resp.GetEnvironmentResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author Vince Yuan
 * @date 03/27/2021
 */
@Api(value = "应用-SpringBoot-启动过程", tags = "应用-SpringBoot-启动过程")
@Slf4j
@RestController
@RequestMapping("/spring/boot/bootstrap")
public class BootstrapController {

    @Autowired
    private Environment environment;

    @ApiOperation(value = "获取环境", notes = "获取SpringBoot的Environment（以获取更多关于项目的信息）")
    @PostMapping("/getEnvironment")
    public CommonResponse getEnvironment() {
        GetEnvironmentResp resp = new GetEnvironmentResp();
        log.info("=== Get Spring-Boot environment | environment: {} ===", environment);
        log.info(LogUtils.getLogMessage("getEnvironment", "Get Spring-Boot environment", new ParametersToLog()
                .addParameter("environment", environment)));
        resp.setDefaultProfiles(environment.getDefaultProfiles());
        resp.setActiveProfiles(environment.getActiveProfiles());
        resp.setServerPort(environment.getProperty("server.port"));
        resp.setContextPath(environment.getProperty("server.context-path"));
        resp.setServletPath(environment.getProperty("server.servlet-path"));
        resp.setDisplayName(environment.getProperty("server.display-name"));
        resp.setSpringApplicationName(environment.getProperty("spring.application.name"));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(resp))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }
}
