package com.demo.server.application.enums;

import com.demo.server.application.enums.enums.SeasonEnum;
import com.demo.server.web.DemoServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Vince Yuan
 * @date 02/05/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoServerApplication.class)
public class TestEnum {

    @Test
    public void test() {
        testSwitchCase();
    }

    private void testSwitchCase() {
        // Build test data
        String code = "spring";

        // Perform test data
        SeasonEnum seasonEnum = SeasonEnum.find(code);
        switch (seasonEnum) {
            case SPRING:
                System.out.println("Here comes " + seasonEnum.getCode() + "!");
                break;
            case SUMMER:
                System.out.println("Here comes " + seasonEnum.getCode() + "!");
                break;
            case FALL:
                System.out.println("Here comes " + seasonEnum.getCode() + "!");
                break;
            case WINTER:
                System.out.println("Here comes " + seasonEnum.getCode() + "!");
                break;
            default:
                System.out.println("Here comes nothing!");
                break;
        }
    }
}
