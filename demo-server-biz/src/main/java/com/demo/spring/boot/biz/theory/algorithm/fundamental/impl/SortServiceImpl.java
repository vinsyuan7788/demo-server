package com.demo.spring.boot.biz.theory.algorithm.fundamental.impl;

import com.demo.spring.boot.biz.theory.algorithm.fundamental.SortService;
import com.demo.spring.boot.biz.theory.structure.data.array.ArraySortService;
import com.demo.spring.boot.biz.theory.structure.data.array.utils.enums.SortObjectEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vince Yuan
 * @date 03/10/2021
 */
@Slf4j
@Service
public class SortServiceImpl implements SortService {

    @Autowired
    private ArraySortService arraySortService;

    @Override
    public <T extends Comparable<T>> T[] timSortInArray(T[] array, int startIndex, int endIndex, boolean isAsc) {
        return arraySortService.timSort(array, startIndex, endIndex, isAsc);
    }

    @Override
    public <T extends Comparable<T>> T[] radixSortInArray(T[] array, int startIndex, int endIndex, boolean isAsc, boolean useLeastSignificantDigit, SortObjectEnum sortObjectEnum) {
        return arraySortService.radixSort(array, startIndex, endIndex, isAsc, useLeastSignificantDigit, sortObjectEnum);
    }

    @Override
    public <T extends Comparable<T>> T[] heapSortInArray(T[] array, int startIndex, int endIndex, boolean isAsc, boolean heapifyToTheStart) {
        return arraySortService.heapSort(array, startIndex, endIndex, isAsc, heapifyToTheStart);
    }

    @Override
    public <T extends Comparable<T>> T[] mergeSortInArray(T[] array, int startIndex, int endIndex, boolean isAsc) {
        return arraySortService.mergeSort(array, startIndex, endIndex, isAsc);
    }

    @Override
    public <T extends Comparable<T>> T[] quickSortWithStabilityInArray(T[] array, int startIndex, int endIndex, boolean isAsc, boolean useStartPivot) {
        return arraySortService.quickSortWithStability(array, startIndex, endIndex, isAsc, useStartPivot);
    }

    @Override
    public <T extends Comparable<T>> T[] quickSortInArray(T[] array, int startIndex, int endIndex, boolean isAsc, boolean useStartPivot) {
        return arraySortService.quickSort(array, startIndex, endIndex, isAsc, useStartPivot);
    }

    @Override
    public <T extends Comparable<T>> T[] insertSortWithDiminishingIncrementInArray(T[] array, int startIndex, int endIndex, boolean isAsc, boolean insertToTheStart) {
        return arraySortService.insertSortWithDiminishingIncrement(array, startIndex, endIndex, isAsc, insertToTheStart);
    }

    @Override
    public <T extends Comparable<T>> T[] insertSortWithBinarySearchInArray(T[] array, int startIndex, int endIndex, boolean isAsc, boolean insertToTheStart) {
        return arraySortService.insertSortWithBinarySearch(array, startIndex, endIndex, isAsc, insertToTheStart);
    }

    @Override
    public <T extends Comparable<T>> T[] insertSortInArray(T[] array, int startIndex, int endIndex, boolean isAsc, boolean insertToTheStart) {
        return arraySortService.insertSort(array, startIndex, endIndex, isAsc, insertToTheStart);
    }

    @Override
    public <T extends Comparable<T>> T[] selectSortInArray(T[] array, int startIndex, int endIndex, boolean isAsc, boolean selectToTheStart) {
        return arraySortService.selectSort(array, startIndex, endIndex, isAsc, selectToTheStart);
    }

    @Override
    public <T extends Comparable<T>> T[] bubbleSortInArray(T[] array, int startIndex, int endIndex, boolean isAsc, boolean bubbleToTheStart) {
        return arraySortService.bubbleSort(array, startIndex, endIndex, isAsc, bubbleToTheStart);
    }
}
