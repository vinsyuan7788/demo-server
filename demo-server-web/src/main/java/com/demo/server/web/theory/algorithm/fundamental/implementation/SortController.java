package com.demo.server.web.theory.algorithm.fundamental.implementation;

import com.demo.base.common.response.bean.CommonResponse;
import com.demo.base.common.response.enums.ResponseEnum;
import com.demo.base.common.utils.LogUtils;
import com.demo.base.common.utils.bean.ParametersToLog;
import com.demo.server.biz.theory.algorithm.fundamental.SortService;
import com.demo.server.biz.theory.structure.data.array.utils.enums.SortedElementTypeEnum;
import com.demo.server.biz.theory.structure.data.array.utils.model.Student;
import com.demo.server.common.constant.CommonConstant;
import com.demo.server.common.utils.SystemUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Vince Yuan
 * @date 03/10/2021
 */
@Api(value = "原理-基础算法-实现-排序", tags = "原理-基础算法-实现-排序")
@Slf4j
@RestController
@RequestMapping("/algorithm/fundamental/implementation/sort")
public class SortController {

    @Autowired
    private SortService sortService;

    private final Integer[] originalArray1 = new Integer[] { 1, -5, 8, -3, 6, -5, 2 };
    private final Long[] originalArray2 = new Long[] { 10L, -50L, 80L, -30L, 60L, -50L, 20L };
    private final Double[] originalArray3 = new Double[] { 10.24d, -50.78d, 82.43d, -38.47d, 61.98d, -50.78d, 23.65d };
    private final String[] originalArray4 = new String[] { "Hello", "Hi", "Howdy", "Hey", "What's Up" };
    private final Student[] originalArray5 = new Student[] {
            Student.builder().id(1L).studentName("Tom").studentAge(34).build(),
            Student.builder().id(2L).studentName("Jerry").studentAge(35).build(),
            Student.builder().id(3L).studentName("Vince").studentAge(33).build(),
            Student.builder().id(4L).studentName("Violet").studentAge(32).build(),
            Student.builder().id(5L).studentName("Kevin").studentAge(34).build(),
            Student.builder().id(6L).studentName("Michael").studentAge(31).build()
    };
    private final Student[] originalArray6 = new Student[] {
            Student.builder().id(1L).studentName("Tom").studentAge(34).build(),
            Student.builder().id(2L).studentName("Jerry").studentAge(35).build(),
            Student.builder().id(3L).studentName("Vince").studentAge(33).build(),
            Student.builder().id(4L).studentName("Violet").studentAge(32).build(),
            Student.builder().id(5L).studentName("Kevin").studentAge(34).build(),
            Student.builder().id(6L).studentName("Michael").studentAge(31).build()
    };
    private final Student[] originalArray7 = new Student[] {
            Student.builder().id(1L).studentName("Tom").studentAge(35).build(),
            Student.builder().id(2L).studentName("Jerry").studentAge(36).build(),
            Student.builder().id(3L).studentName("Vince").studentAge(35).build(),
            Student.builder().id(4L).studentName("Violet").studentAge(35).build(),
            Student.builder().id(5L).studentName("Kevin").studentAge(36).build(),
            Student.builder().id(6L).studentName("Michael").studentAge(36).build()
    };
    private final Student[] originalArray8 = new Student[] {
            Student.builder().id(1L).studentName("Tom").studentAge(35).build(),
            Student.builder().id(2L).studentName("Jerry").studentAge(36).build(),
            Student.builder().id(3L).studentName("Vince").studentAge(35).build(),
            Student.builder().id(4L).studentName("Violet").studentAge(35).build(),
            Student.builder().id(5L).studentName("Kevin").studentAge(36).build(),
            Student.builder().id(6L).studentName("Michael").studentAge(36).build()
    };
    private final Random random = new Random();
    private final int dataAmount = CommonConstant.ONE_THOUSAND;

