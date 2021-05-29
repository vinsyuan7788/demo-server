package com.demo.spring.boot.web;

import com.demo.base.common.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Vince Yuan
 * @date 12/29/2020
 */
@Slf4j
@EnableAsync
@EnableScheduling
@EnableTransactionManagement
@MapperScan({ "com.demo.spring.boot.dal.mapper" })
@SpringBootApplication(scanBasePackages = { "com.demo.*" })
public class DemoSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringBootApplication.class, args);
        log.info(LogUtils.getLogMessage("Spring-boot application is started"));
    }
}
