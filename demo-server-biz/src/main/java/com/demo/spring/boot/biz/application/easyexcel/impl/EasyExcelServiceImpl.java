package com.demo.spring.boot.biz.application.easyexcel.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.demo.base.common.utils.LogUtils;
import com.demo.spring.boot.biz.application.easyexcel.EasyExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Vince Yuan
 * @date 05/14/2021
 */
@Slf4j
@Service
public class EasyExcelServiceImpl implements EasyExcelService {

    private String filePath = "E:/demo_excel.xlsx";

    @Override
    public void writeExcelFile() {
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcelFactory.getWriter(new FileOutputStream(filePath));
            List<List<Object>> data = getData();
            Sheet sheet = new Sheet(1, 0);
            sheet.setSheetName("测试页面");
            Table table = new Table(1);
            table.setHead(getTableHead());
            excelWriter.write1(data, sheet, table);
        } catch (Exception e) {
            log.error(LogUtils.getLogMessage("writeExcelFile", "Exception is found when excel is being written"), e);
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    @Override
    public List<Object> readExcelFile() {
        List<Object> dataOfRows = new ArrayList<>();
        try {
            byte[] content = FileUtils.readFileToByteArray(new File(filePath));
            BufferedInputStream stream = new BufferedInputStream(new ByteArrayInputStream(content != null ? content : new byte[0]));
            dataOfRows = EasyExcelFactory.read(stream, new Sheet(1, 2));
        } catch (Exception e) {
            log.error(LogUtils.getLogMessage("readExcelFile", "Exception is found when excel is being read"), e);
        }
        return dataOfRows;
    }

    private List<List<String>> getTableHead() {
        List<List<String>> headsOfColumns = new ArrayList<>();
        headsOfColumns.add(Arrays.asList("第1行字段1", "第2行字段1"));
        headsOfColumns.add(Arrays.asList("第1行字段2", "第2行字段1"));
        headsOfColumns.add(Arrays.asList("第1行字段3", "第2行字段2"));
        return headsOfColumns;
    }

    private List<List<Object>> getData() {
        List<List<Object>> dataOfRows = new ArrayList<>();
        dataOfRows.add(Arrays.asList(10001L, "客服", BigDecimal.valueOf(20583)));
        dataOfRows.add(Arrays.asList(10002L, "测试", BigDecimal.valueOf(30532)));
        dataOfRows.add(Arrays.asList(10003L, "开发", BigDecimal.valueOf(98754)));
        return dataOfRows;
    }
}
