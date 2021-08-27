package com.demo.server.theory;

import com.demo.server.biz.theory.algorithm.fundamental.SortService;
import com.demo.server.biz.theory.structure.data.array.utils.enums.SortedElementTypeEnum;
import com.demo.server.web.DemoServerApplication;
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
@SpringBootTest(classes = DemoServerApplication.class)
public class TestAlgorithm {

    @Autowired
    private SortService sortService;

    @Test
    public void test() {
        Integer[] array = { 13, 2, 35, 4, 57, 6, 79 };
        Integer[] sortedArray = sortService.radixSortInArray(array, 0, array.length - 1, true, true, SortedElementTypeEnum.NUMBER);
        System.out.println(Arrays.asList(sortedArray));
    }
}
