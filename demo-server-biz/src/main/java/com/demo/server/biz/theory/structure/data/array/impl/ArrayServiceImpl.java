package com.demo.server.biz.theory.structure.data.array.impl;

import com.demo.server.biz.theory.structure.data.array.ArrayService;
import com.demo.server.biz.theory.structure.data.array.ArraySortService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vince Yuan
 * @date 03/10/2021
 */
@Slf4j
@Service
public class ArrayServiceImpl implements ArrayService {

    @Autowired
    private ArraySortService arraySortService;

    @Override
    public <T> T[] swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        return array;
    }

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array, int startIndex, int endIndex, boolean isAsc) {
        return arraySortService.heapSort(array, startIndex, endIndex, isAsc, true);
    }
}