    @ApiOperation(value = "数组-蒂姆排序", notes = "数组-蒂姆排序")
    @PostMapping("/timSortInArray")
        public CommonResponse timSortInArray() {
        // Sort the sample arrays
        Integer[] sortedArray1 = sortService.timSortInArray(originalArray1, 0, originalArray1.length - 1, true);
        Long[] sortedArray2 = sortService.timSortInArray(originalArray2, 0, originalArray2.length - 1, false);
        Double[] sortedArray3 = sortService.timSortInArray(originalArray3, 0, originalArray3.length - 1, true);
        String[] sortedArray4 = sortService.timSortInArray(originalArray4, 0, originalArray4.length - 1, false);
        log.info(LogUtils.getLogMessage("timSortInArray", "Sort the sample arrays"));
        log.info(LogUtils.getLogMessage("timSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray1)).addParameter("Asc-sorted array", Arrays.asList(sortedArray1))));
        log.info(LogUtils.getLogMessage("timSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray2)).addParameter("Desc-sorted array", Arrays.asList(sortedArray2))));
        log.info(LogUtils.getLogMessage("timSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray3)).addParameter("Asc-sorted array", Arrays.asList(sortedArray3))));
        log.info(LogUtils.getLogMessage("timSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray4)).addParameter("Desc-sorted array", Arrays.asList(sortedArray4))));
        // Sort to test stability
        Student[] originalArray5 = Arrays.copyOf(this.originalArray5, this.originalArray5.length);
        Student[] originalArray6 = Arrays.copyOf(this.originalArray6, this.originalArray6.length);
        Student[] originalArray7 = Arrays.copyOf(this.originalArray7, this.originalArray7.length);
        Student[] originalArray8 = Arrays.copyOf(this.originalArray8, this.originalArray8.length);
        Student[] sortedArray5 = sortService.timSortInArray(originalArray5, 0, originalArray5.length - 1, true);
        Student[] sortedArray6 = sortService.timSortInArray(originalArray6, 0, originalArray6.length - 1, false);
        Student[] sortedArray7 = sortService.timSortInArray(originalArray7, 0, originalArray7.length - 1, true);
        Student[] sortedArray8 = sortService.timSortInArray(originalArray8, 0, originalArray8.length - 1, false);
        log.info(LogUtils.getLogMessage("timSortInArray", "Sort to test stability"));
        log.info(LogUtils.getLogMessage("timSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray5)).addParameter("Asc-sorted array", Arrays.asList(sortedArray5))));
        log.info(LogUtils.getLogMessage("timSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray6)).addParameter("Desc-sorted array", Arrays.asList(sortedArray6))));
        log.info(LogUtils.getLogMessage("timSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray7)).addParameter("Asc-sorted array", Arrays.asList(sortedArray7))));
        log.info(LogUtils.getLogMessage("timSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray8)).addParameter("Desc-sorted array", Arrays.asList(sortedArray8))));
        // Sort huge amount of data
        Integer[] originalHugeArray1 = getHugeAmountOfIntegers();
        long timeMillisBeforeSort = SystemUtils.currentTimeMillis();
        Integer[] sortedHugeArray1 = sortService.timSortInArray(originalHugeArray1, 0, originalHugeArray1.length - 1, true);
        long timeMillisAfterSort = SystemUtils.currentTimeMillis();
        log.info(LogUtils.getLogMessage("timSortInArray", "Sort huge amount of data"));
        log.info(LogUtils.getLogMessage("timSortInArray", new ParametersToLog().addParameter("Time cost", SystemUtils.elapsedTimeSeconds(timeMillisBeforeSort, timeMillisAfterSort)).addParameter("Sorted elements", sortedHugeArray1.length)));
        // Return result
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Arrays.asList(
                        "Sort the sample arrays:",
                        "Original array: " + Arrays.asList(this.originalArray1) + " | Asc-sorted array: " + Arrays.asList(sortedArray1),
                        "Original array: " + Arrays.asList(this.originalArray2) + " | Desc-sorted array: " + Arrays.asList(sortedArray2),
                        "Original array: " + Arrays.asList(this.originalArray3) + " | Asc-sorted array: " + Arrays.asList(sortedArray3),
                        "Original array: " + Arrays.asList(this.originalArray4) + " | Desc-sorted array: " + Arrays.asList(sortedArray4),
                        "Sort to test stability:",
                        "Original array: " + Arrays.asList(this.originalArray5) + " | Asc-sorted array: " + Arrays.asList(sortedArray5),
                        "Original array: " + Arrays.asList(this.originalArray6) + " | Desc-sorted array: " + Arrays.asList(sortedArray6),
                        "Original array: " + Arrays.asList(this.originalArray7) + " | Asc-sorted array: " + Arrays.asList(sortedArray7),
                        "Original array: " + Arrays.asList(this.originalArray8) + " | Desc-sorted array: " + Arrays.asList(sortedArray8),
                        "Sort huge amount of data:",
                        "Time cost: " + SystemUtils.elapsedTimeSeconds(timeMillisBeforeSort, timeMillisAfterSort) + " | sorted elements: " + sortedHugeArray1.length
                ))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "数组-基数排序", notes = "数组-基数排序")
    @PostMapping("/radixSortInArray")
    public CommonResponse radixSortInArray() {
        // Sort the sample arrays
        Integer[] sortedArray1 = sortService.radixSortInArray(originalArray1, 0, originalArray1.length - 1, true, true, SortedElementTypeEnum.NUMBER);
        Long[] sortedArray2 = sortService.radixSortInArray(originalArray2, 0, originalArray2.length - 1, false, false, SortedElementTypeEnum.NUMBER);
        Double[] sortedArray3 = sortService.radixSortInArray(originalArray3, 0, originalArray3.length - 1, true, false, SortedElementTypeEnum.NUMBER);
        String[] sortedArray4 = sortService.radixSortInArray(originalArray4, 0, originalArray4.length - 1, false, true, SortedElementTypeEnum.STRING);
        log.info(LogUtils.getLogMessage("radixSortInArray", "Sort the sample arrays"));
        log.info(LogUtils.getLogMessage("radixSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray1)).addParameter("Asc-sorted array", Arrays.asList(sortedArray1))));
        log.info(LogUtils.getLogMessage("radixSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray2)).addParameter("Desc-sorted array", Arrays.asList(sortedArray2))));
        log.info(LogUtils.getLogMessage("radixSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray3)).addParameter("Asc-sorted array", Arrays.asList(sortedArray3))));
        log.info(LogUtils.getLogMessage("radixSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray4)).addParameter("Desc-sorted array", Arrays.asList(sortedArray4))));
        // Sort to test stability
        Student[] originalArray5 = Arrays.copyOf(this.originalArray5, this.originalArray5.length);
        Student[] originalArray6 = Arrays.copyOf(this.originalArray6, this.originalArray6.length);
        Student[] originalArray7 = Arrays.copyOf(this.originalArray7, this.originalArray7.length);
        Student[] originalArray8 = Arrays.copyOf(this.originalArray8, this.originalArray8.length);
        Student[] sortedArray5 = sortService.radixSortInArray(originalArray5, 0, originalArray5.length - 1, true, true, SortedElementTypeEnum.NUMBER);
        Student[] sortedArray6 = sortService.radixSortInArray(originalArray6, 0, originalArray6.length - 1, false, true, SortedElementTypeEnum.NUMBER);
        Student[] sortedArray7 = sortService.radixSortInArray(originalArray7, 0, originalArray7.length - 1, true, false, SortedElementTypeEnum.NUMBER);
        Student[] sortedArray8 = sortService.radixSortInArray(originalArray8, 0, originalArray8.length - 1, false, false, SortedElementTypeEnum.STRING);
        log.info(LogUtils.getLogMessage("radixSortInArray", "Sort to test stability"));
        log.info(LogUtils.getLogMessage("radixSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray5)).addParameter("Asc-sorted array", Arrays.asList(sortedArray5))));
        log.info(LogUtils.getLogMessage("radixSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray6)).addParameter("Desc-sorted array", Arrays.asList(sortedArray6))));
        log.info(LogUtils.getLogMessage("radixSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray7)).addParameter("Asc-sorted array", Arrays.asList(sortedArray7))));
        log.info(LogUtils.getLogMessage("radixSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray8)).addParameter("Desc-sorted array", Arrays.asList(sortedArray8))));
        // Sort huge amount of data
        Integer[] originalHugeArray1 = getHugeAmountOfIntegers();
        long timeMillisBeforeSort = SystemUtils.currentTimeMillis();
        Integer[] sortedHugeArray1 = sortService.radixSortInArray(originalHugeArray1, 0, originalHugeArray1.length - 1, true, true, SortedElementTypeEnum.NUMBER);
        long timeMillisAfterSort = SystemUtils.currentTimeMillis();
        log.info(LogUtils.getLogMessage("radixSortInArray", "Sort huge amount of data"));
        log.info(LogUtils.getLogMessage("radixSortInArray", new ParametersToLog().addParameter("Time cost", SystemUtils.elapsedTimeSeconds(timeMillisBeforeSort, timeMillisAfterSort)).addParameter("Sorted elements", sortedHugeArray1.length)));
        // Return result
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Arrays.asList(
                        "Sort the sample arrays:",
                        "Original array: " + Arrays.asList(this.originalArray1) + " | Asc-sorted array: " + Arrays.asList(sortedArray1),
                        "Original array: " + Arrays.asList(this.originalArray2) + " | Desc-sorted array: " + Arrays.asList(sortedArray2),
                        "Original array: " + Arrays.asList(this.originalArray3) + " | Asc-sorted array: " + Arrays.asList(sortedArray3),
                        "Original array: " + Arrays.asList(this.originalArray4) + " | Desc-sorted array: " + Arrays.asList(sortedArray4),
                        "Sort to test stability:",
                        "Original array: " + Arrays.asList(this.originalArray5) + " | Asc-sorted array: " + Arrays.asList(sortedArray5),
                        "Original array: " + Arrays.asList(this.originalArray6) + " | Desc-sorted array: " + Arrays.asList(sortedArray6),
                        "Original array: " + Arrays.asList(this.originalArray7) + " | Asc-sorted array: " + Arrays.asList(sortedArray7),
                        "Original array: " + Arrays.asList(this.originalArray8) + " | Desc-sorted array: " + Arrays.asList(sortedArray8),
                        "Sort huge amount of data:",
                        "Time cost: " + SystemUtils.elapsedTimeSeconds(timeMillisBeforeSort, timeMillisAfterSort) + " | sorted elements: " + sortedHugeArray1.length
                ))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "数组-堆排序", notes = "数组-堆排序")
    @PostMapping("/heapSortInArray")
    public CommonResponse heapSortInArray() {
        // Sort the sample arrays
        Integer[] originalArray1 = Arrays.copyOf(this.originalArray1, this.originalArray1.length);
        Long[] originalArray2 = Arrays.copyOf(this.originalArray2, this.originalArray2.length);
        Double[] originalArray3 = Arrays.copyOf(this.originalArray3, this.originalArray3.length);
        String[] originalArray4 = Arrays.copyOf(this.originalArray4, this.originalArray4.length);
        Integer[] sortedArray1 = sortService.heapSortInArray(originalArray1, 0, originalArray1.length - 1, true, true);
        Long[] sortedArray2 = sortService.heapSortInArray(originalArray2, 0, originalArray2.length - 1, false, false);
        Double[] sortedArray3 = sortService.heapSortInArray(originalArray3, 0, originalArray3.length - 1, true, false);
        String[] sortedArray4 = sortService.heapSortInArray(originalArray4, 0, originalArray4.length - 1, false, true);
        log.info(LogUtils.getLogMessage("heapSortInArray", "Sort the sample arrays"));
        log.info(LogUtils.getLogMessage("heapSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray1)).addParameter("Asc-sorted array", Arrays.asList(sortedArray1))));
        log.info(LogUtils.getLogMessage("heapSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray2)).addParameter("Desc-sorted array", Arrays.asList(sortedArray2))));
        log.info(LogUtils.getLogMessage("heapSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray3)).addParameter("Asc-sorted array", Arrays.asList(sortedArray3))));
        log.info(LogUtils.getLogMessage("heapSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray4)).addParameter("Desc-sorted array", Arrays.asList(sortedArray4))));
        // Sort to test stability
        Student[] originalArray5 = Arrays.copyOf(this.originalArray5, this.originalArray5.length);
        Student[] originalArray6 = Arrays.copyOf(this.originalArray6, this.originalArray6.length);
        Student[] originalArray7 = Arrays.copyOf(this.originalArray7, this.originalArray7.length);
        Student[] originalArray8 = Arrays.copyOf(this.originalArray8, this.originalArray8.length);
        Student[] sortedArray5 = sortService.heapSortInArray(originalArray5, 0, originalArray5.length - 1, true, true);
        Student[] sortedArray6 = sortService.heapSortInArray(originalArray6, 0, originalArray6.length - 1, false, true);
        Student[] sortedArray7 = sortService.heapSortInArray(originalArray7, 0, originalArray7.length - 1, true, false);
        Student[] sortedArray8 = sortService.heapSortInArray(originalArray8, 0, originalArray8.length - 1, false, false);
        log.info(LogUtils.getLogMessage("heapSortInArray", "Sort to test stability"));
        log.info(LogUtils.getLogMessage("heapSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray5)).addParameter("Asc-sorted array", Arrays.asList(sortedArray5))));
        log.info(LogUtils.getLogMessage("heapSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray6)).addParameter("Desc-sorted array", Arrays.asList(sortedArray6))));
        log.info(LogUtils.getLogMessage("heapSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray7)).addParameter("Asc-sorted array", Arrays.asList(sortedArray7))));
        log.info(LogUtils.getLogMessage("heapSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray8)).addParameter("Desc-sorted array", Arrays.asList(sortedArray8))));
        // Sort huge amount of data
        Integer[] originalHugeArray1 = getHugeAmountOfIntegers();
        long timeMillisBeforeSort = SystemUtils.currentTimeMillis();
        Integer[] sortedHugeArray1 = sortService.heapSortInArray(originalHugeArray1, 0, originalHugeArray1.length - 1, true, true);
        long timeMillisAfterSort = SystemUtils.currentTimeMillis();
        log.info(LogUtils.getLogMessage("heapSortInArray", "Sort huge amount of data"));
        log.info(LogUtils.getLogMessage("heapSortInArray", new ParametersToLog().addParameter("Time cost", SystemUtils.elapsedTimeSeconds(timeMillisBeforeSort, timeMillisAfterSort)).addParameter("Sorted elements", sortedHugeArray1.length)));
        // Return result
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Arrays.asList(
                        "Sort the sample arrays:",
                        "Original array: " + Arrays.asList(this.originalArray1) + " | Asc-sorted array: " + Arrays.asList(sortedArray1),
                        "Original array: " + Arrays.asList(this.originalArray2) + " | Desc-sorted array: " + Arrays.asList(sortedArray2),
                        "Original array: " + Arrays.asList(this.originalArray3) + " | Asc-sorted array: " + Arrays.asList(sortedArray3),
                        "Original array: " + Arrays.asList(this.originalArray4) + " | Desc-sorted array: " + Arrays.asList(sortedArray4),
                        "Sort to test stability:",
                        "Original array: " + Arrays.asList(this.originalArray5) + " | Asc-sorted array: " + Arrays.asList(sortedArray5),
                        "Original array: " + Arrays.asList(this.originalArray6) + " | Desc-sorted array: " + Arrays.asList(sortedArray6),
                        "Original array: " + Arrays.asList(this.originalArray7) + " | Asc-sorted array: " + Arrays.asList(sortedArray7),
                        "Original array: " + Arrays.asList(this.originalArray8) + " | Desc-sorted array: " + Arrays.asList(sortedArray8),
                        "Sort huge amount of data:",
                        "Time cost: " + SystemUtils.elapsedTimeSeconds(timeMillisBeforeSort, timeMillisAfterSort) + " | sorted elements: " + sortedHugeArray1.length
                ))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "数组-归并排序", notes = "数组-归并排序")
    @PostMapping("/mergeSortInArray")
    public CommonResponse mergeSortInArray() {
        // Sort the sample arrays
        Integer[] sortedArray1 = sortService.mergeSortInArray(originalArray1, 0, originalArray1.length - 1, true);
        Long[] sortedArray2 = sortService.mergeSortInArray(originalArray2, 0, originalArray2.length - 1, false);
        Double[] sortedArray3 = sortService.mergeSortInArray(originalArray3, 0, originalArray3.length - 1, true);
        String[] sortedArray4 = sortService.mergeSortInArray(originalArray4, 0, originalArray4.length - 1, false);
        log.info(LogUtils.getLogMessage("mergeSortInArray", "Sort the sample arrays"));
        log.info(LogUtils.getLogMessage("mergeSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray1)).addParameter("Asc-sorted array", Arrays.asList(sortedArray1))));
        log.info(LogUtils.getLogMessage("mergeSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray2)).addParameter("Desc-sorted array", Arrays.asList(sortedArray2))));
        log.info(LogUtils.getLogMessage("mergeSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray3)).addParameter("Asc-sorted array", Arrays.asList(sortedArray3))));
        log.info(LogUtils.getLogMessage("mergeSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray4)).addParameter("Desc-sorted array", Arrays.asList(sortedArray4))));
        // Sort to test stability
        Student[] sortedArray5 = sortService.mergeSortInArray(originalArray5, 0, originalArray5.length - 1, true);
        Student[] sortedArray6 = sortService.mergeSortInArray(originalArray6, 0, originalArray6.length - 1, false);
        Student[] sortedArray7 = sortService.mergeSortInArray(originalArray7, 0, originalArray7.length - 1, true);
        Student[] sortedArray8 = sortService.mergeSortInArray(originalArray8, 0, originalArray8.length - 1, false);
        log.info(LogUtils.getLogMessage("mergeSortInArray", "Sort to test stability"));
        log.info(LogUtils.getLogMessage("mergeSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray5)).addParameter("Asc-sorted array", Arrays.asList(sortedArray5))));
        log.info(LogUtils.getLogMessage("mergeSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray6)).addParameter("Desc-sorted array", Arrays.asList(sortedArray6))));
        log.info(LogUtils.getLogMessage("mergeSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray7)).addParameter("Asc-sorted array", Arrays.asList(sortedArray7))));
        log.info(LogUtils.getLogMessage("mergeSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray8)).addParameter("Desc-sorted array", Arrays.asList(sortedArray8))));
        // Sort huge amount of data
        Integer[] originalHugeArray1 = getHugeAmountOfIntegers();
        long timeMillisBeforeSort = SystemUtils.currentTimeMillis();
        Integer[] sortedHugeArray1 = sortService.mergeSortInArray(originalHugeArray1, 0, originalHugeArray1.length - 1, true);
        long timeMillisAfterSort = SystemUtils.currentTimeMillis();
        log.info(LogUtils.getLogMessage("mergeSortInArray", "Sort huge amount of data"));
        log.info(LogUtils.getLogMessage("mergeSortInArray", new ParametersToLog().addParameter("Time cost", SystemUtils.elapsedTimeSeconds(timeMillisBeforeSort, timeMillisAfterSort)).addParameter("Sorted elements", sortedHugeArray1.length)));
        // Return result
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Arrays.asList(
                        "Sort the sample arrays:",
                        "Original array: " + Arrays.asList(this.originalArray1) + " | Asc-sorted array: " + Arrays.asList(sortedArray1),
                        "Original array: " + Arrays.asList(this.originalArray2) + " | Desc-sorted array: " + Arrays.asList(sortedArray2),
                        "Original array: " + Arrays.asList(this.originalArray3) + " | Asc-sorted array: " + Arrays.asList(sortedArray3),
                        "Original array: " + Arrays.asList(this.originalArray4) + " | Desc-sorted array: " + Arrays.asList(sortedArray4),
                        "Sort to test stability:",
                        "Original array: " + Arrays.asList(this.originalArray5) + " | Asc-sorted array: " + Arrays.asList(sortedArray5),
                        "Original array: " + Arrays.asList(this.originalArray6) + " | Desc-sorted array: " + Arrays.asList(sortedArray6),
                        "Original array: " + Arrays.asList(this.originalArray7) + " | Asc-sorted array: " + Arrays.asList(sortedArray7),
                        "Original array: " + Arrays.asList(this.originalArray8) + " | Desc-sorted array: " + Arrays.asList(sortedArray8),
                        "Sort huge amount of data:",
                        "Time cost: " + SystemUtils.elapsedTimeSeconds(timeMillisBeforeSort, timeMillisAfterSort) + " | sorted elements: " + sortedHugeArray1.length
                ))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "数组-快速排序", notes = "数组-快速排序")
    @PostMapping("/quickSortInArray")
    public CommonResponse quickSortInArray() {
        // Sort the sample arrays
        Integer[] originalArray1 = Arrays.copyOf(this.originalArray1, this.originalArray1.length);
        Long[] originalArray2 = Arrays.copyOf(this.originalArray2, this.originalArray2.length);
        Double[] originalArray3 = Arrays.copyOf(this.originalArray3, this.originalArray3.length);
        String[] originalArray4 = Arrays.copyOf(this.originalArray4, this.originalArray4.length);
        Integer[] sortedArray1 = sortService.quickSortInArray(originalArray1, 0, originalArray1.length - 1, true, true);
        Long[] sortedArray2 = sortService.quickSortInArray(originalArray2, 0, originalArray2.length - 1, false, false);
        Double[] sortedArray3 = sortService.quickSortInArray(originalArray3, 0, originalArray3.length - 1, true, false);
        String[] sortedArray4 = sortService.quickSortInArray(originalArray4, 0, originalArray4.length - 1, false, true);
        log.info(LogUtils.getLogMessage("quickSortInArray", "Sort the sample arrays"));
        log.info(LogUtils.getLogMessage("quickSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray1)).addParameter("Asc-sorted array", Arrays.asList(sortedArray1))));
        log.info(LogUtils.getLogMessage("quickSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray2)).addParameter("Desc-sorted array", Arrays.asList(sortedArray2))));
        log.info(LogUtils.getLogMessage("quickSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray3)).addParameter("Asc-sorted array", Arrays.asList(sortedArray3))));
        log.info(LogUtils.getLogMessage("quickSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray4)).addParameter("Desc-sorted array", Arrays.asList(sortedArray4))));
        // Sort to test stability
        Student[] originalArray5 = Arrays.copyOf(this.originalArray5, this.originalArray5.length);
        Student[] originalArray6 = Arrays.copyOf(this.originalArray6, this.originalArray6.length);
        Student[] originalArray7 = Arrays.copyOf(this.originalArray7, this.originalArray7.length);
        Student[] originalArray8 = Arrays.copyOf(this.originalArray8, this.originalArray8.length);
        Student[] sortedArray5 = sortService.quickSortInArray(originalArray5, 0, originalArray5.length - 1, true, true);
        Student[] sortedArray6 = sortService.quickSortInArray(originalArray6, 0, originalArray6.length - 1, false, true);
        Student[] sortedArray7 = sortService.quickSortInArray(originalArray7, 0, originalArray7.length - 1, true, false);
        Student[] sortedArray8 = sortService.quickSortInArray(originalArray8, 0, originalArray8.length - 1, false, false);
        log.info(LogUtils.getLogMessage("quickSortInArray", "Sort to test stability"));
        log.info(LogUtils.getLogMessage("quickSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray5)).addParameter("Asc-sorted array", Arrays.asList(sortedArray5))));
        log.info(LogUtils.getLogMessage("quickSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray6)).addParameter("Desc-sorted array", Arrays.asList(sortedArray6))));
        log.info(LogUtils.getLogMessage("quickSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray7)).addParameter("Asc-sorted array", Arrays.asList(sortedArray7))));
        log.info(LogUtils.getLogMessage("quickSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray8)).addParameter("Desc-sorted array", Arrays.asList(sortedArray8))));
        // Sort huge amount of data
        Integer[] originalHugeArray1 = getHugeAmountOfIntegers();
        long timeMillisBeforeSort = SystemUtils.currentTimeMillis();
        Integer[] sortedHugeArray1 = sortService.quickSortInArray(originalHugeArray1, 0, originalHugeArray1.length - 1, true, true);
        long timeMillisAfterSort = SystemUtils.currentTimeMillis();
        log.info(LogUtils.getLogMessage("quickSortInArray", "Sort huge amount of data"));
        log.info(LogUtils.getLogMessage("quickSortInArray", new ParametersToLog().addParameter("Time cost", SystemUtils.elapsedTimeSeconds(timeMillisBeforeSort, timeMillisAfterSort)).addParameter("Sorted elements", sortedHugeArray1.length)));
        // Return result
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Arrays.asList(
                        "Sort the sample arrays:",
                        "Original array: " + Arrays.asList(this.originalArray1) + " | Asc-sorted array: " + Arrays.asList(sortedArray1),
                        "Original array: " + Arrays.asList(this.originalArray2) + " | Desc-sorted array: " + Arrays.asList(sortedArray2),
                        "Original array: " + Arrays.asList(this.originalArray3) + " | Asc-sorted array: " + Arrays.asList(sortedArray3),
                        "Original array: " + Arrays.asList(this.originalArray4) + " | Desc-sorted array: " + Arrays.asList(sortedArray4),
                        "Sort to test stability:",
                        "Original array: " + Arrays.asList(this.originalArray5) + " | Asc-sorted array: " + Arrays.asList(sortedArray5),
                        "Original array: " + Arrays.asList(this.originalArray6) + " | Desc-sorted array: " + Arrays.asList(sortedArray6),
                        "Original array: " + Arrays.asList(this.originalArray7) + " | Asc-sorted array: " + Arrays.asList(sortedArray7),
                        "Original array: " + Arrays.asList(this.originalArray8) + " | Desc-sorted array: " + Arrays.asList(sortedArray8),
                        "Sort huge amount of data:",
                        "Time cost: " + SystemUtils.elapsedTimeSeconds(timeMillisBeforeSort, timeMillisAfterSort) + " | sorted elements: " + sortedHugeArray1.length
                ))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "数组-插入排序-缩减增量分组优化", notes = "数组-希尔（或缩减增量）排序")
    @PostMapping("/insertSortWithDiminishingIncrementInArray")
    public CommonResponse insertSortWithDiminishingIncrementInArray() {
        // Sort the sample arrays
        Integer[] originalArray1 = Arrays.copyOf(this.originalArray1, this.originalArray1.length);
        Long[] originalArray2 = Arrays.copyOf(this.originalArray2, this.originalArray2.length);
        Double[] originalArray3 = Arrays.copyOf(this.originalArray3, this.originalArray3.length);
        String[] originalArray4 = Arrays.copyOf(this.originalArray4, this.originalArray4.length);
        Integer[] sortedArray1 = sortService.insertSortWithDiminishingIncrementInArray(originalArray1, 0, originalArray1.length - 1, true, true);
        Long[] sortedArray2 = sortService.insertSortWithDiminishingIncrementInArray(originalArray2, 0, originalArray2.length - 1, false, false);
        Double[] sortedArray3 = sortService.insertSortWithDiminishingIncrementInArray(originalArray3, 0, originalArray3.length - 1, true, false);
        String[] sortedArray4 = sortService.insertSortWithDiminishingIncrementInArray(originalArray4, 0, originalArray4.length - 1, false, true);
        log.info(LogUtils.getLogMessage("insertSortWithDiminishingIncrementInArray", "Sort the sample arrays"));
        log.info(LogUtils.getLogMessage("insertSortWithDiminishingIncrementInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray1)).addParameter("Asc-sorted array", Arrays.asList(sortedArray1))));
        log.info(LogUtils.getLogMessage("insertSortWithDiminishingIncrementInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray2)).addParameter("Desc-sorted array", Arrays.asList(sortedArray2))));
        log.info(LogUtils.getLogMessage("insertSortWithDiminishingIncrementInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray3)).addParameter("Asc-sorted array", Arrays.asList(sortedArray3))));
        log.info(LogUtils.getLogMessage("insertSortWithDiminishingIncrementInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray4)).addParameter("Desc-sorted array", Arrays.asList(sortedArray4))));
        // Sort to test stability
        Student[] originalArray5 = Arrays.copyOf(this.originalArray5, this.originalArray5.length);
        Student[] originalArray6 = Arrays.copyOf(this.originalArray6, this.originalArray6.length);
        Student[] originalArray7 = Arrays.copyOf(this.originalArray7, this.originalArray7.length);
        Student[] originalArray8 = Arrays.copyOf(this.originalArray8, this.originalArray8.length);
        Student[] sortedArray5 = sortService.insertSortWithDiminishingIncrementInArray(originalArray5, 0, originalArray5.length - 1, true, true);
        Student[] sortedArray6 = sortService.insertSortWithDiminishingIncrementInArray(originalArray6, 0, originalArray6.length - 1, false, true);
        Student[] sortedArray7 = sortService.insertSortWithDiminishingIncrementInArray(originalArray7, 0, originalArray7.length - 1, true, false);
        Student[] sortedArray8 = sortService.insertSortWithDiminishingIncrementInArray(originalArray8, 0, originalArray8.length - 1, false, false);
        log.info(LogUtils.getLogMessage("insertSortWithDiminishingIncrementInArray", "Sort to test stability"));
        log.info(LogUtils.getLogMessage("insertSortWithDiminishingIncrementInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray5)).addParameter("Asc-sorted array", Arrays.asList(sortedArray5))));
        log.info(LogUtils.getLogMessage("insertSortWithDiminishingIncrementInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray6)).addParameter("Desc-sorted array", Arrays.asList(sortedArray6))));
        log.info(LogUtils.getLogMessage("insertSortWithDiminishingIncrementInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray7)).addParameter("Asc-sorted array", Arrays.asList(sortedArray7))));
        log.info(LogUtils.getLogMessage("insertSortWithDiminishingIncrementInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray8)).addParameter("Desc-sorted array", Arrays.asList(sortedArray8))));
        // Sort huge amount of data
        Integer[] originalHugeArray1 = getHugeAmountOfIntegers();
        long timeMillisBeforeSort = SystemUtils.currentTimeMillis();
        Integer[] sortedHugeArray1 = sortService.insertSortWithDiminishingIncrementInArray(originalHugeArray1, 0, originalHugeArray1.length - 1, true, true);
        long timeMillisAfterSort = SystemUtils.currentTimeMillis();
        log.info(LogUtils.getLogMessage("insertSortWithDiminishingIncrementInArray", "Sort huge amount of data"));
        log.info(LogUtils.getLogMessage("insertSortWithDiminishingIncrementInArray", new ParametersToLog().addParameter("Time cost", SystemUtils.elapsedTimeSeconds(timeMillisBeforeSort, timeMillisAfterSort)).addParameter("Sorted elements", sortedHugeArray1.length)));
        // Return result
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Arrays.asList(
                        "Sort the sample arrays:",
                        "Original array: " + Arrays.asList(this.originalArray1) + " | Asc-sorted array: " + Arrays.asList(sortedArray1),
                        "Original array: " + Arrays.asList(this.originalArray2) + " | Desc-sorted array: " + Arrays.asList(sortedArray2),
                        "Original array: " + Arrays.asList(this.originalArray3) + " | Asc-sorted array: " + Arrays.asList(sortedArray3),
                        "Original array: " + Arrays.asList(this.originalArray4) + " | Desc-sorted array: " + Arrays.asList(sortedArray4),
                        "Sort to test stability:",
                        "Original array: " + Arrays.asList(this.originalArray5) + " | Asc-sorted array: " + Arrays.asList(sortedArray5),
                        "Original array: " + Arrays.asList(this.originalArray6) + " | Desc-sorted array: " + Arrays.asList(sortedArray6),
                        "Original array: " + Arrays.asList(this.originalArray7) + " | Asc-sorted array: " + Arrays.asList(sortedArray7),
                        "Original array: " + Arrays.asList(this.originalArray8) + " | Desc-sorted array: " + Arrays.asList(sortedArray8),
                        "Sort huge amount of data:",
                        "Time cost: " + SystemUtils.elapsedTimeSeconds(timeMillisBeforeSort, timeMillisAfterSort) + " | sorted elements: " + sortedHugeArray1.length
                ))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "数组-插入排序-二分搜索优化", notes = "数组-二分（或折半）插入排序")
    @PostMapping("/insertSortWithBinarySearchInArray")
    public CommonResponse insertSortWithBinarySearchInArray() {
        // Sort the sample arrays
        Integer[] originalArray1 = Arrays.copyOf(this.originalArray1, this.originalArray1.length);
        Long[] originalArray2 = Arrays.copyOf(this.originalArray2, this.originalArray2.length);
        Double[] originalArray3 = Arrays.copyOf(this.originalArray3, this.originalArray3.length);
        String[] originalArray4 = Arrays.copyOf(this.originalArray4, this.originalArray4.length);
        Integer[] sortedArray1 = sortService.insertSortWithBinarySearchInArray(originalArray1, 0, originalArray1.length - 1, true, true);
        Long[] sortedArray2 = sortService.insertSortWithBinarySearchInArray(originalArray2, 0, originalArray2.length - 1, false, false);
        Double[] sortedArray3 = sortService.insertSortWithBinarySearchInArray(originalArray3, 0, originalArray3.length - 1, true, false);
        String[] sortedArray4 = sortService.insertSortWithBinarySearchInArray(originalArray4, 0, originalArray4.length - 1, false, true);
        log.info(LogUtils.getLogMessage("insertSortWithBinarySearchInArray", "Sort the sample arrays"));
        log.info(LogUtils.getLogMessage("insertSortWithBinarySearchInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray1)).addParameter("Asc-sorted array", Arrays.asList(sortedArray1))));
        log.info(LogUtils.getLogMessage("insertSortWithBinarySearchInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray2)).addParameter("Desc-sorted array", Arrays.asList(sortedArray2))));
        log.info(LogUtils.getLogMessage("insertSortWithBinarySearchInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray3)).addParameter("Asc-sorted array", Arrays.asList(sortedArray3))));
        log.info(LogUtils.getLogMessage("insertSortWithBinarySearchInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray4)).addParameter("Desc-sorted array", Arrays.asList(sortedArray4))));
        // Sort to test stability
        Student[] originalArray5 = Arrays.copyOf(this.originalArray5, this.originalArray5.length);
        Student[] originalArray6 = Arrays.copyOf(this.originalArray6, this.originalArray6.length);
        Student[] originalArray7 = Arrays.copyOf(this.originalArray7, this.originalArray7.length);
        Student[] originalArray8 = Arrays.copyOf(this.originalArray8, this.originalArray8.length);
        Student[] sortedArray5 = sortService.insertSortWithBinarySearchInArray(originalArray5, 0, originalArray5.length - 1, true, true);
        Student[] sortedArray6 = sortService.insertSortWithBinarySearchInArray(originalArray6, 0, originalArray6.length - 1, false, true);
        Student[] sortedArray7 = sortService.insertSortWithBinarySearchInArray(originalArray7, 0, originalArray7.length - 1, true, false);
        Student[] sortedArray8 = sortService.insertSortWithBinarySearchInArray(originalArray8, 0, originalArray8.length - 1, false, false);
        log.info(LogUtils.getLogMessage("insertSortWithBinarySearchInArray", "Sort to test stability"));
        log.info(LogUtils.getLogMessage("insertSortWithBinarySearchInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray5)).addParameter("Asc-sorted array", Arrays.asList(sortedArray5))));
        log.info(LogUtils.getLogMessage("insertSortWithBinarySearchInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray6)).addParameter("Desc-sorted array", Arrays.asList(sortedArray6))));
        log.info(LogUtils.getLogMessage("insertSortWithBinarySearchInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray7)).addParameter("Asc-sorted array", Arrays.asList(sortedArray7))));
        log.info(LogUtils.getLogMessage("insertSortWithBinarySearchInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray8)).addParameter("Desc-sorted array", Arrays.asList(sortedArray8))));
        // Sort huge amount of data
        Integer[] originalHugeArray1 = getHugeAmountOfIntegers();
        long timeMillisBeforeSort = SystemUtils.currentTimeMillis();
        Integer[] sortedHugeArray1 = sortService.insertSortWithBinarySearchInArray(originalHugeArray1, 0, originalHugeArray1.length - 1, true, true);
        long timeMillisAfterSort = SystemUtils.currentTimeMillis();
        log.info(LogUtils.getLogMessage("insertSortWithBinarySearchInArray", "Sort huge amount of data"));
        log.info(LogUtils.getLogMessage("insertSortWithBinarySearchInArray", new ParametersToLog().addParameter("Time cost", SystemUtils.elapsedTimeSeconds(timeMillisBeforeSort, timeMillisAfterSort)).addParameter("Sorted elements", sortedHugeArray1.length)));
        // Return result
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Arrays.asList(
                        "Sort the sample arrays:",
                        "Original array: " + Arrays.asList(this.originalArray1) + " | Asc-sorted array: " + Arrays.asList(sortedArray1),
                        "Original array: " + Arrays.asList(this.originalArray2) + " | Desc-sorted array: " + Arrays.asList(sortedArray2),
                        "Original array: " + Arrays.asList(this.originalArray3) + " | Asc-sorted array: " + Arrays.asList(sortedArray3),
                        "Original array: " + Arrays.asList(this.originalArray4) + " | Desc-sorted array: " + Arrays.asList(sortedArray4),
                        "Sort to test stability:",
                        "Original array: " + Arrays.asList(this.originalArray5) + " | Asc-sorted array: " + Arrays.asList(sortedArray5),
                        "Original array: " + Arrays.asList(this.originalArray6) + " | Desc-sorted array: " + Arrays.asList(sortedArray6),
                        "Original array: " + Arrays.asList(this.originalArray7) + " | Asc-sorted array: " + Arrays.asList(sortedArray7),
                        "Original array: " + Arrays.asList(this.originalArray8) + " | Desc-sorted array: " + Arrays.asList(sortedArray8),
                        "Sort huge amount of data:",
                        "Time cost: " + SystemUtils.elapsedTimeSeconds(timeMillisBeforeSort, timeMillisAfterSort) + " | sorted elements: " + sortedHugeArray1.length
                ))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "数组-插入排序", notes = "数组-（直接）插入排序")
    @PostMapping("/insertSortInArray")
    public CommonResponse insertSortInArray() {
        // Sort the sample arrays
        Integer[] originalArray1 = Arrays.copyOf(this.originalArray1, this.originalArray1.length);
        Long[] originalArray2 = Arrays.copyOf(this.originalArray2, this.originalArray2.length);
        Double[] originalArray3 = Arrays.copyOf(this.originalArray3, this.originalArray3.length);
        String[] originalArray4 = Arrays.copyOf(this.originalArray4, this.originalArray4.length);
        Integer[] sortedArray1 = sortService.insertSortInArray(originalArray1, 0, originalArray1.length - 1, true, true);
        Long[] sortedArray2 = sortService.insertSortInArray(originalArray2, 0, originalArray2.length - 1, false, false);
        Double[] sortedArray3 = sortService.insertSortInArray(originalArray3, 0, originalArray3.length - 1, true, false);
        String[] sortedArray4 = sortService.insertSortInArray(originalArray4, 0, originalArray4.length - 1, false, true);
        log.info(LogUtils.getLogMessage("insertSortInArray", "Sort the sample arrays"));
        log.info(LogUtils.getLogMessage("insertSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray1)).addParameter("Asc-sorted array", Arrays.asList(sortedArray1))));
        log.info(LogUtils.getLogMessage("insertSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray2)).addParameter("Desc-sorted array", Arrays.asList(sortedArray2))));
        log.info(LogUtils.getLogMessage("insertSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray3)).addParameter("Asc-sorted array", Arrays.asList(sortedArray3))));
        log.info(LogUtils.getLogMessage("insertSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray4)).addParameter("Desc-sorted array", Arrays.asList(sortedArray4))));
        // Sort to test stability
        Student[] originalArray5 = Arrays.copyOf(this.originalArray5, this.originalArray5.length);
        Student[] originalArray6 = Arrays.copyOf(this.originalArray6, this.originalArray6.length);
        Student[] originalArray7 = Arrays.copyOf(this.originalArray7, this.originalArray7.length);
        Student[] originalArray8 = Arrays.copyOf(this.originalArray8, this.originalArray8.length);
        Student[] sortedArray5 = sortService.insertSortInArray(originalArray5, 0, originalArray5.length - 1, true, true);
        Student[] sortedArray6 = sortService.insertSortInArray(originalArray6, 0, originalArray6.length - 1, false, true);
        Student[] sortedArray7 = sortService.insertSortInArray(originalArray7, 0, originalArray7.length - 1, true, false);
        Student[] sortedArray8 = sortService.insertSortInArray(originalArray8, 0, originalArray8.length - 1, false, false);
        log.info(LogUtils.getLogMessage("insertSortInArray", "Sort to test stability"));
        log.info(LogUtils.getLogMessage("insertSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray5)).addParameter("Asc-sorted array", Arrays.asList(sortedArray5))));
        log.info(LogUtils.getLogMessage("insertSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray6)).addParameter("Desc-sorted array", Arrays.asList(sortedArray6))));
        log.info(LogUtils.getLogMessage("insertSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray7)).addParameter("Asc-sorted array", Arrays.asList(sortedArray7))));
        log.info(LogUtils.getLogMessage("insertSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray8)).addParameter("Desc-sorted array", Arrays.asList(sortedArray8))));
        // Sort huge amount of data
        Integer[] originalHugeArray1 = getHugeAmountOfIntegers();
        long timeMillisBeforeSort = SystemUtils.currentTimeMillis();
        Integer[] sortedHugeArray1 = sortService.insertSortInArray(originalHugeArray1, 0, originalHugeArray1.length - 1, true, true);
        long timeMillisAfterSort = SystemUtils.currentTimeMillis();
        log.info(LogUtils.getLogMessage("insertSortInArray", "Sort huge amount of data"));
        log.info(LogUtils.getLogMessage("insertSortInArray", new ParametersToLog().addParameter("Time cost", SystemUtils.elapsedTimeSeconds(timeMillisBeforeSort, timeMillisAfterSort)).addParameter("Sorted elements", sortedHugeArray1.length)));
        // Return result
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Arrays.asList(
                        "Sort the sample arrays:",
                        "Original array: " + Arrays.asList(this.originalArray1) + " | Asc-sorted array: " + Arrays.asList(sortedArray1),
                        "Original array: " + Arrays.asList(this.originalArray2) + " | Desc-sorted array: " + Arrays.asList(sortedArray2),
                        "Original array: " + Arrays.asList(this.originalArray3) + " | Asc-sorted array: " + Arrays.asList(sortedArray3),
                        "Original array: " + Arrays.asList(this.originalArray4) + " | Desc-sorted array: " + Arrays.asList(sortedArray4),
                        "Sort to test stability:",
                        "Original array: " + Arrays.asList(this.originalArray5) + " | Asc-sorted array: " + Arrays.asList(sortedArray5),
                        "Original array: " + Arrays.asList(this.originalArray6) + " | Desc-sorted array: " + Arrays.asList(sortedArray6),
                        "Original array: " + Arrays.asList(this.originalArray7) + " | Asc-sorted array: " + Arrays.asList(sortedArray7),
                        "Original array: " + Arrays.asList(this.originalArray8) + " | Desc-sorted array: " + Arrays.asList(sortedArray8),
                        "Sort huge amount of data:",
                        "Time cost: " + SystemUtils.elapsedTimeSeconds(timeMillisBeforeSort, timeMillisAfterSort) + " | sorted elements: " + sortedHugeArray1.length
                ))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "数组-选择排序", notes = "数组-选择排序")
    @PostMapping("/selectSortInArray")
    public CommonResponse selectSortInArray() {
        // Sort the sample arrays
        Integer[] originalArray1 = Arrays.copyOf(this.originalArray1, this.originalArray1.length);
        Long[] originalArray2 = Arrays.copyOf(this.originalArray2, this.originalArray2.length);
        Double[] originalArray3 = Arrays.copyOf(this.originalArray3, this.originalArray3.length);
        String[] originalArray4 = Arrays.copyOf(this.originalArray4, this.originalArray4.length);
        Integer[] sortedArray1 = sortService.selectSortInArray(originalArray1, 0, originalArray1.length - 1, true, true);
        Long[] sortedArray2 = sortService.selectSortInArray(originalArray2, 0, originalArray2.length - 1, false, false);
        Double[] sortedArray3 = sortService.selectSortInArray(originalArray3, 0, originalArray3.length - 1, true, false);
        String[] sortedArray4 = sortService.selectSortInArray(originalArray4, 0, originalArray4.length - 1, false, true);
        log.info(LogUtils.getLogMessage("selectSortInArray", "Sort the sample arrays"));
        log.info(LogUtils.getLogMessage("selectSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray1)).addParameter("Asc-sorted array", Arrays.asList(sortedArray1))));
        log.info(LogUtils.getLogMessage("selectSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray2)).addParameter("Desc-sorted array", Arrays.asList(sortedArray2))));
        log.info(LogUtils.getLogMessage("selectSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray3)).addParameter("Asc-sorted array", Arrays.asList(sortedArray3))));
        log.info(LogUtils.getLogMessage("selectSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray4)).addParameter("Desc-sorted array", Arrays.asList(sortedArray4))));
        // Sort to test stability
        Student[] originalArray5 = Arrays.copyOf(this.originalArray5, this.originalArray5.length);
        Student[] originalArray6 = Arrays.copyOf(this.originalArray6, this.originalArray6.length);
        Student[] originalArray7 = Arrays.copyOf(this.originalArray7, this.originalArray7.length);
        Student[] originalArray8 = Arrays.copyOf(this.originalArray8, this.originalArray8.length);
        Student[] sortedArray5 = sortService.selectSortInArray(originalArray5, 0, originalArray5.length - 1, true, true);
        Student[] sortedArray6 = sortService.selectSortInArray(originalArray6, 0, originalArray6.length - 1, false, true);
        Student[] sortedArray7 = sortService.selectSortInArray(originalArray7, 0, originalArray7.length - 1, true, false);
        Student[] sortedArray8 = sortService.selectSortInArray(originalArray8, 0, originalArray8.length - 1, false, false);
        log.info(LogUtils.getLogMessage("selectSortInArray", "Sort to test stability"));
        log.info(LogUtils.getLogMessage("selectSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray5)).addParameter("Asc-sorted array", Arrays.asList(sortedArray5))));
        log.info(LogUtils.getLogMessage("selectSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray6)).addParameter("Desc-sorted array", Arrays.asList(sortedArray6))));
        log.info(LogUtils.getLogMessage("selectSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray7)).addParameter("Asc-sorted array", Arrays.asList(sortedArray7))));
        log.info(LogUtils.getLogMessage("selectSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray8)).addParameter("Desc-sorted array", Arrays.asList(sortedArray8))));
        // Sort huge amount of data
        Integer[] originalHugeArray1 = getHugeAmountOfIntegers();
        long timeMillisBeforeSort = SystemUtils.currentTimeMillis();
        Integer[] sortedHugeArray1 = sortService.selectSortInArray(originalHugeArray1, 0, originalHugeArray1.length - 1, true, true);
        long timeMillisAfterSort = SystemUtils.currentTimeMillis();
        log.info(LogUtils.getLogMessage("selectSortInArray", "Sort huge amount of data"));
        log.info(LogUtils.getLogMessage("selectSortInArray", new ParametersToLog().addParameter("Time cost", SystemUtils.elapsedTimeSeconds(timeMillisBeforeSort, timeMillisAfterSort)).addParameter("Sorted elements", sortedHugeArray1.length)));
        // Return result
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Arrays.asList(
                        "Sort the sample arrays:",
                        "Original array: " + Arrays.asList(this.originalArray1) + " | Asc-sorted array: " + Arrays.asList(sortedArray1),
                        "Original array: " + Arrays.asList(this.originalArray2) + " | Desc-sorted array: " + Arrays.asList(sortedArray2),
                        "Original array: " + Arrays.asList(this.originalArray3) + " | Asc-sorted array: " + Arrays.asList(sortedArray3),
                        "Original array: " + Arrays.asList(this.originalArray4) + " | Desc-sorted array: " + Arrays.asList(sortedArray4),
                        "Sort to test stability:",
                        "Original array: " + Arrays.asList(this.originalArray5) + " | Asc-sorted array: " + Arrays.asList(sortedArray5),
                        "Original array: " + Arrays.asList(this.originalArray6) + " | Desc-sorted array: " + Arrays.asList(sortedArray6),
                        "Original array: " + Arrays.asList(this.originalArray7) + " | Asc-sorted array: " + Arrays.asList(sortedArray7),
                        "Original array: " + Arrays.asList(this.originalArray8) + " | Desc-sorted array: " + Arrays.asList(sortedArray8),
                        "Sort huge amount of data:",
                        "Time cost: " + SystemUtils.elapsedTimeSeconds(timeMillisBeforeSort, timeMillisAfterSort) + " | sorted elements: " + sortedHugeArray1.length
                ))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "数组-冒泡排序", notes = "数组-冒泡排序")
    @PostMapping("/bubbleSortInArray")
    public CommonResponse bubbleSortInArray() {
        // Sort the sample arrays
        Integer[] originalArray1 = Arrays.copyOf(this.originalArray1, this.originalArray1.length);
        Long[] originalArray2 = Arrays.copyOf(this.originalArray2, this.originalArray2.length);
        Double[] originalArray3 = Arrays.copyOf(this.originalArray3, this.originalArray3.length);
        String[] originalArray4 = Arrays.copyOf(this.originalArray4, this.originalArray4.length);
        Integer[] sortedArray1 = sortService.bubbleSortInArray(originalArray1, 0, originalArray1.length - 1, true, true);
        Long[] sortedArray2 = sortService.bubbleSortInArray(originalArray2, 0, originalArray2.length - 1, false, false);
        Double[] sortedArray3 = sortService.bubbleSortInArray(originalArray3, 0, originalArray3.length - 1, true, false);
        String[] sortedArray4 = sortService.bubbleSortInArray(originalArray4, 0, originalArray4.length - 1, false, true);
        log.info(LogUtils.getLogMessage("bubbleSortInArray", "Sort the sample arrays"));
        log.info(LogUtils.getLogMessage("bubbleSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray1)).addParameter("Asc-sorted array", Arrays.asList(sortedArray1))));
        log.info(LogUtils.getLogMessage("bubbleSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray2)).addParameter("Desc-sorted array", Arrays.asList(sortedArray2))));
        log.info(LogUtils.getLogMessage("bubbleSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray3)).addParameter("Asc-sorted array", Arrays.asList(sortedArray3))));
        log.info(LogUtils.getLogMessage("bubbleSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray4)).addParameter("Desc-sorted array", Arrays.asList(sortedArray4))));
        // Sort to test stability
        Student[] originalArray5 = Arrays.copyOf(this.originalArray5, this.originalArray5.length);
        Student[] originalArray6 = Arrays.copyOf(this.originalArray6, this.originalArray6.length);
        Student[] originalArray7 = Arrays.copyOf(this.originalArray7, this.originalArray7.length);
        Student[] originalArray8 = Arrays.copyOf(this.originalArray8, this.originalArray8.length);
        Student[] sortedArray5 = sortService.bubbleSortInArray(originalArray5, 0, originalArray5.length - 1, true, true);
        Student[] sortedArray6 = sortService.bubbleSortInArray(originalArray6, 0, originalArray6.length - 1, false, true);
        Student[] sortedArray7 = sortService.bubbleSortInArray(originalArray7, 0, originalArray7.length - 1, true, false);
        Student[] sortedArray8 = sortService.bubbleSortInArray(originalArray8, 0, originalArray8.length - 1, false, false);
        log.info(LogUtils.getLogMessage("bubbleSortInArray", "Sort to test stability"));
        log.info(LogUtils.getLogMessage("bubbleSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray5)).addParameter("Asc-sorted array", Arrays.asList(sortedArray5))));
        log.info(LogUtils.getLogMessage("bubbleSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray6)).addParameter("Desc-sorted array", Arrays.asList(sortedArray6))));
        log.info(LogUtils.getLogMessage("bubbleSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray7)).addParameter("Asc-sorted array", Arrays.asList(sortedArray7))));
        log.info(LogUtils.getLogMessage("bubbleSortInArray", new ParametersToLog().addParameter("Original array", Arrays.asList(this.originalArray8)).addParameter("Desc-sorted array", Arrays.asList(sortedArray8))));
        // Sort huge amount of data
        Integer[] originalHugeArray1 = getHugeAmountOfIntegers();
        long timeMillisBeforeSort = SystemUtils.currentTimeMillis();
        Integer[] sortedHugeArray1 = sortService.bubbleSortInArray(originalHugeArray1, 0, originalHugeArray1.length - 1, true, true);
        long timeMillisAfterSort = SystemUtils.currentTimeMillis();
        log.info(LogUtils.getLogMessage("bubbleSortInArray", "Sort huge amount of data"));
        log.info(LogUtils.getLogMessage("bubbleSortInArray", new ParametersToLog().addParameter("Time cost", SystemUtils.elapsedTimeSeconds(timeMillisBeforeSort, timeMillisAfterSort)).addParameter("Sorted elements", sortedHugeArray1.length)));
        // Return result
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Arrays.asList(
                        "Sort the sample arrays:",
                        "Original array: " + Arrays.asList(this.originalArray1) + " | Asc-sorted array: " + Arrays.asList(sortedArray1),
                        "Original array: " + Arrays.asList(this.originalArray2) + " | Desc-sorted array: " + Arrays.asList(sortedArray2),
                        "Original array: " + Arrays.asList(this.originalArray3) + " | Asc-sorted array: " + Arrays.asList(sortedArray3),
                        "Original array: " + Arrays.asList(this.originalArray4) + " | Desc-sorted array: " + Arrays.asList(sortedArray4),
                        "Sort to test stability:",
                        "Original array: " + Arrays.asList(this.originalArray5) + " | Asc-sorted array: " + Arrays.asList(sortedArray5),
                        "Original array: " + Arrays.asList(this.originalArray6) + " | Desc-sorted array: " + Arrays.asList(sortedArray6),
                        "Original array: " + Arrays.asList(this.originalArray7) + " | Asc-sorted array: " + Arrays.asList(sortedArray7),
                        "Original array: " + Arrays.asList(this.originalArray8) + " | Desc-sorted array: " + Arrays.asList(sortedArray8),
                        "Sort huge amount of data:",
                        "Time cost: " + SystemUtils.elapsedTimeSeconds(timeMillisBeforeSort, timeMillisAfterSort) + " | sorted elements: " + sortedHugeArray1.length
                ))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    /**
     *  Generate huge amount of data
     *
     * @return the array that contains huge amount of integers
     */
    private Integer[] getHugeAmountOfIntegers() {
        log.info(LogUtils.getLogMessage("getHugeAmountOfIntegers", "Generating huge amount of data"));
        Integer[] integers = new Integer[this.dataAmount];
        for (int i = 0; i < this.dataAmount; i++) {
            integers[i] = this.random.nextInt(this.dataAmount);
        }
        log.info(LogUtils.getLogMessage("getHugeAmountOfIntegers", "Complete generating data"));
        return integers;
    }
}
