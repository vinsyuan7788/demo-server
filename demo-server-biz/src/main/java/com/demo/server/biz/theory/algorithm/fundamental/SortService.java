package com.demo.server.biz.theory.algorithm.fundamental;

import com.demo.server.biz.theory.structure.data.array.utils.enums.SortObjectEnum;

/**
 * @author Vince Yuan
 * @date 03/10/2021
 */
public interface SortService {

    /**
     *  Perform a tim sort in array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    <T extends Comparable<T>> T[] timSortInArray(T[] array, int startIndex, int endIndex, boolean isAsc);

    /**
     *  Perform a radix sort in array
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
    <T extends Comparable<T>> T[] radixSortInArray(T[] array, int startIndex, int endIndex, boolean isAsc, boolean useLeastSignificantDigit, SortObjectEnum sortObjectEnum);

    /**
     *  Perform a heap sort in array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param heapifyToTheStart if heapifying the array to the start index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    <T extends Comparable<T>> T[] heapSortInArray(T[] array, int startIndex, int endIndex, boolean isAsc, boolean heapifyToTheStart);

    /**
     *  Perform a merge sort in array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    <T extends Comparable<T>> T[] mergeSortInArray(T[] array, int startIndex, int endIndex, boolean isAsc);

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
    <T extends Comparable<T>> T[] quickSortWithStabilityInArray(T[] array, int startIndex, int endIndex, boolean isAsc, boolean useStartPivot);

    /**
     *  Perform a quick sort in array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param useStartPivot if using start pivot to sort the elements in the array from the end index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    <T extends Comparable<T>> T[] quickSortInArray(T[] array, int startIndex, int endIndex, boolean isAsc, boolean useStartPivot);

    /**
     *  Perform a shell sort in array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param insertToTheStart if inserting the element towards the start index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    <T extends Comparable<T>> T[] insertSortWithDiminishingIncrementInArray(T[] array, int startIndex, int endIndex, boolean isAsc, boolean insertToTheStart);

    /**
     *  Perform a binary insert sort in array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param insertToTheStart if inserting the element towards the start index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    <T extends Comparable<T>> T[] insertSortWithBinarySearchInArray(T[] array, int startIndex, int endIndex, boolean isAsc, boolean insertToTheStart);

    /**
     *  Perform a (straight) insert sort in array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param insertToTheStart if inserting the element towards the start index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    <T extends Comparable<T>> T[] insertSortInArray(T[] array, int startIndex, int endIndex, boolean isAsc, boolean insertToTheStart);

    /**
     *  Perform a (straight) select sort in array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param selectToTheStart if selecting the element towards the start index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    <T extends Comparable<T>> T[] selectSortInArray(T[] array, int startIndex, int endIndex, boolean isAsc, boolean selectToTheStart);

    /**
     *  Perform a bubble sort in array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param bubbleToTheStart if bubbling the element towards the start index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    <T extends Comparable<T>> T[] bubbleSortInArray(T[] array, int startIndex, int endIndex, boolean isAsc, boolean bubbleToTheStart);
}
