package com.demo.server.biz.theory.structure.data.array.impl;

import com.demo.server.biz.theory.structure.data.array.ArraySwapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Vince Yuan
 * @date 08/26/2021
 */
@Slf4j
@Service
public class ArraySwapServiceImpl implements ArraySwapService {

    @Override
    public <T> T[] swapByTemporaryVariable(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        return array;
    }

    @Override
    public Integer[] swapByBitOperation(Integer[] array, int i, int j) {
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[j] ^ array[i];
        return array;
    }
}
