package com.demo.server.web;

import com.demo.base.common.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EnableAsync
@EnableScheduling
@EnableTransactionManagement
@MapperScan({ "com.demo.server.dal.mapper" })
@SpringBootApplication(scanBasePackages = { "com.demo.*" })
public class DemoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoServerApplication.class, args);
        log.info(LogUtils.getLogMessage("Server application is started"));
    }
}
