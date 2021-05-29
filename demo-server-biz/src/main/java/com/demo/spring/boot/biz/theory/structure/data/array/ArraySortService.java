package com.demo.spring.boot.biz.theory.structure.data.array;

import com.demo.spring.boot.biz.theory.structure.data.array.utils.enums.SortObjectEnum;

/**
 * @author Vince Yuan
 * @date 03/29/2021
 */
public interface ArraySortService {

    /**
     *  Perform a tim sort
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    <T extends Comparable<T>> T[] timSort(T[] array, int startIndex, int endIndex, boolean isAsc);

    /**
     *  Perform a radix sort
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param useLeastSignificantDigit if using least significant digit to implement radix sort
     * @param sortObjectEnum what type of object to be sorted
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    <T extends Comparable<T>> T[] radixSort(T[] array, int startIndex, int endIndex, boolean isAsc, boolean useLeastSignificantDigit, SortObjectEnum sortObjectEnum);

    /**
     *  Perform a heap sort
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param heapifyToTheStart if heapifying the array to the start index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    <T extends Comparable<T>> T[] heapSort(T[] array, int startIndex, int endIndex, boolean isAsc, boolean heapifyToTheStart);

    /**
     *  Perform a merge sort
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    <T extends Comparable<T>> T[] mergeSort(T[] array, int startIndex, int endIndex, boolean isAsc);

    /**
     *  Perform a quick sort with considering stability while sacrificing time complexity in some scenario <br/>
     *  -- This method is reserved as a reference or pending-to-do
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param useStartPivot if using start pivot to sort the elements in the array from the end index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    <T extends Comparable<T>> T[] quickSortWithStability(T[] array, int startIndex, int endIndex, boolean isAsc, boolean useStartPivot);

    /**
     *  Perform a quick sort
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param useStartPivot if using start pivot to sort the elements in the array from the end index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    <T extends Comparable<T>> T[] quickSort(T[] array, int startIndex, int endIndex, boolean isAsc, boolean useStartPivot);

    /**
     *  Perform a shell sort
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param insertToTheStart if inserting the element towards the start index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    <T extends Comparable<T>> T[] insertSortWithDiminishingIncrement(T[] array, int startIndex, int endIndex, boolean isAsc, boolean insertToTheStart);

    /**
     *  Perform a binary insert sort
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param insertToTheStart if inserting the element towards the start index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    <T extends Comparable<T>> T[] insertSortWithBinarySearch(T[] array, int startIndex, int endIndex, boolean isAsc, boolean insertToTheStart);

    /**
     *  Perform a (straight) insert sort
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param insertToTheStart if inserting the element towards the start index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    <T extends Comparable<T>> T[] insertSort(T[] array, int startIndex, int endIndex, boolean isAsc, boolean insertToTheStart);

    /**
     *  Perform a (straight) select sort
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param selectToTheStart if selecting the element towards the start index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    <T extends Comparable<T>> T[] selectSort(T[] array, int startIndex, int endIndex, boolean isAsc, boolean selectToTheStart);

    /**
     *  Perform a bubble sort
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param bubbleToTheStart if bubbling the element towards the start index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    <T extends Comparable<T>> T[] bubbleSort(T[] array, int startIndex, int endIndex, boolean isAsc, boolean bubbleToTheStart);
}
