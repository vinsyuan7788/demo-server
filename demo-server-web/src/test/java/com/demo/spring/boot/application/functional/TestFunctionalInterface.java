package com.demo.spring.boot.application.functional;

import com.demo.spring.boot.web.DemoSpringBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Vince Yuan
 * @date 04/25/2021
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoSpringBootApplication.class)
public class TestFunctionalInterface {

    @Test
    public void test() {
        System.out.println("testFunction:");
        testFunction();
        System.out.println("\ntestConsumer:");
        testConsumer();
        System.out.println("\ntestSupplier:");
        testSupplier();
        System.out.println("\ntestPredicate:");
        testPredicate();
    }

    /**
     *  One input, ont output
     */
    private void testFunction() {
        // Build test data
        Function<? super List<Long>, ? extends Stream<Long>> function1 = (longList) -> {
            Stream<Long> longStream = longList.stream();
            return longStream;
        };
        // Perform test
        Stream<Long> longStream = function1.apply(Arrays.asList(1L, 2L, 3L, 4L, 5L));
        List<Long> longList = longStream.collect(Collectors.toList());
        System.out.println(longList);
    }

    /**
     *  One input, no output
     */
    private void testConsumer() {
        // Build test data
        Consumer<? super List<Long>> consumer1 = (longList) -> {
            System.out.println(longList);
        };
        // Perform test
        consumer1.accept(Arrays.asList(1L, 2L, 3L, 4L, 5L));
    }

    /**
     *  No input, one output
     */
    private void testSupplier() {
        // Build test data
        Supplier<? extends List<Long>> supplier1 = () -> Arrays.asList(1L, 2L, 3L, 4L, 5L);
        // Perform test
        List<Long> longList = supplier1.get();
        System.out.println(longList);
    }

    /**
     *  One input, one boolean output (MUST be boolean-type)
     */
    private void testPredicate() {
        // Build test data
        Predicate<? super List<Long>> predicate1 = (longList) -> CollectionUtils.isNotEmpty(longList);
        // Perform test
        boolean trueOrFalse = predicate1.test(Arrays.asList(1L, 2L, 3L, 4L, 5L));
        System.out.println(trueOrFalse);
    }
}
