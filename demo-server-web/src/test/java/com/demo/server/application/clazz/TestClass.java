package com.demo.server.application.clazz;

import com.demo.server.application.list.stream.model.Student;
import com.demo.server.web.DemoServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Vince Yuan
 * @date 05/15/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoServerApplication.class)
public class TestClass {

    @Test
    public void test() {
        testClass();
    }

    private void testClass() {
        Class clazz = Student.class;
        log.info("=== test | 是否是相同的类: {} ===", clazz.equals(Student.class));
    }
}
