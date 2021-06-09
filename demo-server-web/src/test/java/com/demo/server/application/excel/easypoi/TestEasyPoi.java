package com.demo.server.application.excel.easypoi;

import com.demo.server.web.DemoServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Vince Yuan
 * @date 06/08/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoServerApplication.class)
public class TestEasyPoi {

    private String filePath1 = "C:/Users/yuanxu/Desktop/outsource_collection_record_example_002.xlsx";

    @Test
    public void test() throws Exception {
        testEasyPoiWrite();
    }

    private void testEasyPoiWrite() throws Exception {

    }
}
