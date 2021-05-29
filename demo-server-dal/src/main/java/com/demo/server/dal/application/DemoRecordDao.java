package com.demo.server.dal.application;

import com.demo.server.dal.model.DemoRecord;

import java.util.List;

/**
 * @author Vince Yuan
 * @date 02/09/2021
 */
public interface DemoRecordDao {

    /**
     *  Insert a record
     *
     * @param record
     * @return
     */
    Boolean insertSelective(DemoRecord record);

    /**
     *  Select the latest record
     *
     * @return
     */
    DemoRecord selectLatestRecord();

    /**
     *  Select all records
     *
     * @return
     */
    List<DemoRecord> selectAllRecords();

    /**
     *  Logically delete the record with specified IDs
     *
     * @param ids
     * @return
     */
    Boolean deleteRecordsLogically(List<Long> ids);
}
