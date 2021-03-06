package com.demo.spring.boot.theory;

import com.demo.spring.boot.biz.theory.algorithm.fundamental.SortService;
import com.demo.spring.boot.biz.theory.structure.data.array.utils.enums.SortObjectEnum;
import com.demo.spring.boot.web.DemoSpringBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @author Vince Yuan
 * @date 03/17/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoSpringBootApplication.class)
public class TestAlgorithm {

    @Autowired
    private SortService sortService;

    @Test
    public void test() {
        Integer[] array = { 13, 2, 35, 4, 57, 6, 79 };
        Integer[] sortedArray = sortService.radixSortInArray(array, 0, array.length - 1, true, true, SortObjectEnum.NUMBER);
        System.out.println(Arrays.asList(sortedArray));
    }
}
