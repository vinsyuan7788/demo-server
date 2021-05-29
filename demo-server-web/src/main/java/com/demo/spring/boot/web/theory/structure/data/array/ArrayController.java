package com.demo.spring.boot.web.theory.structure.data.array;

import com.demo.base.common.response.bean.CommonResponse;
import com.demo.base.common.response.enums.ResponseEnum;
import com.demo.spring.boot.biz.theory.structure.data.array.ArrayService;
import com.demo.spring.boot.common.utils.SystemUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author Vince Yuan
 * @date 03/10/2021
 */
@Api(value = "原理-数据结构-数组", tags = "原理-数据结构-数组")
@Slf4j
@RestController
@RequestMapping("/structure/data/array")
public class ArrayController {

    @Autowired
    private ArrayService arrayService;

    private final Integer[] originalArray1 = new Integer[] { 1, 5, 8, 3, 6, 5, 2 };
    private final Long[] originalArray2 = new Long[] { 10L, 50L, 80L, 30L, 60L, 50L, 20L };
    private final Double[] originalArray3 = new Double[] { 10.24d, 50.78d, 82.43d, 38.47d, 61.98d, 50.78d, 23.65d };
    private final String[] originalArray4 = new String[] { "Hello", "Hi", "Howdy", "Hey", "What's Up" };

    @ApiOperation(value = "交换", notes = "对数组中的两个元素进行交换")
    @PostMapping("/swap")
    public CommonResponse swap() {
        Integer[] originalArray1 = Arrays.copyOf(this.originalArray1, this.originalArray1.length);
        Long[] originalArray2 = Arrays.copyOf(this.originalArray2, this.originalArray2.length);
        Double[] originalArray3 = Arrays.copyOf(this.originalArray3, this.originalArray3.length);
        String[] originalArray4 = Arrays.copyOf(this.originalArray4, this.originalArray4.length);
        Integer[] swappedArray1 = arrayService.swap(originalArray1, 0, originalArray1.length - 1);
        Long[] swappedArray2 = arrayService.swap(originalArray2, 0, originalArray2.length - 1);
        Double[] swappedArray3 = arrayService.swap(originalArray3, 0, originalArray3.length - 1);
        String[] swappedArray4 = arrayService.swap(originalArray4, 0, originalArray4.length - 1);
        log.info("=== Swap the elements in the sample arrays ===");
        log.info("=== Original array: " + Arrays.asList(this.originalArray1) + " | Swapped array: " + Arrays.asList(swappedArray1) + " ===");
        log.info("=== Original array: " + Arrays.asList(this.originalArray2) + " | Swapped array: " + Arrays.asList(swappedArray2) + " ===");
        log.info("=== Original array: " + Arrays.asList(this.originalArray3) + " | Swapped array: " + Arrays.asList(swappedArray3) + " ===");
        log.info("=== Original array: " + Arrays.asList(this.originalArray4) + " | Swapped array: " + Arrays.asList(swappedArray4) + " ===");
        // Return result
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Arrays.asList(
                        "Swap the elements in the sample arrays:",
                        "Original array: " + Arrays.asList(this.originalArray1) + " | Swapped array: " + Arrays.asList(swappedArray1),
                        "Original array: " + Arrays.asList(this.originalArray2) + " | Swapped array: " + Arrays.asList(swappedArray2),
                        "Original array: " + Arrays.asList(this.originalArray3) + " | Swapped array: " + Arrays.asList(swappedArray3),
                        "Original array: " + Arrays.asList(this.originalArray4) + " | Swapped array: " + Arrays.asList(swappedArray4)
                ))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "排序", notes = "对数组中的元素进行排序")
    @PostMapping("/sort")
    public CommonResponse sort() {
        Integer[] originalArray1 = Arrays.copyOf(this.originalArray1, this.originalArray1.length);
        Long[] originalArray2 = Arrays.copyOf(this.originalArray2, this.originalArray2.length);
        Double[] originalArray3 = Arrays.copyOf(this.originalArray3, this.originalArray3.length);
        String[] originalArray4 = Arrays.copyOf(this.originalArray4, this.originalArray4.length);
        Integer[] sortedArray1 = arrayService.sort(originalArray1, 0, originalArray1.length - 1, true);
        Long[] sortedArray2 = arrayService.sort(originalArray2, 0, originalArray2.length - 1, false);
        Double[] sortedArray3 = arrayService.sort(originalArray3, 0, originalArray3.length - 1, true);
        String[] sortedArray4 = arrayService.sort(originalArray4, 0, originalArray4.length - 1, false);
        log.info("=== Sort the sample arrays ===");
        log.info("=== Original array: " + Arrays.asList(this.originalArray1) + " | Asc-sorted array: " + Arrays.asList(sortedArray1) + " ===");
        log.info("=== Original array: " + Arrays.asList(this.originalArray2) + " | Desc-sorted array: " + Arrays.asList(sortedArray2) + " ===");
        log.info("=== Original array: " + Arrays.asList(this.originalArray3) + " | Asc-sorted array: " + Arrays.asList(sortedArray3) + " ===");
        log.info("=== Original array: " + Arrays.asList(this.originalArray4) + " | Desc-sorted array: " + Arrays.asList(sortedArray4) + " ===");
        // Return result
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Arrays.asList(
                        "Sort the sample arrays:",
                        "Original array: " + Arrays.asList(this.originalArray1) + " | Asc-sorted array: " + Arrays.asList(sortedArray1),
                        "Original array: " + Arrays.asList(this.originalArray2) + " | Desc-sorted array: " + Arrays.asList(sortedArray2),
                        "Original array: " + Arrays.asList(this.originalArray3) + " | Asc-sorted array: " + Arrays.asList(sortedArray3),
                        "Original array: " + Arrays.asList(this.originalArray4) + " | Desc-sorted array: " + Arrays.asList(sortedArray4)
                ))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }
}
