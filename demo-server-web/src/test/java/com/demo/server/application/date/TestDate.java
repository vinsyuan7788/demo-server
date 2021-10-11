package com.demo.server.application.date;

import com.demo.server.web.DemoServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Vince Yuan
 * @date 09/16/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoServerApplication.class)
public class TestDate {

    private static final DateFormat DATE_FORMAT_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static final DateFormat DATE_FORMAT_2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final DateFormat DATE_FORMAT_3 = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void test() throws Exception {
        testTruncateMillisPart();
        testWithinTheSameDay();
    }

    private void testTruncateMillisPart() throws Exception {
        Date date = new Date();
        System.out.println("old date: " + DATE_FORMAT_1.format(date));

        Date newDate = DATE_FORMAT_2.parse(DATE_FORMAT_2.format(date));
        System.out.println("new date: " + DATE_FORMAT_1.format(newDate));
    }

    private void testWithinTheSameDay() throws Exception {
        Date date1 = DATE_FORMAT_1.parse("2021-10-02 00:00:01.000");
        Date date2 = DATE_FORMAT_1.parse("2021-10-02 23:59:59.999");

        String date1String = DATE_FORMAT_3.format(date1);
        String date2String = DATE_FORMAT_3.format(date2);
        System.out.println("in the same day: " + date1String.equals(date2String));
    }
}
