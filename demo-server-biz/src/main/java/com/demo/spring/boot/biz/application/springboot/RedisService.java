package com.demo.spring.boot.biz.application.springboot;

import java.util.Collection;

/**
 * @author Vince Yuan
 * @date 05/15/2021
 */
public interface RedisService {

    /**
     *  Set the key-value pair
     *
     * @param key
     * @param value
     */
    void setKey(Object key, Object value);

    /**
     *  Get the value of specific key
     *
     * @param key
     * @return
     */
    Object getKey(Object key);

    /**
     *  Remove specific keys
     *
     * @param keys
     */
    void removeKeys(Collection<Object> keys);

    /**
     *  Get the next integer by Redis atomic integer
     *
     * @return
     */
    Integer getNextIntegerFromRedisAtomicInteger();
}
