package com.demo.spring.boot.application.clazz;

import com.demo.spring.boot.application.list.stream.model.Student;
import com.demo.spring.boot.web.DemoSpringBootApplication;
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
@SpringBootTest(classes = DemoSpringBootApplication.class)
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
