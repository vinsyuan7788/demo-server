package com.demo.server.core.research;

/**
 * @author Vince Yuan
 * @date 03/02/2021
 */
public interface IdService {

    /**
     *  Get ID in stand-alone mode
     *
     * @return
     */
    Long getStandaloneId();

    Long getLongSnowflakeId();
}
