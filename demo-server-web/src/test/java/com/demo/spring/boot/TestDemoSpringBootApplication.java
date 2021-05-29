package com.demo.spring.boot;

import com.demo.spring.boot.web.DemoSpringBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoSpringBootApplication.class)
public class TestDemoSpringBootApplication {

    @Test
    public void test() {
        testDemoSpringBootApplication();
    }

    private void testDemoSpringBootApplication() {
        System.out.println("=== Hello Demo Spring Boot Application! ===");
    }
}
