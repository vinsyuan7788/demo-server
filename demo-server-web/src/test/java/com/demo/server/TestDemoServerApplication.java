package com.demo.server;

import com.demo.server.web.DemoServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoServerApplication.class)
public class TestDemoServerApplication {

    @Test
    public void test() {
        testDemoServerApplication();
    }

    private void testDemoServerApplication() {
        System.out.println("=== Hello Demo Spring Boot Application! ===");
    }
}
