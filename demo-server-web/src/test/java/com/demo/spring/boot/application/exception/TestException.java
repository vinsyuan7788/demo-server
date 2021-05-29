package com.demo.spring.boot.application.exception;

import com.demo.spring.boot.web.DemoSpringBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Vince Yuan
 * @date 04/26/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoSpringBootApplication.class)
public class TestException {

    @Test
    public void test() {
        testException();
    }

    private void testException() {
        log.info("=== testException | Execution starts ===");
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            log.error("=== testException | Exception is found ===", e);
        }
        log.info("=== testException | Execution ends ===");
    }
}
