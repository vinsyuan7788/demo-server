package com.demo.server.web.configuration;

import com.demo.base.common.snowflake.SnowflakeIdService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  todo v.y. next step is to integrate snow-flake service through base project
 *
 * @author Vince Yuan
 * @date 2021/11/18
 */
//@Configuration
//public class SnowFlakeConfiguration {
//
//    @Value("${snowflake.servers}")
//    private String snowflakeServers;
//    @Value("${snowflake.timeout}")
//    private Integer snowflakeTimeout;
//
//    @Bean
//    public SnowflakeIdService getSnowflakeIdService() {
//        return new SnowflakeIdService(snowflakeServers, snowflakeTimeout);
//    }
//}
