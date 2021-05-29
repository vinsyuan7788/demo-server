package com.demo.spring.boot.application.calendar;

import com.demo.spring.boot.web.DemoSpringBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Vince Yuan
 * @date 01/08/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoSpringBootApplication.class)
public class TestCalendar {

    private static final DateFormat DATE_FORMAT_1 = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void test() throws Exception {
        testParseDate();
//        testAddMonth();
    }

    private void testParseDate() {
        try {
            Date date = DATE_FORMAT_1.parse("2021-01-01");
            log.info("=== XXX | 日期: {} ===", date);
        } catch (Exception e) {
            log.error("=== XXX | 出现异常 ===", e);
        }
    }

    private void testAddMonth() throws Exception {

        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-06-01 00:00:00");
        int monthAmount = -6;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, monthAmount);

        Date newDate = calendar.getTime();
        int month = calendar.get(2) + 1;
        System.out.println(newDate + " | " + month);
    }
}
