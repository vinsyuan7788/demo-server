package com.demo.server.application.string;

import com.demo.server.web.DemoServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Vince Yuan
 * @date 01/12/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoServerApplication.class)
public class TestString {

    @Test
    public void test() {
        System.out.println("testGetChinese:");
        testGetChinese();
        System.out.println("\ntestGetByteLength:");
        testGetByteLength();
        System.out.println("\ntestLongToString:");
        testLongToString();
    }

    private void testLongToString() {
        Long longNum = 12345L;
        String str = "0" + longNum;
        System.out.println(longNum + " | " + str);
    }

    private void testGetChinese() {
        String words = "已婚已育123ABC有房有车";
        String chinese = getChinese(words);
        System.out.println(chinese);
    }

    private void testGetByteLength() {
        String words = "快使用双截棍哈哈";
        int byteLength1 = words.getBytes().length;
        System.out.println(byteLength1);
    }

    /**
     *  获取字符串中的所有汉字
     *
     * @param str
     * @return
     */
    private String getChinese(String str) {
        String chineseRegex = "[^\u4e00-\u9fa5]";
        str = str.replaceAll(chineseRegex, "");
        return str;
    }
}
