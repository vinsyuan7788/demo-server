package com.demo.spring.boot.application.array;

import com.demo.spring.boot.web.DemoSpringBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author Vince Yuan
 * @date 03/12/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoSpringBootApplication.class)
public class TestArray {

    @Test
    public void test() {
        Integer[] intArray = new Integer[] { 1, 2, 3, 4, 5 };
        testNewArrayOfGenericType(intArray);
        testCopyArrayOfGenericType(intArray);
        String[] strArray = new String[] { "Hello", "JVM", "SpringBoot", "Flink", "Demo" };
        testNewArrayOfGenericType(strArray);
        testCopyArrayOfGenericType(strArray);
    }

    private <T> void testNewArrayOfGenericType(T[] inputArray) {
        T[] outputArray = (T[]) Array.newInstance(inputArray.getClass().getComponentType(), inputArray.length);
        System.out.println("New Array of Generic Type | size: " + outputArray.length + " | content: " + Arrays.asList(outputArray));
    }

    private <T> void testCopyArrayOfGenericType(T[] inputArray) {
        T[] outputArray = Arrays.copyOf(inputArray, inputArray.length);
        System.out.println("Copy Array of Generic Type | size: " + outputArray.length + " | content: " + Arrays.asList(outputArray));
    }
}
