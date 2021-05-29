package com.demo.spring.boot.application.utils;

import com.demo.spring.boot.web.DemoSpringBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoSpringBootApplication.class)
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
