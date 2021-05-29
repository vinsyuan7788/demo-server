package com.demo.server.biz.application.springboot;

import com.demo.server.dal.model.DemoRecord;

import java.util.List;

/**
 * @author Vince Yuan
 * @date 02/09/2021
 */
public interface DataSourceService {

    /**
     *  Add a record
     *
     * @return
     */
    Boolean addRecord();

    /**
     *  Get the latest record
     *
     * @return
     */
    DemoRecord getLatestRecord();

    /**
     *  Get all records
     *
     * @return
     */
    List<DemoRecord> getAllRecords();

    /**
     *  Remove all records
     *
     * @return
     */
    Boolean removeAllRecords();
}
