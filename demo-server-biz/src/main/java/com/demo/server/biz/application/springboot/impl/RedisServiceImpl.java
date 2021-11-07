package com.demo.server.biz.application.springboot.impl;

import com.demo.base.common.utils.LogUtils;
import com.demo.base.common.utils.bean.ParametersToLog;
import com.demo.server.biz.application.springboot.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * @author Vince Yuan
 * @date 05/15/2021
 */
@Slf4j
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void setKey(Object key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public Object getKey(Object key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void removeKeys(Collection<Object> keys) {
        redisTemplate.delete(keys);
    }

    @Override
    public Integer getNextIntegerFromRedisAtomicInteger() {
        String key = "zlsd-asset-work-manage-ImpExpManageService-insert-20210515";
        log.info(LogUtils.getLogMessage("getNextIntegerFromRedisAtomicInteger", new ParametersToLog()
                .addParameter("key", key)));
        RedisAtomicInteger entityIdCounter = new RedisAtomicInteger(key, redisTemplate.getConnectionFactory());
        int increment = entityIdCounter.getAndIncrement();
        if (increment == 0) {
            long timeout = 5; TimeUnit timeUnit = TimeUnit.MINUTES;
            entityIdCounter.expire(5, TimeUnit.MINUTES);
            log.info(LogUtils.getLogMessage("getNextIntegerFromRedisAtomicInteger", "Set expiration time for the distributed atomic integer", new ParametersToLog()
                    .addParameter("key", key)
                    .addParameter("timeout", timeout)
                    .addParameter("time unit", timeUnit)));
        }
        return new Integer(increment + 1);
    }
}
