package com.demo.server.business;

import com.demo.server.web.DemoServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Vince Yuan
 * @date 03/02/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoServerApplication.class)
public class TestBusiness {

    @Test
    public void test() {
        testBusiness();
    }

    private void testBusiness() {
        System.out.println("=== Hello Business! ===");
    }
}
