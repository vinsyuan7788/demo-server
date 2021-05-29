package com.demo.spring.boot.web.application.springboot;

import com.demo.base.common.response.bean.CommonResponse;
import com.demo.base.common.response.enums.ResponseEnum;
import com.demo.spring.boot.biz.application.springboot.DataSourceService;
import com.demo.spring.boot.common.utils.SystemUtils;
import com.demo.spring.boot.dal.model.DemoRecord;
import com.demo.spring.boot.resp.DemoRecordResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vince Yuan
 * @date 02/09/2021
 */
@Api(value = "应用-SpringBoot-数据源访问", tags = "应用-SpringBoot-数据源访问")
@Slf4j
@RestController
@RequestMapping("/spring/boot/data/source")
public class DataSourceController {

    @Autowired
    private DataSourceService dataSourceService;

    @ApiOperation(value = "增加记录", notes = "往数据库中的表增加一条记录")
    @PostMapping("/addRecord")
    public CommonResponse addRecord() {
        boolean isInsertSuccessful = dataSourceService.addRecord();
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(isInsertSuccessful))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "获取最新记录", notes = "获取数据库中的表的最新记录")
    @PostMapping("/getLatestRecord")
    public CommonResponse getLatestRecord() {
        DemoRecord demoRecord = dataSourceService.getLatestRecord();
        DemoRecordResp demoRecordResp = new DemoRecordResp();
        if (demoRecord != null) {
            BeanUtils.copyProperties(demoRecord, demoRecordResp);
        }
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(demoRecordResp))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "获取所有记录", notes = "获取数据库中的表的所有记录")
    @PostMapping("/getAllRecords")
    public CommonResponse getAllRecords() {
        List<DemoRecord> demoRecords = dataSourceService.getAllRecords();
        List<DemoRecordResp> demoRecordResps = demoRecords.stream().map(demoRecord -> {
            DemoRecordResp demoRecordResp = new DemoRecordResp();
            BeanUtils.copyProperties(demoRecord, demoRecordResp);
            return demoRecordResp;
        }).collect(Collectors.toList());
        return CommonResponse.<DemoRecordResp>builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(demoRecordResps)
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "移除所有记录", notes = "移除数据库的表的所有记录")
    @PostMapping("/removeAllRecords")
    public CommonResponse removeAllRecords() {
        boolean isRemovalSuccessful = dataSourceService.removeAllRecords();
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(isRemovalSuccessful))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }
}
