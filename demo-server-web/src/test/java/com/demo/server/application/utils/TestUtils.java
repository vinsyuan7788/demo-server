package com.demo.server.application.utils;

import com.demo.server.web.DemoServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoServerApplication.class)
public class TestUtils {

    @Test
    public void test() throws Exception {
        testMatchUtils();
    }

    private void testMatchUtils() throws Exception {
        MatchUtils.findLinesContainedInBothFiles("C:\\Users\\yuanxu\\Desktop\\text1.txt",
                "C:\\Users\\yuanxu\\Desktop\\text2.txt",
                "C:\\Users\\yuanxu\\Desktop\\output.txt");
    }
}
