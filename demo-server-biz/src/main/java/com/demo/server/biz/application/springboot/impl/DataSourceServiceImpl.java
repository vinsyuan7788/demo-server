package com.demo.server.biz.application.springboot.impl;

import com.demo.base.common.enums.DeleteEnum;
import com.demo.server.biz.application.springboot.DataSourceService;
import com.demo.server.core.research.IdService;
import com.demo.server.dal.model.DemoRecord;
import com.demo.server.dal.application.DemoRecordDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Vince Yuan
 * @date 02/09/2021
 */
@Slf4j
@Service
public class DataSourceServiceImpl implements DataSourceService {

    @Autowired
    private DemoRecordDao demoRecordDao;
    @Autowired
    private IdService idService;

    private final Long defaultOperator = 10001L;
    private final String[] optionalDemoNames = { "demo1", "demo2", "demo3" };
    private final Random random = new Random();

    @Override
    public Boolean addRecord() {
        // Get necessary information
        Long id = idService.getStandaloneId();
        String demoName = optionalDemoNames[random.nextInt(optionalDemoNames.length)];
        Date nowDate = new Date();
        // Insert record
        DemoRecord demoRecord = new DemoRecord();
        demoRecord.setId(id);
        demoRecord.setDemoName(demoName);
        demoRecord.setCreator(defaultOperator);
        demoRecord.setCreated(nowDate);
        demoRecord.setModifier(defaultOperator);
        demoRecord.setUpdated(nowDate);
        demoRecord.setDeleted(DeleteEnum.NORMAL.getCode());
        return demoRecordDao.insertSelective(demoRecord);
    }

    @Override
    public DemoRecord getLatestRecord() {
        return demoRecordDao.selectLatestRecord();
    }

    @Override
    public List<DemoRecord> getAllRecords() {
        return demoRecordDao.selectAllRecords();
    }

    @Override
    public Boolean removeAllRecords() {
        List<DemoRecord> demoRecords = demoRecordDao.selectAllRecords();
        List<Long> ids = demoRecords.stream().map(DemoRecord::getId).collect(Collectors.toList());
        return demoRecordDao.deleteRecordsLogically(ids);
    }
}
