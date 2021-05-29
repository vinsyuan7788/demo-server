package com.demo.server.biz.application.springboot.impl;

import com.demo.base.common.exception.bean.BusinessException;
import com.demo.server.biz.application.springboot.TransactionService;
import com.demo.server.biz.application.springboot.DataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Vince Yuan
 * @date 03/02/2021
 */
@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private DataSourceService dataSourceService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void triggerRollback() {
        // Add record
        dataSourceService.addRecord();
        // throw an exception before transaction commit
        throw new BusinessException("An exception occurs when adding new record");
    }
}
