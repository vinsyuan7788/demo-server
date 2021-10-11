package com.demo.server.application.time;

import com.demo.server.web.DemoServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Vince Yuan
 * @date 09/09/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoServerApplication.class)
public class TestTime {

    @Test
    public void testTime() {
        testTimeElapse();
    }

    private void testTimeElapse() {
        long n1 = 1999;
        long n2 = 1555L;
        long dividend = 1000L;
        long currentDateSeconds = n1 / dividend;
        long visitStartTimeSeconds = n2 / dividend;
        long result = currentDateSeconds - visitStartTimeSeconds;
        System.out.println("result: " + result);
    }
}
