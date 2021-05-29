package com.demo.server.biz.theory.structure.data.array;

/**
 * @author Vince Yuan
 * @date 03/10/2021
 */
public interface ArrayService {

    /**
     *  Swap the elements at index i and j in the array
     *
     * @param array the array that needs to swap elements
     * @param i the index where the element lies to be swapped
     * @param j another index where the element lies to be swapped
     * @param <T> The generic type that represents the data type of the element in the array
     * @return the array whose elements has been swapped
     */
    <T> T[] swap(T[] array, int i, int j);

    /**
     *  Sort the elements of the array in ascending or descending order
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    <T extends Comparable<T>> T[] sort(T[] array, int startIndex, int endIndex, boolean isAsc);
}
