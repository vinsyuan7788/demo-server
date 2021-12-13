package com.demo.server.biz.application.springboot.impl;

import com.demo.base.common.enums.DeleteEnum;
import com.demo.base.common.snowflake.SnowflakeIdService;
import com.demo.server.biz.application.springboot.DataSourceService;
import com.demo.server.core.research.IdService;
import com.demo.server.dal.application.DemoRecordDao;
import com.demo.server.dal.model.DemoRecord;
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

    // todo v.y. next step is to integrate snow-flake service through base project
//    @Autowired
//    private SnowflakeIdService snowflakeIdService;

    private final Long defaultOperator = 10001L;
    private final String[] optionalDemoNames = { "demo1", "demo2", "demo3" };
    private final String[] optionalDemoAddresses = {
            "广东省深圳市南山区长岭村西区12幢503房",
            "广东省汕头市金平区梅园北区5幢304房",
            "湖北省武汉市桃林区金满堂小区13幢2201房",
    };
    private final Random random = new Random();

    @Override
    public Boolean addRecord() {
        // Get necessary information
        Long id = idService.getStandaloneId();
//        Long id = snowflakeIdService.getLongSnowflakeId();
//        Long id = idService.getLongSnowflakeId();
        String demoName = optionalDemoNames[random.nextInt(optionalDemoNames.length)];
        String demoAddress = optionalDemoAddresses[random.nextInt(optionalDemoAddresses.length)];
        Date nowDate = new Date();
        // Insert record
        DemoRecord demoRecord = new DemoRecord();
        demoRecord.setId(id);
        demoRecord.setDemoName(demoName);
        demoRecord.setDemoAddress(demoAddress);
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
        List<Long> ids = demoRecords.stream().map(DemoRecord::getId).distinct().collect(Collectors.toList());
        return demoRecordDao.deleteRecordsLogically(ids);
    }
}
