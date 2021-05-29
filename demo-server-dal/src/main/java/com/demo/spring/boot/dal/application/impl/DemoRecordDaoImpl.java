package com.demo.spring.boot.dal.application.impl;

import com.demo.base.common.enums.DeleteEnum;
import com.demo.base.common.exception.bean.DAOException;
import com.demo.base.common.response.enums.ResponseEnum;
import com.demo.base.common.utils.ListUtils;
import com.demo.spring.boot.common.constant.CommonConstant;
import com.demo.spring.boot.dal.mapper.DemoRecordMapper;
import com.demo.spring.boot.dal.model.DemoRecord;
import com.demo.spring.boot.dal.model.DemoRecordExample;
import com.demo.spring.boot.dal.application.DemoRecordDao;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Vince Yuan
 * @date 02/09/2021
 */
@Slf4j
@Repository
public class DemoRecordDaoImpl implements DemoRecordDao {

    @Autowired
    private DemoRecordMapper demoRecordMapper;

    @Override
    public Boolean insertSelective(DemoRecord record) {
        try {
            return demoRecordMapper.insertSelective(record) > 0;
        } catch (Exception e) {
            throw new DAOException(ResponseEnum.INSERT_FAIL, e);
        }
    }

    @Override
    public DemoRecord selectLatestRecord() {
        DemoRecordExample example = new DemoRecordExample();
        DemoRecordExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(DeleteEnum.NORMAL.getCode());
        example.setOrderByClause(" created desc ");
        PageHelper.startPage(1, 1, false);
        List<DemoRecord> demoRecords = demoRecordMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(demoRecords) ? demoRecords.get(0) : null;
    }

    @Override
    public List<DemoRecord> selectAllRecords() {
        DemoRecordExample example = new DemoRecordExample();
        DemoRecordExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(DeleteEnum.NORMAL.getCode());
        return demoRecordMapper.selectByExample(example);
    }

    @Override
    public Boolean deleteRecordsLogically(List<Long> ids) {
        long totalUpdated = 0;
        List<List<Long>> idLists = ListUtils.splitBatch(ids, CommonConstant.ONE_THOUSAND);
        for (List<Long> idList : idLists) {
            DemoRecordExample example = new DemoRecordExample();
            DemoRecordExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(idList);
            DemoRecord record = new DemoRecord();
            record.setDeleted(DeleteEnum.DELETE.getCode());
            totalUpdated += demoRecordMapper.updateByExampleSelective(record, example);
        }
        return totalUpdated >= 0;
    }
}
