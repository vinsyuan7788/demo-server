package com.demo.server.application.easyexcel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.fastjson.JSONObject;
import com.demo.server.application.easyexcel.model.CollectRecordTemplateResp;
import com.demo.server.common.utils.ExcelUtils;
import com.demo.server.web.DemoServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vince Yuan
 * @date 06/04/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoServerApplication.class)
public class TestEasyExcel {

    private String filePath1 = "C:/Users/yuanxu/Desktop/outsource_collection_record_example.xlsx";
    private String filePath2 = "C:/Users/yuanxu/Desktop/loan12354331_loan8AB9C9AS_20200202.xlsx";
    private String filePath3 = "C:/Users/yuanxu/Desktop/test_loan12354331_loan8AB9C9AS_20200202.xlsx";

    @Test
    public void test() throws Exception {
//        testEasyExcel();
        testEasyExcelRead();
    }

    private void testEasyExcelRead() {
        int totalsRead = 0;
        int startLine = 1;
        while (totalsRead < 50000) {
            List<Object> objects = ExcelUtils.readFromDisk(filePath3, 1, startLine, startLine + 5000, CollectRecordTemplateResp.class);
            log.info("=== XXX | 读取数据成功 | 行数: {} ===", objects.size());
            totalsRead += 5000;
        }
    }

    private void testEasyExcel() throws Exception {
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filePath1));
        List<Object> dataRead = EasyExcelFactory.read(inputStream, new Sheet(1, 1, CollectRecordTemplateResp.class));
        log.info("=== XXX | 读取数据成功 | 行数: {} | 数据: {} ===", CollectionUtils.isNotEmpty(dataRead) ? dataRead.size() : 0, JSONObject.toJSONString(dataRead));
        List<CollectRecordTemplateResp> dataToWrite = getDataToWrite(dataRead);
        log.info("=== XXX | 需要写入的数据 | 行数: {} ===", dataToWrite.size());
        ExcelUtils.writeToDisk(filePath2, getSheetNameAndData(dataToWrite), CollectRecordTemplateResp.class);
        log.info("=== XXX | 写入数据成功 | 行数: {} ===", dataToWrite.size());
    }

    private List<CollectRecordTemplateResp> getDataToWrite(List<Object> dataRead) {
        List<CollectRecordTemplateResp> dataToWrite = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(dataRead)) {
            List<CollectRecordTemplateResp> dataRowsRead = JSONObject.parseArray(JSONObject.toJSONString(dataRead), CollectRecordTemplateResp.class);
            for (int i = 0; i < 50000; i++) {
                dataToWrite.add(dataRowsRead.get(dataRowsRead.size() - 1));
            }
        }
        return dataToWrite;
    }

    private Map<String, List<? extends BaseRowModel>> getSheetNameAndData(List<CollectRecordTemplateResp> dataToWrite) {
        Map<String, List<? extends BaseRowModel>> map = new HashMap<>();
        map.put("loan12354331|loan8AB9C9AS|20200202", dataToWrite);
        return map;
    }
}
