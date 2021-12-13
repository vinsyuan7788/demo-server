package com.demo.server.core.research.impl;

import com.demo.base.common.snowflake.SnowflakeIdService;
import com.demo.base.common.utils.DateUtils;
import com.demo.haima.client.Client;
import com.demo.haima.client.HaimaClient;
import com.demo.server.common.constant.CommonConstant;
import com.demo.server.common.utils.SystemUtils;
import com.demo.server.core.research.IdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author Vince Yuan
 * @date 03/02/2021
 */
@Slf4j
@Service
public class IdServiceImpl implements IdService {

    @Value("${snowflake.servers}")
    private String snowflakeServers;
    @Value("${snowflake.timeout}")
    private Integer snowflakeTimeout;

    @Override
    public Long getStandaloneId() {
        // Get the string representing current date, which is 17 digits
        String prefix = DateUtils.DATE_FORMAT_CONSECUTIVE_YEAR_TO_MILLIS.format(SystemUtils.currentDate());
        // Provide the suffix with 2 random digits
        Random random = new Random();
        int[] suffixes = new int[] { random.nextInt(CommonConstant.TEN), random.nextInt(CommonConstant.TEN) };
        StringBuffer suffixBuffer = new StringBuffer();
        for (int suffix : suffixes) {
            suffixBuffer.append(suffix);
        }
        String suffix = suffixBuffer.toString();
        // Get ID, which is 19 digits that Long-typed can contain
        return Long.valueOf(prefix + suffix);
    }

    // todo v.y. next step is to integrate snow-flake service through base project
//    private volatile Client client;
//
//    @Override
//    public Long getLongSnowflakeId() {
//        if (client == null) {
//            client = getClient();
//        }
//        try {
//            return client.getSnowFlakeId(0);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0L;
//        }
//    }
//
//    private Client getClient() {
//        try {
//            return new HaimaClient(snowflakeServers, snowflakeTimeout);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
