package com.demo.server.web.application.excel;

import com.demo.base.common.response.bean.CommonResponse;
import com.demo.base.common.response.enums.ResponseEnum;
import com.demo.server.biz.application.easyexcel.EasyExcelService;
import com.demo.server.common.utils.SystemUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @author Vince Yuan
 * @date 05/14/2021
 */
@Api(value = "应用-Excel-EasyExcel", tags = "应用-Excel-EasyExcel")
@Slf4j
@RestController
@RequestMapping("/excel/easy/excel")
public class EasyExcelController {

    @Autowired
    private EasyExcelService easyExcelService;

    @ApiOperation(value = "写入Excel文件", notes = "写入Excel文件")
    @PostMapping("/writeExcel")
    public CommonResponse writeExcel() {
        easyExcelService.writeExcelFile();
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList("Excel file has been written successfully"))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "读取Excel文件", notes = "读取Excel文件")
    @PostMapping("/readExcel")
    public CommonResponse readExcel() {
        List<Object> dataRows = easyExcelService.readExcelFile();
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(dataRows)
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }
}
