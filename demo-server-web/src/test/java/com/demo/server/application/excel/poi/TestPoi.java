package com.demo.server.application.excel.poi;

import com.alibaba.fastjson.JSONObject;
import com.demo.server.application.excel.poi.model.CollectionRecord;
import com.demo.server.common.utils.EasyExcelUtils;
import com.demo.server.web.DemoServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * @author Vince Yuan
 * @date 06/08/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoServerApplication.class)
public class TestPoi {

    private String filePath1 = "C:/Users/yuanxu/Desktop/outsource_collection_record_example_002.xlsx";
    private byte[] byteArray;

    @Test
    public void test() throws Exception {
        testPoiWrite();
        testPoiRead();
//        testEasyExcelRead();
    }

    private void testPoiWrite() throws Exception {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        FileOutputStream outputStream = new FileOutputStream(filePath1);

        // 工作簿
        SXSSFWorkbook workBook = new SXSSFWorkbook();
        workBook.setCompressTempFiles(true);
        /**
         *  样式
         *  -- 字段样式
         */
        CellStyle fieldStyle = workBook.createCellStyle();
        /**
         *  样式
         *  -- 值样式
         */
        CellStyle valueStyle = workBook.createCellStyle();
        /**
         *  第一页
         *  -- 前两行
         */
        SXSSFSheet sheet1 = workBook.createSheet("第一页");
        SXSSFRow row_1 = sheet1.createRow(0);
        SXSSFCell cell_1 = row_1.createCell(0, CellType.STRING);
        cell_1.setCellValue("编号");
        cell_1.setCellStyle(fieldStyle);
        SXSSFCell cell_2 = row_1.createCell(1, CellType.STRING);
        cell_2.setCellValue("日期");
        cell_2.setCellStyle(fieldStyle);
        SXSSFCell cell_3 = row_1.createCell(2, CellType.STRING);
        cell_3.setCellValue("金额");
        cell_3.setCellStyle(fieldStyle);
        SXSSFCell cell_4 = row_1.createCell(3, CellType.STRING);
        cell_4.setCellValue("备注");
        cell_4.setCellStyle(fieldStyle);
        SXSSFRow row_2 = sheet1.createRow(1);
        cell_1 = row_2.createCell(0, CellType.STRING);
        cell_1.setCellValue("89757");
        cell_1.setCellStyle(valueStyle);
        cell_2 = row_2.createCell(1, CellType.STRING);
        cell_2.setCellValue("2021-01-01 10:00:00");
        cell_2.setCellStyle(valueStyle);
        cell_3 = row_2.createCell(2, CellType.STRING);
        cell_3.setCellValue("5361.845");
        cell_3.setCellStyle(valueStyle);
        cell_4 = row_2.createCell(3, CellType.STRING);
        cell_4.setCellValue("！@#！@%￥……%￥&……*（&……（*哇哈哈哈哈哈哈哈");
        cell_4.setCellStyle(valueStyle);
        /**
         *  第二页
         */
        SXSSFSheet sheet2 = workBook.createSheet("第二页");
        /**
         *  第三页
         */
        SXSSFSheet sheet3 = workBook.createSheet("第三页");
        workBook.write(outputStream);
        workBook.close();
        workBook.dispose();

        byteArray = outputStream.toByteArray();
        log.info("=== XXX | 字节数: {} ===", byteArray.length);
    }

    private void testPoiRead() throws Exception {

        ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray);
//        FileInputStream inputStream = new FileInputStream(filePath1);

        XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
        int numberOfSheets = workBook.getNumberOfSheets();
        Integer physicalNumberOfCells = null;
        log.info("=== XXX | 页数: {} ===", numberOfSheets);
        for (int i = 0; i < numberOfSheets; i++) {
            XSSFSheet sheet = workBook.getSheetAt(i);
            int lastRowNum = sheet.getLastRowNum();
            log.info("=== XXX | 第{}页 | 总共{}行 ===", (i + 1), (lastRowNum + 1));
            for (int j = 0; j <= lastRowNum; j++) {
                XSSFRow row = sheet.getRow(j);
                if (physicalNumberOfCells == null) {
                    physicalNumberOfCells = row.getPhysicalNumberOfCells();
                }
                log.info("=== XXX | 第{}页第{}行 | 总共{}列 ===", (i + 1), (lastRowNum + 1), physicalNumberOfCells);
                for (int k = 0; k < physicalNumberOfCells; k++) {
                    XSSFCell cell = row.getCell(k);
                    String cellValue = cell != null ? cell.toString() : null;
                    log.info("=== XXX | 第{}页第{}行第{}列 | 值: {} ===", (i + 1), (j + 1), (k + 1), cellValue);
                }
            }
        }
    }

    private void testEasyExcelRead() throws Exception {
        List<Object> objects = EasyExcelUtils.readFromDisk(filePath1, 1, 1, null, CollectionRecord.class);
        log.info("=== XXX | 读取成功 | 行数: {} ===", objects.size());
        List<CollectionRecord> records = JSONObject.parseArray(JSONObject.toJSONString(objects), CollectionRecord.class);
        log.info("=== XXX | 读取记录 | 记录: {} ===", records);
    }
}
