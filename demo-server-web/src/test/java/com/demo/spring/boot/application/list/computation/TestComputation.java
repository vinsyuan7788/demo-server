package com.demo.spring.boot.application.list.computation;

import com.demo.spring.boot.web.DemoSpringBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.ListUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author Vince Yuan
 * @date 01/13/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoSpringBootApplication.class)
public class TestComputation {

    @Test
    public void test() {
        System.out.println("testNumberListIntersection:");
        testNumberListIntersection();
        System.out.println("\ntestStringListIntersection:");
        testStringListIntersection();
    }

    private void testNumberListIntersection() {
        List<Long> list1 = Arrays.asList(1L, 2L, 3L, 4L, 5L);
//        List<Long> list2 = Arrays.asList(3L, 4L, 5L, 6L, 7L);
        List<Long> list2 = Arrays.asList(3L, 4L, 5L);
        System.out.println("list1: " + list1);
        System.out.println("list2: " + list2);
        List<Long> intersection = ListUtils.intersection(list1, list2);
        System.out.println("intersection: " + intersection);
        List<Long> subtract1 = ListUtils.subtract(list1, intersection);
        List<Long> subtract2 = ListUtils.subtract(list2, intersection);
        System.out.println("subtract1: " + subtract1);
        System.out.println("subtract2: " + subtract2);
    }

    private void testStringListIntersection() {
        List<String> list1 = Arrays.asList("123已婚已育ABC", "ABC未婚未育123", "Hello爪哇", "你好China", "偶买噶balabala");
//        List<Long> list2 = Arrays.asList("Hello爪哇", "你好China", "偶买噶balabala", "测试字符串", "没想到吧HAHAHAHA");
        List<String> list2 = Arrays.asList("ABC未婚未育123", "Hello爪哇", "你好China");
        System.out.println("list1: " + list1);
        System.out.println("list2: " + list2);
        List<String> intersection = ListUtils.intersection(list1, list2);
        System.out.println("intersection: " + intersection);
        List<String> subtract1 = ListUtils.subtract(list1, intersection);
        List<String> subtract2 = ListUtils.subtract(list2, intersection);
        System.out.println("subtract1: " + subtract1);
        System.out.println("subtract2: " + subtract2);
    }
}
