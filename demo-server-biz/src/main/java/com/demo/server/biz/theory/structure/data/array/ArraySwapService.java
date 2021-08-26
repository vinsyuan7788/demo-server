package com.demo.server.biz.theory.structure.data.array;

/**
 * @author Vince Yuan
 * @date 08/26/2021
 */
public interface ArraySwapService {

    /**
     *  Perform a swap through temporary variable
     *
     * @param array the array to be swapped
     * @param i an index of the element to be swapped in the array
     * @param j another index of the element to be swapped in the array
     * @param <T> the generic type
     * @return the swapped array
     */
    <T> T[] swapByTemporaryVariable(T[] array, int i, int j);

    /**
     *  Perform a swap by bit operation
     *
     * @param array the array to be swapped
     * @param i an index of the element to be swapped in the array
     * @param j another index of the element to be swapped in the array
     * @return the swapped array
     */
    Integer[] swapByBitOperation(Integer[] array, int i, int j);
}
