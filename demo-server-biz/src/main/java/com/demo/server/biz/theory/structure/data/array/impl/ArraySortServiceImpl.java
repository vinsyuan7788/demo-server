package com.demo.server.biz.theory.structure.data.array.impl;

import com.demo.base.common.exception.bean.BusinessException;
import com.demo.server.biz.theory.structure.data.array.ArrayService;
import com.demo.server.biz.theory.structure.data.array.ArraySortService;
import com.demo.server.biz.theory.structure.data.array.utils.enums.SortedElementTypeEnum;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Vince Yuan
 * @date 03/29/2021
 */
@Slf4j
@Service
public class ArraySortServiceImpl implements ArraySortService {

    @Autowired
    private ArrayService arrayService;

    /*** Conclusion of Sort Algorithms ***/
    /**
     *  Conclusion of tim sort: <br/>
     *  -- Brief & Key: <br/>
     *  &emsp; --  <br/>
     *  -- Time Complexity: O(nlogn) in average <br/>
     *  &emsp; --  <br/>
     *  -- Space Complexity: O(n) <br/>
     *  &emsp; --  <br/>
     *  &emsp; -- Hence it is NOT an in-place algorithm <br/>
     *  -- Stability: stable <br/>
     *  &emsp; --  <br/>
     *  &emsp; -- Hence it is a stable sort algorithm
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param <T>
     * @return
     */
    @Override
    public <T extends Comparable<T>> T[] timSort(T[] array, int startIndex, int endIndex, boolean isAsc) {
        return array;
    }

    /**
     *  Conclusion of radix sort: <br/>
     *  -- Brief & Key: <br/>
     *  &emsp; --  <br/>
     *  -- Time Complexity: O(nlog(r)m) in average <br/>
     *  &emsp; --  <br/>
     *  -- Space Complexity:  <br/>
     *  &emsp; --  <br/>
     *  &emsp; -- Hence it is NOT an in-place algorithm <br/>
     *  -- Stability: stable <br/>
     *  &emsp; --  <br/>
     *  &emsp; -- Hence it is a stable sort algorithm
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param useLeastSignificantDigit if using least significant digit
     * @param sortObjectEnum what type of object to be sorted
     * @param <T>
     * @return
     */
    @Override
    public <T extends Comparable<T>> T[] radixSort(T[] array, int startIndex, int endIndex, boolean isAsc, boolean useLeastSignificantDigit, SortedElementTypeEnum sortObjectEnum) {
        return useLeastSignificantDigit ? sortByUsingLeastSignificantDigit(array, startIndex, endIndex, isAsc, sortObjectEnum) : sortByUsingMostSignificantDigit(array, startIndex, endIndex, isAsc, sortObjectEnum);
    }

    /**
     *  Conclusion of heap sort: <br/>
     *  -- Brief & Key: <br/>
     *  &emsp; -- It is an implementation of the idea of iterative algorithm <br/>
     *  &emsp; -- It borrows the idea of heapification and swap the elements upside-down at each iteration until all elements are traversed <br/>
     *  &emsp; -- It is one of the most applicable or referable algorithms in the industry <br/>
     *  -- Time Complexity: O(nlogn) in average <br/>
     *  &emsp; -- The heapification needs to be performed across the elements from start to end iteratively (i.e., O(n)) <br/>
     *  &emsp; -- Each heapification traverses all parent elements (i.e., O(logn)) <br/>
     *  -- Space Complexity: O(1) <br/>
     *  &emsp; -- It does not require extra space to store temporary sorted result <br/>
     *  &emsp; -- Hence it is an in-place algorithm <br/>
     *  -- Stability: unstable <br/>
     *  &emsp; -- If all elements have the same value, they will be swapped at the end of each iteration <br/>
     *  &emsp; -- Each iteration will rebuild the heap, during which the elements will be regroup as new parent-children iteratively pairs for comparison and possible swap <br/>
     *  &emsp; -- Hence it is NOT a stable sort algorithm
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param heapifyToTheStart if heapifying the array to the start index of the array
     * @param <T>
     * @return
     */
    @Override
    public <T extends Comparable<T>> T[] heapSort(T[] array, int startIndex, int endIndex, boolean isAsc, boolean heapifyToTheStart) {
        return heapifyToTheStart ? sortByHeapifyingToTheStart(array, startIndex, endIndex, isAsc) : sortByHeapifyingToTheEnd(array, startIndex, endIndex, isAsc);
    }

    /**
     *  Conclusion of merge sort: <br/>
     *  -- Brief & Key: <br/>
     *  &emsp; -- It is an implementation of the idea of divide-and-conquer algorithm <br/>
     *  &emsp; -- The recursive method invocation is an representation of Binary-Tree <br/>
     *  &emsp; -- It is one of the most applicable or referable algorithms in the industry <br/>
     *  -- Time Complexity: O(nlogn) in average <br/>
     *  &emsp; -- The recursive method invocation is a Binary-Tree structure (i.e., O(logn)) <br/>
     *  &emsp; -- Each invocation needs to compare each element (i.e., O(n)) in the merged sub-sequence <br/>
     *  -- Space Complexity: O(n) <br/>
     *  &emsp; -- It requires an additional array to temporarily store the sorted elements <br/>
     *  &emsp; -- Hence it is NOT an in-place algorithm <br/>
     *  -- Stability: stable <br/>
     *  &emsp; -- If the sub-sequence contains only 1 element, the element will be directly returned <br/>
     *  &emsp; -- If the sub-sequence contains more than 1 equal elements, the elements will be directly moved to the new sub-sequence without interchanging the positions of two elements <br/>
     *  &emsp; -- Hence it is a stable sort algorithm
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param <T>
     * @return
     */
    @Override
    public <T extends Comparable<T>> T[] mergeSort(T[] array, int startIndex, int endIndex, boolean isAsc) {
        return divideRecursivelyAndMerge(array, startIndex, endIndex, isAsc, Arrays.copyOf(array, array.length));
    }

    /**
     *  Conclusion of quick sort with considering stability while sacrificing time complexity in some scenario: <br/>
     *  -- Brief & Key: <br/>
     *  &emsp; -- Refer to {@link ArraySortServiceImpl#quickSort} <br/>
     *  -- Time Complexity: O(nlogn) in average, O(n^2) for the worst <br/>
     *  &emsp; -- There will be more scenario that reaches the worst time complexity <br/>
     *  &emsp; -- For example, for an array whose elements have the same value, it will be O(n^2) <br/>
     *  &emsp; -- The more elements with the same value, the more likely it will reach the worse case <br/>
     *  -- Space Complexity: O(1) <br/>
     *  &emsp; -- Refer to {@link ArraySortServiceImpl#quickSort} <br/>
     *  -- Stability: stable <br/>
     *  &emsp; -- The element that has the same value as (or meet the condition of) pivot will NOT be swapped <br/>
     *  &emsp; &emsp; -- But the index of elements within above range may be interchanged <br/>
     *  &emsp; -- Hence it is still NOT a stable sort algorithm <br/>
     *  &emsp; &emsp; -- It proves that stability is defined or decided by the nature of the algorithm
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param useStartPivot if using start pivot to sort the elements in the array from the end index of the array
     * @param <T>
     * @return
     */
    @Override
    public <T extends Comparable<T>> T[] quickSortWithStability(T[] array, int startIndex, int endIndex, boolean isAsc, boolean useStartPivot) {
        return useStartPivot ? sortByUsingStartPivotAndDivideRecursivelyWithStability(array, startIndex, endIndex, isAsc) : sortByUsingEndPivotAndDivideRecursivelyWithStability(array, startIndex, endIndex, isAsc);
    }

    /**
     *  Conclusion of quick sort: <br/>
     *  -- Brief & Key: <br/>
     *  &emsp; -- It is an implementation of the idea of divide-and-conquer algorithm (D&C) <br/>
     *  &emsp; -- The recursive method invocation is an representation of Binary-Tree <br/>
     *  &emsp; -- It is one of the most applicable or referable algorithms in the industry <br/>
     *  -- Time Complexity: O(nlogn) in average, O(n^2) for the worst <br/>
     *  &emsp; -- The recursive method invocation is a Binary-Tree structure (i.e., O(logn)) <br/>
     *  &emsp; -- Each invocation needs to traverse all elements (i.e., O(n)) in the divided sub-sequence <br/>
     *  &emsp; -- The worst case does not happen as frequently as the quick sort with the modification of stability <br/>
     *  -- Space Complexity: O(1) <br/>
     *  &emsp; -- It does not require extra space to store temporary sorted result <br/>
     *  &emsp; -- Hence it is an in-place algorithm <br/>
     *  -- Stability: unstable <br/>
     *  &emsp; -- The element that has the same value as pivot will be swapped to ensure the pivot will get to the middle index of the array as close as possible <br/>
     *  &emsp; -- Even the elements with the same value as pivot can be skipped, those greater or smaller than pivot may interchange their positions in the next iteration <br/>
     *  &emsp; -- Hence it is NOT a stable sort algorithm
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param useStartPivot if using start pivot to sort the elements in the array from the end index of the array
     * @param <T>
     * @return
     */
    @Override
    public <T extends Comparable<T>> T[] quickSort(T[] array, int startIndex, int endIndex, boolean isAsc, boolean useStartPivot) {
        return useStartPivot ? sortByUsingStartPivotAndDivideRecursively(array, startIndex, endIndex, isAsc) : sortByUsingEndPivotAndDivideRecursively(array, startIndex, endIndex, isAsc);
    }

    /**
     *  Conclusion of shell sort: <br/>
     *  -- Brief & Key: <br/>
     *  &emsp; -- It is an implementation of the idea of iterative algorithm and exhaustive algorithm <br/>
     *  -- Time Complexity: O(n^(1.3-2)) in average <br/>
     *  &emsp; --  <br/>
     *  -- Space Complexity: O(1) <br/>
     *  &emsp; -- It does not require extra space to store temporary sorted result <br/>
     *  &emsp; -- Hence it is an in-place algorithm <br/>
     *  -- Stability: stable <br/>
     *  &emsp; --  <br/>
     *  &emsp; -- Hence it is NOT a stable sort algorithm
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param insertToTheStart if inserting the element towards the start index of the array
     * @param <T>
     * @return
     */
    @Override
    public <T extends Comparable<T>> T[] insertSortWithDiminishingIncrement(T[] array, int startIndex, int endIndex, boolean isAsc, boolean insertToTheStart) {
        return insertToTheStart ? sortByInsertingToTheStartWithDiminishingIncrement(array, startIndex, endIndex, isAsc) : sortByInsertingToTheEndWithDiminishingIncrement(array, startIndex, endIndex, isAsc);
    }

    /**
     *  Conclusion of binary insert sort: <br/>
     *  -- Brief & Key: <br/>
     *  &emsp; -- It is an implementation of the idea of iterative algorithm and divide-and-conquer algorithm <br/>
     *  -- Time Complexity: O(n^2) in average <br/>
     *  &emsp; --  <br/>
     *  -- Space Complexity: O(1) <br/>
     *  &emsp; -- It does not require extra space to store temporary sorted result <br/>
     *  &emsp; -- Hence it is an in-place algorithm <br/>
     *  -- Stability: stable <br/>
     *  &emsp; -- The element to insert to the sorted sub-sequence will NOT be inserted at the index of the element with the same value (i.e., take its place and push it away) <br/>
     *  &emsp; -- Hence it is a stable sort algorithm
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param insertToTheStart if inserting the element towards the start index of the array
     * @param <T>
     * @return
     */
    @Override
    public <T extends Comparable<T>> T[] insertSortWithBinarySearch(T[] array, int startIndex, int endIndex, boolean isAsc, boolean insertToTheStart) {
        return insertToTheStart ? sortByInsertingToTheStartWithBinarySearch(array, startIndex, endIndex, isAsc) : sortByInsertingToTheEndWithBinarySearch(array, startIndex, endIndex, isAsc);
    }

    /**
     *  Conclusion of (straight) insert sort: <br/>
     *  -- Brief & Key: <br/>
     *  &emsp; -- It is an implementation of the idea of iterative algorithm and exhaustive algorithm <br/>
     *  -- Time Complexity: O(n^2) in average <br/>
     *  &emsp; --  <br/>
     *  -- Space Complexity: O(1) <br/>
     *  &emsp; -- It does not require extra space to store temporary sorted result <br/>
     *  &emsp; -- Hence it is an in-place algorithm <br/>
     *  -- Stability: stable <br/>
     *  &emsp; -- The element to insert to the sorted sub-sequence will NOT be inserted at the index of the element with the same value (i.e., take its place and push it away) <br/>
     *  &emsp; -- Hence it is a stable sort algorithm
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param insertToTheStart if inserting the element towards the start index of the array
     * @param <T>
     * @return
     */
    @Override
    public <T extends Comparable<T>> T[] insertSort(T[] array, int startIndex, int endIndex, boolean isAsc, boolean insertToTheStart) {
        return insertToTheStart ? sortByInsertingToTheStart(array, startIndex, endIndex, isAsc) : sortByInsertingToTheEnd(array, startIndex, endIndex, isAsc);
    }

    /**
     *  Conclusion of select sort: <br/>
     *  -- Brief & Key: <br/>
     *  &emsp; -- It is an implementation of the idea of iterative algorithm and exhaustive algorithm <br/>
     *  -- Time Complexity: O(n^2) in average <br/>
     *  &emsp; --  <br/>
     *  -- Space Complexity: O(1) <br/>
     *  &emsp; -- It does not require extra space to store temporary sorted result <br/>
     *  &emsp; -- Hence it is an in-place algorithm <br/>
     *  -- Stability: unstable <br/>
     *  &emsp; -- If there is any element that has the same value as the selected element staying in-between the swapped elements (i.e., the selected one and the swapped one), the stability will be broken <br/>
     *  &emsp; -- Hence it is NOT a stable sort algorithm
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param selectToTheStart if selecting the element towards the start index of the array
     * @param <T>
     * @return
     */
    @Override
    public <T extends Comparable<T>> T[] selectSort(T[] array, int startIndex, int endIndex, boolean isAsc, boolean selectToTheStart) {
        return selectToTheStart ? sortBySelectingToTheStart(array, startIndex, endIndex, isAsc) : sortBySelectingToTheEnd(array, startIndex, endIndex, isAsc);
    }

    /**
     *  Conclusion of bubble sort: <br/>
     *  -- Brief & Key:  <br/>
     *  &emsp; -- It is an implementation of the idea of iterative algorithm <br/>
     *  -- Time Complexity: O(n^2) in average <br/>
     *  &emsp; --  <br/>
     *  -- Space Complexity: O(1) <br/>
     *  &emsp; -- It does not require extra space to store temporary sorted result <br/>
     *  &emsp; -- Hence it is an in-place algorithm <br/>
     *  -- Stability: stable <br/>
     *  &emsp; -- The neighboring elements with the same value will NOT be swapped <br/>
     *  &emsp; -- Hence it is a stable sort algorithm
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param bubbleToTheStart if bubbling the element towards the start index of the array
     * @param <T>
     * @return
     */
    @Override
    public <T extends Comparable<T>> T[] bubbleSort(T[] array, int startIndex, int endIndex, boolean isAsc, boolean bubbleToTheStart) {
        return bubbleToTheStart ? sortByBubblingToTheStart(array, startIndex, endIndex, isAsc) : sortByBubblingToTheEnd(array, startIndex, endIndex, isAsc);
    }

    /*** Details of Radix Sort ***/
    /**
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param sortedElementTypeEnum what type of object to be sorted
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] sortByUsingLeastSignificantDigit(T[] array, int startIndex, int endIndex, boolean isAsc, SortedElementTypeEnum sortedElementTypeEnum) {
        return sortByIterativeBucketizationStartingFromLeastSignificantDigit(array, startIndex, endIndex, isAsc, sortedElementTypeEnum, getIterationForBucketization(array, startIndex, endIndex, sortedElementTypeEnum));
    }

    /**
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param sortedElementTypeEnum what type of object to be sorted
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] sortByUsingMostSignificantDigit(T[] array, int startIndex, int endIndex, boolean isAsc, SortedElementTypeEnum sortedElementTypeEnum) {
        return array;
    }

    /**
     *
     * @param array
     * @param startIndex
     * @param endIndex
     * @param isAsc
     * @param sortedElementTypeEnum
     * @param iterationForBucketization
     * @param <T>
     * @return
     */
    private <T extends Comparable<T>> T[] sortByIterativeBucketizationStartingFromLeastSignificantDigit(T[] array, int startIndex, int endIndex, boolean isAsc, SortedElementTypeEnum sortedElementTypeEnum, int iterationForBucketization) {
        checkEnum(sortedElementTypeEnum);
        switch (sortedElementTypeEnum) {
            case NUMBER:
                for (int i = 0; i < iterationForBucketization; i++) {
                    BigDecimal divisor = BigDecimal.valueOf(10);
                    LinkedList[] buckets = new LinkedList[10];
                    for (int j = startIndex; j <= endIndex; j++) {
                        BigDecimal element = new BigDecimal(array[j].toString());
                        // todo here to be continued
                        // ...
                    }
                }
                return array;
            case STRING:
                throw new BusinessException("字符串排序暂未实现");
            default:
                throw new BusinessException("未知的排序元素类型");
        }
    }

    private <T extends Comparable<T>> int getIterationForBucketization(T[] array, int startIndex, int endIndex, SortedElementTypeEnum sortedElementTypeEnum) {
        T maxElement = getMaxElement(array, startIndex, endIndex, sortedElementTypeEnum);
        int maxDigitNumber = getMaxDigitNumber(maxElement, sortedElementTypeEnum);
        return maxDigitNumber;
    }

    private <T extends Comparable<T>> T getMaxElement(T[] array, int startIndex, int endIndex, SortedElementTypeEnum sortedElementTypeEnum) {
        checkEnum(sortedElementTypeEnum);
        switch (sortedElementTypeEnum) {
            case NUMBER:
                T maxElement = array[startIndex];
                for (int i = startIndex; i <= endIndex; i++) {
                    T element = array[i];
                    if (element.compareTo(maxElement) > 0) {
                        maxElement = element;
                    }
                }
                return maxElement;
            case STRING:
                throw new BusinessException("字符串排序暂未实现");
            default:
                throw new BusinessException("未知的排序元素类型");
        }
    }

    private <T extends Comparable<T>> int getMaxDigitNumber(T maxElement, SortedElementTypeEnum sortedElementTypeEnum) {
        checkEnum(sortedElementTypeEnum);
        switch (sortedElementTypeEnum) {
            case NUMBER:
                /**
                 *  It may contain multiple scenario (e.g., the number with different number of redundant zeros, etc.)
                 *  -- Hence skip this algorithm for now (only reserves the simplest scenario)
                 */
                int length = String.valueOf(maxElement).length();
                return length;
            case STRING:
                throw new BusinessException("字符串排序暂未实现");
            default:
                throw new BusinessException("未知排序对象");
        }
    }

    /**
     *  Check specified enumeration
     *
     * @param sortedElementTypeEnum
     */
    private void checkEnum(SortedElementTypeEnum sortedElementTypeEnum) {
        if (sortedElementTypeEnum == null) {
            throw new BusinessException("请指定排序元素的类型");
        }
    }

    /*** Details of Heap Sort ***/
    /**
     *  Sort by heapifying the array to the start index of the array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] sortByHeapifyingToTheStart(T[] array, int startIndex, int endIndex, boolean isAsc) {
        return isAsc ? sortByMaxHeapifyingToTheStart(array, startIndex, endIndex) : sortByMinHeapifyingToTheStart(array, startIndex, endIndex);
    }

    /**
     *  Sort by heapifying the array to the end index of the array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] sortByHeapifyingToTheEnd(T[] array, int startIndex, int endIndex, boolean isAsc) {
        return isAsc ? sortByMinHeapifyingToTheEnd(array, startIndex, endIndex) : sortByMaxHeapifyingToTheEnd(array, startIndex, endIndex);
    }

    /**
     *  Sort by max-heapifying the array to the start index of the array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] sortByMaxHeapifyingToTheStart(T[] array, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        for (int i = endIndex; i > startIndex; i--) {
            // Max-heapify the array to the start index of the array
            array = maxHeapifyToTheStart(array, startIndex, i);
            /**
             *  Swap the element with max value to the end index of the array
             *  -- If the elements at start and end index have the same value, they will be swapped
             *     -- Hence the stability is not guaranteed
             */
            array = arrayService.swap(array, startIndex, i);
        }
        return array;
    }

    /**
     *  Sort by max-heapifying the array to the end index of the array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] sortByMaxHeapifyingToTheEnd(T[] array, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        for (int i = startIndex; i < endIndex; i++) {
            // Max-heapify the array to the end index of the array
            array = maxHeapifyToTheEnd(array, i, endIndex);
            /**
             *  Swap the element with max value to the start index of the array
             *  -- If the elements at start and end index have the same value, they will be swapped
             *     -- Hence the stability is not guaranteed
             */
            array = arrayService.swap(array, i, endIndex);
        }
        return array;
    }

    /**
     *  Sort by min-heapifying the array to the start index of the array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] sortByMinHeapifyingToTheStart(T[] array, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        for (int i = endIndex; i > startIndex; i--) {
            // Min-heapify the array to the start index of the array
            array = minHeapifyToTheStart(array, startIndex, i);
            /**
             *  Swap the element with max value to the end index of the array
             *  -- If the elements at start and end index have the same value, they will be swapped
             *     -- Hence the stability is not guaranteed
             */
            array = arrayService.swap(array, startIndex, i);
        }
        return array;
    }

    /**
     *  Sort by min-heapifying the array to the end index of the array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] sortByMinHeapifyingToTheEnd(T[] array, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        for (int i = startIndex; i < endIndex; i++) {
            // Min-heapify the array to the end index of the array
            array = minHeapifyToTheEnd(array, i, endIndex);
            /**
             *  Swap the element with max value to the end index of the array
             *  -- If the elements at start and end index have the same value, they will be swapped
             *     -- Hence the stability is not guaranteed
             */
            array = arrayService.swap(array, i, endIndex);
        }
        return array;
    }

    /**
     *  Max-heapify the array to the start index of the array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] maxHeapifyToTheStart(T[] array, int startIndex, int endIndex) {
        // Get the last parent index
        int lastParentIndex = getLastParentIndexToHeapifyToTheStart(startIndex, endIndex);
        /**
         *  Max-heapify the array to the start index of the array
         *  -- It ensures the element at start index of the array will be the one with max value
         */
        int offSetToTheStart = lastParentIndex - startIndex;
        for (int parentIndex = lastParentIndex; parentIndex >= startIndex; parentIndex--) {
            // Get max child index from children indexes
            ChildrenIndexesToHeapify childrenIndexes = getChildrenIndexesToHeapifyToTheStart(parentIndex, offSetToTheStart);
            int leftChildIndex = childrenIndexes.getLeftChildIndex();
            int rightChildIndex = childrenIndexes.getRightChildIndex();
            int maxChildIndex = getMaxChildIndexToHeapifyToTheStart(array, leftChildIndex, rightChildIndex, endIndex);
            // If the children have the same value, stay put
            if (array[parentIndex].compareTo(array[maxChildIndex]) < 0) {
                array = arrayService.swap(array, parentIndex, maxChildIndex);
            }
            offSetToTheStart--;
        }
        // Return the array that has been max-heapified to the start index of the array
        return array;
    }

    /**
     *  Max-heapify the array to the end index of the array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] maxHeapifyToTheEnd(T[] array, int startIndex, int endIndex) {
        // Get the first parent index
        int firstParentIndex = getFirstParentIndexToHeapifyToTheEnd(startIndex, endIndex);
        /**
         *  Max-heapify the array to the end index of the array
         *  -- It ensures the element at end index of the array will be the one with max value
         */
        int offSetToTheStart = endIndex - firstParentIndex;
        for (int parentIndex = firstParentIndex; parentIndex <= endIndex; parentIndex++) {
            // Get max child index from children indexes
            ChildrenIndexesToHeapify childrenIndexes = getChildrenIndexesToHeapifyToTheEnd(parentIndex, offSetToTheStart);
            int leftChildIndex = childrenIndexes.getLeftChildIndex();
            int rightChildIndex = childrenIndexes.getRightChildIndex();
            int maxChildIndex = getMaxChildIndexToHeapifyToTheEnd(array, leftChildIndex, rightChildIndex, startIndex);
            // If the children have the same value, stay put
            if (array[parentIndex].compareTo(array[maxChildIndex]) < 0) {
                array = arrayService.swap(array, parentIndex, maxChildIndex);
            }
            offSetToTheStart--;
        }
        // Return the array that has been max-heapified to the end index of the array
        return array;
    }

    /**
     *  Min-heapify the array to the start index of the array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] minHeapifyToTheStart(T[] array, int startIndex, int endIndex) {
        // Get the last parent index
        int lastParentIndex = getLastParentIndexToHeapifyToTheStart(startIndex, endIndex);
        /**
         *  Min-heapify the array to the start index of the array
         *  -- It ensures the element at start index of the array will be the one with min value
         */
        int offSetToTheStart = lastParentIndex - startIndex;
        for (int parentIndex = lastParentIndex; parentIndex >= startIndex; parentIndex--) {
            // Get min child index from children indexes
            ChildrenIndexesToHeapify childrenIndexes = getChildrenIndexesToHeapifyToTheStart(parentIndex, offSetToTheStart);
            int leftChildIndex = childrenIndexes.getLeftChildIndex();
            int rightChildIndex = childrenIndexes.getRightChildIndex();
            int minChildIndex = getMinChildIndexToHeapifyToTheStart(array, leftChildIndex, rightChildIndex, endIndex);
            // If the children have the same value, stay put
            if (array[parentIndex].compareTo(array[minChildIndex]) > 0) {
                array = arrayService.swap(array, parentIndex, minChildIndex);
            }
            offSetToTheStart--;
        }
        // Return the array that has been min-heapified to the start index of the array
        return array;
    }

    /**
     *  Min-heapify the array to the end index of the array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] minHeapifyToTheEnd(T[] array, int startIndex, int endIndex) {
        // Get the first parent index
        int firstParentIndex = getFirstParentIndexToHeapifyToTheEnd(startIndex, endIndex);
        /**
         *  Min-heapify the array to the end index of the array
         *  -- It ensures the element at end index of the array will be the one with min value
         */
        int offSetToTheStart = endIndex - firstParentIndex;
        for (int parentIndex = firstParentIndex; parentIndex <= endIndex; parentIndex++) {
            // Get min child index from children indexes
            ChildrenIndexesToHeapify childrenIndexes = getChildrenIndexesToHeapifyToTheEnd(parentIndex, offSetToTheStart);
            int leftChildIndex = childrenIndexes.getLeftChildIndex();
            int rightChildIndex = childrenIndexes.getRightChildIndex();
            int minChildIndex = getMinChildIndexToHeapifyToTheEnd(array, leftChildIndex, rightChildIndex, startIndex);
            // If the children have the same value, stay put
            if (array[parentIndex].compareTo(array[minChildIndex]) > 0) {
                array = arrayService.swap(array, parentIndex, minChildIndex);
            }
            offSetToTheStart--;
        }
        // Return the array that has been min-heapified to the end index of the array
        return array;
    }

    /**
     *  Get the index of last parent element of the array that will be heapified to the start index of the array
     *
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @return the index of last parent element of the array
     */
    private int getLastParentIndexToHeapifyToTheStart(int startIndex, int endIndex) {
        /**
         *  The index can be computed by:
         *  -- startIndex + (number_of_parent_elements - 1)
         *     -- Here -1 since what we are interested in is the index
         *  -- number_of_parent_elements = floor((endIndex - startIndex + 1) / 2)
         */
        double numberOfParentElements = Math.floor((endIndex - startIndex + 1) / 2);
        return startIndex + ((int) numberOfParentElements - 1);
    }

    /**
     *  Get the index of first parent element of the array that will be heapified to the end index of the array
     *
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @return the index of first parent element of the array
     */
    private int getFirstParentIndexToHeapifyToTheEnd(int startIndex, int endIndex) {
        /**
         *  The index can be computed by:
         *  -- endIndex - (number_of_parent_elements - 1)
         *     -- Here -1 since what we are interested in is the index
         *  -- number_of_parent_elements = floor((endIndex - startIndex + 1) / 2)
         */
        double numberOfParentElements = Math.floor((endIndex - startIndex + 1) / 2);
        return endIndex - ((int) numberOfParentElements - 1);
    }

    /**
     *  Get the child indexes based on parent index and offset to the start
     *
     * @param parentIndex the index of parent elements
     * @param parentOffsetToTheStart the offset of parent elements to the start index of the array
     * @return the indexes of children
     */
    private ChildrenIndexesToHeapify getChildrenIndexesToHeapifyToTheStart(int parentIndex, int parentOffsetToTheStart) {
        /**
         *  For example:
         *  [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] samples [2, 3, 4, 5, 6, 7, 8]
         *  4 -- (parentOffsetToTheStart = 2) --> 7; 8 --> 7 = 4 + 2 + 1; 5 = 4 + 2 + 2
         *  3 -- (parentOffsetToTheStart = 1) --> 5; 6 --> 5 = 3 + 1 + 1; 5 = 3 + 1 + 2
         *  2 -- (parentOffsetToTheStart = 0) --> 3; 4 --> 3 = 2 + 0 + 1; 4 = 2 + 0 + 2
         *  [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] samples [3, 4, 5, 6, 7, 8, 9]
         *  5 -- (parentOffsetToTheStart = 2) --> 8; 9 --> 8 = 5 + 2 + 1; 5 = 5 + 2 + 2
         *  4 -- (parentOffsetToTheStart = 1) --> 6; 7 --> 6 = 4 + 1 + 1; 7 = 3 + 1 + 2
         *  3 -- (parentOffsetToTheStart = 0) --> 4; 5 --> 4 = 3 + 0 + 1; 5 = 3 + 0 + 2
         */
        int leftChildIndex = parentIndex + parentOffsetToTheStart + 1;
        int rightChildIndex = parentIndex + parentOffsetToTheStart + 2;
        return ChildrenIndexesToHeapify.builder()
                .leftChildIndex(leftChildIndex)
                .rightChildIndex(rightChildIndex)
                .build();
    }

    /**
     *  Get the child indexes based on parent index and offset to the end
     *
     * @param parentIndex the index of parent elements
     * @param parentOffsetToTheEnd the offset of parent elements to the end index of the array
     * @return the indexes of children
     */
    private ChildrenIndexesToHeapify getChildrenIndexesToHeapifyToTheEnd(int parentIndex, int parentOffsetToTheEnd) {
        /**
         *  For example:
         *  [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] samples [2, 3, 4, 5, 6, 7, 8]
         *  6 -- (parentOffsetToTheEnd = 2) --> 3; 2 --> 3 = 6 - 2 - 1; 2 = 6 - 2 - 2
         *  7 -- (parentOffsetToTheEnd = 1) --> 5; 4 --> 5 = 7 - 1 - 1; 4 = 7 - 1 - 2
         *  8 -- (parentOffsetToTheEnd = 0) --> 7; 6 --> 7 = 8 - 0 - 1; 6 = 8 - 0 - 2
         *  [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] samples [3, 4, 5, 6, 7, 8, 9]
         *  7 -- (parentOffsetToTheEnd = 2) --> 4; 3 --> 4 = 7 - 2 - 1; 3 = 7 - 2 - 2
         *  8 -- (parentOffsetToTheEnd = 1) --> 6; 5 --> 6 = 8 - 1 - 1; 5 = 8 - 1 - 2
         *  9 -- (parentOffsetToTheEnd = 0) --> 8; 7 --> 8 = 9 - 0 - 1; 7 = 9 - 0 - 2
         */
        int leftChildIndex = parentIndex - parentOffsetToTheEnd - 1;
        int rightChildIndex = parentIndex - parentOffsetToTheEnd - 2;
        return ChildrenIndexesToHeapify.builder()
                .leftChildIndex(leftChildIndex)
                .rightChildIndex(rightChildIndex)
                .build();
    }

    /**
     *  Get the index of max child to heapify the array to the start index of the array
     *
     * @param array the array to be sorted
     * @param leftChildIndex the index of left child
     * @param rightChildIndex the index of right child
     * @param endIndex the end index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the index of child with max value
     */
    private <T extends Comparable<T>> int getMaxChildIndexToHeapifyToTheStart(T[] array, int leftChildIndex, int rightChildIndex, int endIndex) {
        if (rightChildIndex > endIndex) {
            return leftChildIndex;
        } else {
            return array[leftChildIndex].compareTo(array[rightChildIndex]) > 0 ? leftChildIndex : rightChildIndex;
        }
    }

    /**
     *  Get the index of max child to heapify the array to the end index of the array
     *
     * @param array the array to be sorted
     * @param leftChildIndex the index of left child
     * @param rightChildIndex the index of right child
     * @param startIndex the start index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the index of child with max value
     */
    private <T extends Comparable<T>> int getMaxChildIndexToHeapifyToTheEnd(T[] array, int leftChildIndex, int rightChildIndex, int startIndex) {
        if (rightChildIndex < startIndex) {
            return leftChildIndex;
        } else {
            return array[leftChildIndex].compareTo(array[rightChildIndex]) > 0 ? leftChildIndex : rightChildIndex;
        }
    }

    /**
     *  Get the index of min child to heapify the array to the start index of the array
     *
     * @param array the array to be sorted
     * @param leftChildIndex the index of left child
     * @param rightChildIndex the index of right child
     * @param endIndex the end index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the index of child with min value
     */
    private <T extends Comparable<T>> int getMinChildIndexToHeapifyToTheStart(T[] array, int leftChildIndex, int rightChildIndex, int endIndex) {
        if (rightChildIndex > endIndex) {
            return leftChildIndex;
        } else {
            return array[leftChildIndex].compareTo(array[rightChildIndex]) < 0 ? leftChildIndex : rightChildIndex;
        }
    }

    /**
     *  Get the index of min child to heapify the array to the end index of the array
     *
     * @param array the array to be sorted
     * @param leftChildIndex the index of left child
     * @param rightChildIndex the index of right child
     * @param startIndex the start index of the array
     * @param <T> the generic type that extends Comparable interface
     * @return the index of child with min value
     */
    private <T extends Comparable<T>> int getMinChildIndexToHeapifyToTheEnd(T[] array, int leftChildIndex, int rightChildIndex, int startIndex) {
        if (rightChildIndex < startIndex) {
            return leftChildIndex;
        } else {
            return array[leftChildIndex].compareTo(array[rightChildIndex]) < 0 ? leftChildIndex : rightChildIndex;
        }
    }

    /**
     *  The model to store the children indexes for heapification
     */
    @Data
    @Builder
    static class ChildrenIndexesToHeapify implements Serializable {

        /**
         *  The index of left child
         */
        private int leftChildIndex;

        /**
         *  The index of right child
         */
        private int rightChildIndex;
    }

    /*** Details of Merge Sort ***/
    /**
     *  Divide the array into two sub-sequences recursively and merge them bottom-up and left-to-right (through the idea of DFS)
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param tempArray the temporary array to store the sorted elements, which has the same size as array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] divideRecursivelyAndMerge(T[] array, int startIndex, int endIndex, boolean isAsc, T[] tempArray) {
        if (array == null) {
            return null;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (endIndex > array.length - 1) {
            endIndex = array.length - 1;
        }
        if (endIndex <= startIndex) {
            return array;
        }
        int midIndex = (startIndex + endIndex) / 2;
        array = divideRecursivelyAndMerge(array, startIndex, midIndex, isAsc, tempArray);
        array = divideRecursivelyAndMerge(array, midIndex + 1, endIndex, isAsc, tempArray);
        array = merge(array, startIndex, midIndex, endIndex, isAsc, tempArray);
        return array;
    }

    /**
     *  Merge the divided sub-sequences
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the divided sub-sequence
     * @param midIndex the middle index between startIndex and endIndex
     * @param endIndex the end index of the divided sub-sequence
     * @param isAsc if the elements of the array are sorted in ascending order\
     * @param tempArray the temporary array to store the sorted elements, which has the same size as array
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] merge(T[] array, int startIndex, int midIndex, int endIndex, boolean isAsc, T[] tempArray) {
        int leftPointer = startIndex;
        int rightPointer = midIndex + 1;
        int tempPointer = startIndex;
        while (leftPointer <= midIndex && rightPointer <= endIndex) {
            if (isAsc ? array[leftPointer].compareTo(array[rightPointer]) <= 0 : array[leftPointer].compareTo(array[rightPointer]) >= 0) {
                leftPointer = assignValueAndIncrementPointer(array, leftPointer, tempArray, tempPointer);
                tempPointer++;
            }
            if (isAsc ? array[leftPointer].compareTo(array[rightPointer]) > 0 : array[leftPointer].compareTo(array[rightPointer]) < 0) {
                rightPointer = assignValueAndIncrementPointer(array, rightPointer, tempArray, tempPointer);
                tempPointer++;
            }
        }
        while (leftPointer <= midIndex) {
            leftPointer = assignValueAndIncrementPointer(array, leftPointer, tempArray, tempPointer);
            tempPointer++;
        }
        while (rightPointer <= endIndex) {
            rightPointer = assignValueAndIncrementPointer(array, rightPointer, tempArray, tempPointer);
            tempPointer++;
        }
        array = Arrays.copyOf(tempArray, tempArray.length);
        return array;
    }

    /**
     *  Assign value and increment pointer
     *
     * @param array the array to be sorted
     * @param pointer the pointer of the array
     * @param tempArray the temporary array to store sorted elements
     * @param tempPointer the pointer of the temporary array
     * @param <T> the generic type that extends Comparable interface
     * @return the incremented pointer
     */
    private <T extends Comparable<T>> int assignValueAndIncrementPointer(T[] array, int pointer, T[] tempArray, int tempPointer) {
        tempArray[tempPointer] = array[pointer];
        pointer++;
        return pointer;
    }

    /*** Details of Quick Sort with Stability ***/
    /**
     *  Sort by using start pivot and divide the sorted array recursively with considering stability
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] sortByUsingStartPivotAndDivideRecursivelyWithStability(T[] array, int startIndex, int endIndex, boolean isAsc) {
        if (array == null) {
            return null;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (endIndex > array.length - 1) {
            endIndex = array.length - 1;
        }
        if (endIndex <= startIndex) {
            return array;
        }
        // Sort by using start pivot
        int lowIndex = startIndex;
        int highIndex = endIndex;
        int pivotIndex = lowIndex;
        boolean allElementsTraversed = false;
        while (lowIndex < highIndex) {
            /**
             *  Here will move the index where the element is of the same value
             *  -- Hence the elements with the same value do not have to swap, which maintains the stability
             *  -- But if all of the elements have the same value, the time complexity will be O(n^2)
             */
            while (isAsc ? array[highIndex].compareTo(array[pivotIndex]) >= 0 : array[highIndex].compareTo(array[pivotIndex]) <= 0) {
                highIndex--;
                if (highIndex == pivotIndex) {
                    allElementsTraversed = true;
                    break;
                }
            }
            /**
             *  If all elements (traced by high index) have been traversed (i.e., have compared with pivot and moved the high index towards the start index of the array within above while loop)
             *  -- In this case, the rest of current loop can be skipped
             *  -- Otherwise there are still elements left to be traversed, hence do the swapping, keep trace of the pivot, and increment the low index
             *     -- Why increment the low index: the element at original low index is swapped to high index (in which the pivot lies), which means it need not be compared with pivot
             */
            if (allElementsTraversed) {
                continue;
            } else {
                array = arrayService.swap(array, highIndex, pivotIndex);
                pivotIndex = highIndex;
                lowIndex++;
            }
            /**
             *  If the incremented low index has reached the pivot index
             *  -- In this case, the rest of current loop can be skipped
             */
            if (lowIndex == pivotIndex) {
                continue;
            }
            /**
             *  Similar as above that will move the index where the element is of the same value
             *  -- Here ensures that not all of the elements with the same value are moved along with only one direction
             *     -- E.g., if an array with elements [5, 5, 4, 5, 3, 4, 5, 5], then in this case low index can be moved towards the end index of the array, instead of standing still and waiting high index to be moved towards the start index of the array
             */
            while (isAsc ? array[lowIndex].compareTo(array[pivotIndex]) <= 0 : array[lowIndex].compareTo(array[pivotIndex]) >= 0) {
                lowIndex++;
                if (lowIndex == pivotIndex) {
                    allElementsTraversed = true;
                    break;
                }
            }
            // Similar as above that deals with the situation where all elements (traced by low index) have been traversed
            if (allElementsTraversed) {
                continue;
            } else {
                array = arrayService.swap(array, lowIndex, pivotIndex);
                pivotIndex = lowIndex;
                highIndex--;
            }
        }
        array = sortByUsingStartPivotAndDivideRecursivelyWithStability(array, startIndex, pivotIndex - 1, isAsc);
        array = sortByUsingStartPivotAndDivideRecursivelyWithStability(array, pivotIndex + 1, endIndex, isAsc);
        return array;
    }

    /**
     *  Sort by using end pivot and divide the sorted array recursively with considering stability
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] sortByUsingEndPivotAndDivideRecursivelyWithStability(T[] array, int startIndex, int endIndex, boolean isAsc) {
        if (array == null) {
            return null;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (endIndex > array.length - 1) {
            endIndex = array.length - 1;
        }
        if (endIndex <= startIndex) {
            return array;
        }
        int lowIndex = startIndex;
        int highIndex = endIndex;
        int pivotIndex = highIndex;
        boolean allElementsTraversed = false;
        while (lowIndex < highIndex) {
            /**
             *  Here will move the index where the element is of the same value
             *  -- Hence the elements with the same value do not have to swap, which maintains the stability
             *  -- But if all of the elements have the same value, the time complexity will be O(n^2)
             */
            while (isAsc ? array[lowIndex].compareTo(array[pivotIndex]) <= 0 : array[lowIndex].compareTo(array[pivotIndex]) >= 0) {
                lowIndex++;
                if (lowIndex == pivotIndex) {
                    allElementsTraversed = true;
                    break;
                }
            }
            /**
             *  If all elements (traced by low index) have been traversed (i.e., have compared with pivot and moved the low index towards the end index of the array within above while loop)
             *  -- In this case, the rest of current loop can be skipped
             *  -- Otherwise there are still elements left to be traversed, hence do the swapping, keep trace of the pivot, and decrement the high index
             *     -- Why decrement the high index: the element at original high index is swapped to low index (in which the pivot lies), which means it need not be compared with pivot
             */
            if (allElementsTraversed) {
                continue;
            } else {
                array = arrayService.swap(array, lowIndex, pivotIndex);
                pivotIndex = lowIndex;
                highIndex--;
            }
            /**
             *  If the decremented high index has reached the pivot index
             *  -- In this case, the rest of current loop can be skipped
             */
            if (highIndex == pivotIndex) {
                continue;
            }
            /**
             *  Similar as above that will move the index where the element is of the same value
             *  -- Here ensures that not all of the elements with the same value are moved along with only one direction
             *     -- E.g., if an array with elements [5, 5, 4, 5, 3, 4, 5, 5], then in this case low index can be moved towards the end index of the array, instead of standing still and waiting high index to be moved towards the start index of the array
             */
            while (isAsc ? array[highIndex].compareTo(array[pivotIndex]) >= 0 : array[highIndex].compareTo(array[pivotIndex]) <= 0) {
                highIndex--;
                if (highIndex == pivotIndex) {
                    allElementsTraversed = true;
                    break;
                }
            }
            // Similar as above that deals with the situation where all elements (traced by high index) have been traversed
            if (allElementsTraversed) {
                continue;
            } else {
                array = arrayService.swap(array, highIndex, pivotIndex);
                pivotIndex = highIndex;
                lowIndex++;
            }
        }
        array = sortByUsingEndPivotAndDivideRecursivelyWithStability(array, startIndex, pivotIndex - 1, isAsc);
        array = sortByUsingEndPivotAndDivideRecursivelyWithStability(array, pivotIndex + 1, endIndex, isAsc);
        return array;
    }

    /*** Details of Quick Sort ***/
    /**
     *  Sort by using start pivot and divide the sorted array recursively
     *
     * @param array
     * @param startIndex
     * @param endIndex
     * @param isAsc
     * @param <T>
     * @return
     */
    private <T extends Comparable<T>> T[] sortByUsingStartPivotAndDivideRecursively(T[] array, int startIndex, int endIndex, boolean isAsc) {
        if (array == null) {
            return null;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (endIndex > array.length - 1) {
            endIndex = array.length - 1;
        }
        if (endIndex <= startIndex) {
            return array;
        }
        int lowIndex = startIndex;
        int highIndex = endIndex;
        int pivotIndex = lowIndex;
        boolean elementsAllTraversed = false;
        while (lowIndex < highIndex) {
            /**
             *  Here does not move the index where the element has the same value as the pivot
             *  -- It ensures the low index can move towards the end index of the array, instead of standing still and waiting high index to move towards the start index of the array
             *  -- It ensures the index where low index and high index finally meet will get to the middle index of the array as close as possible, which will reduce the array division
             *     -- But for exchange, the stability of the array will not be guaranteed, since the elements with the same value will be swapped
             */
            while (isAsc ? array[highIndex].compareTo(array[pivotIndex]) > 0 : array[highIndex].compareTo(array[pivotIndex]) < 0) {
                highIndex--;
                if (highIndex == pivotIndex) {
                    elementsAllTraversed = true;
                    break;
                }
            }
            /**
             *  If all elements (traced by high index) have been traversed (i.e., have compared with pivot and moved the high index towards the start index of the array within above while loop)
             *  -- In this case, the rest of current loop can be skipped
             *  -- Otherwise there are still elements left to be traversed, hence do the swapping, keep trace of the pivot, and increment the low index
             *     -- Why increment the low index: the element at original low index is swapped to high index (in which the pivot lies), which means it need not be compared with pivot
             */
            if (elementsAllTraversed) {
                continue;
            } else {
                array = arrayService.swap(array, highIndex, pivotIndex);
                pivotIndex = highIndex;
                lowIndex++;
            }
            /**
             *  If the incremented low index has reached the pivot index
             *  -- In this case, the rest of current loop can be skipped
             */
            if (lowIndex == pivotIndex) {
                continue;
            }
            /**
             *  Similar as above that does not move the index where the element has the same value as the pivot
             *  -- Here ensures that the elements with the same value wil be swapped getting close to each other
             *     -- E.g., if an array with elements [5, 5, 5, 5, 5, 5, 5], then in this case low index and high index will meet at the middle index of this array
             */
            while (isAsc ? array[lowIndex].compareTo(array[pivotIndex]) < 0 : array[lowIndex].compareTo(array[pivotIndex]) > 0) {
                lowIndex++;
                if (lowIndex == pivotIndex) {
                    elementsAllTraversed = true;
                    break;
                }
            }
            // Similar as above that deals with the situation where all elements (traced by low index) have been traversed
            if (elementsAllTraversed) {
                continue;
            } else {
                array = arrayService.swap(array, lowIndex, pivotIndex);
                pivotIndex = lowIndex;
                highIndex--;
            }
        }
        array = sortByUsingStartPivotAndDivideRecursively(array, startIndex, pivotIndex - 1, isAsc);
        array = sortByUsingStartPivotAndDivideRecursively(array, pivotIndex + 1, endIndex, isAsc);
        return array;
    }

    /**
     *  Sort by using end pivot and divide the sorted array recursively
     *
     * @param array
     * @param startIndex
     * @param endIndex
     * @param isAsc
     * @param <T>
     * @return
     */
    private <T extends Comparable<T>> T[] sortByUsingEndPivotAndDivideRecursively(T[] array, int startIndex, int endIndex, boolean isAsc) {
        if (array == null) {
            return null;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (endIndex > array.length - 1) {
            endIndex = array.length - 1;
        }
        if (endIndex <= startIndex) {
            return array;
        }
        int lowIndex = startIndex;
        int highIndex = endIndex;
        int pivotIndex = highIndex;
        boolean allElementsTraversed = false;
        while (lowIndex < highIndex) {
            /**
             *  Here does not move the index where the element has the same value as the pivot
             *  -- It ensures the high index can move towards the start index of the array, instead of standing still and waiting low index to move towards the end index of the array
             *  -- It ensures the index where low index and high index finally meet will get to the middle index of the array as closed as possible, which will reduce the array division
             *     -- But for exchange, the stability of the array will not be guaranteed, since the elements with the same value will be swapped
             */
            while (isAsc ? array[lowIndex].compareTo(array[pivotIndex]) < 0 : array[lowIndex].compareTo(array[pivotIndex]) > 0) {
                lowIndex++;
                if (lowIndex == pivotIndex) {
                    allElementsTraversed = true;
                    break;
                }
            }
            /**
             *  If all elements (traced by low index) have been traversed (i.e., have compared with pivot and moved the low index towards the end index of the array within above while loop)
             *  -- In this case, the rest of current loop can be skipped
             *  -- Otherwise there are still elements left to be traversed, hence do the swapping, keep trace of the pivot, and decrement the high index
             *     -- Why decrement the high index: the element at original high index is swapped to low index (in which the pivot lies), which means it need not be compared with pivot
             */
            if (allElementsTraversed) {
                continue;
            } else {
                array = arrayService.swap(array, lowIndex, pivotIndex);
                pivotIndex = lowIndex;
                highIndex--;
            }
            /**
             *  If the decremented high index has reached the pivot index
             *  -- In this case, the rest of current loop can be skipped
             */
            if (highIndex == pivotIndex) {
                continue;
            }
            /**
             *  Similar as above that does not move the index where the element has the same value as the pivot
             *  -- Here ensures that the elements with the same value wil be swapped getting close to each other
             *     -- E.g., if an array with elements [5, 5, 5, 5, 5, 5, 5], then in this case low index and high index will meet at the middle index of this array
             */
            while (isAsc ? array[highIndex].compareTo(array[pivotIndex]) > 0 : array[highIndex].compareTo(array[pivotIndex]) < 0) {
                highIndex--;
                if (highIndex == pivotIndex) {
                    allElementsTraversed = true;
                    break;
                }
            }
            // Similar as above that deals with the situation where all elements (traced by high index) have been traversed
            if (allElementsTraversed) {
                continue;
            } else {
                array = arrayService.swap(array, highIndex, pivotIndex);
                pivotIndex = highIndex;
                lowIndex++;
            }
        }
        array = sortByUsingEndPivotAndDivideRecursively(array, startIndex, pivotIndex -1, isAsc);
        array = sortByUsingEndPivotAndDivideRecursively(array, pivotIndex + 1 , endIndex, isAsc);
        return array;
    }

    /*** Details of Shell Sort ***/
    /**
     *  Sort by inserting the element towards the start index of the array with the optimization of diminishing increment
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] sortByInsertingToTheStartWithDiminishingIncrement(T[] array, int startIndex, int endIndex, boolean isAsc) {
        if (array == null) {
            return null;
        }
        /**
         *  Diminish the increment that divides the array into different sub-arrays
         *  -- This process continues until the increment reaches zero (which means the increment used in the last effective traversal is 1)
         */
        for (int increment = array.length / 2; increment > 0; increment = increment / 2) {
            /**
             *  Use the increment to divide the arrays into different sub-arrays and process them iteratively
             *  -- The elements with the index interval of increment consist of a sub-array, each of which has a start index (i.e., startIndexOfSubArray)
             */
            for (int startIndexOfSubArray = startIndex; startIndexOfSubArray < startIndex + increment; startIndexOfSubArray++) {
                /**
                 *  For each sub-array, compute its end index (i.e., endIndexOfSubArray)
                 *  -- Hence the sub-array with start index (i.e., startIndexOfSubArray) and end index (i.e., endIndexOfSubArray) can be sorted by insert-sort algorithm
                 */
                int endIndexOfSubArray = getEndIndexOfSubArray(startIndexOfSubArray, startIndex, endIndex, increment);
                /**
                 *  Perform an insert-sort on each sub-array
                 *  -- Here the process is the same as insert-sort algorithm (hence here can also be optimized by binary search)
                 */
                for (int i = startIndexOfSubArray; i < endIndexOfSubArray; i = i + increment) {
                    // Get the element to insert
                    T elementToInsert = array[i + increment];
                    /**
                     *  Find the position to insert: default value is -1, which means no need to insert
                     *  -- If the element has the same value as the one to insert, break the loop that compares the element to insert and the elements in the sorted sub-sequence
                     *     -- It ensures the positions of the elements that have the same value will NOT be interchanged
                     */
                    int indexToInsert = -1;
                    for (int j = i; j >= startIndexOfSubArray; j = j - increment) {
                        if (isAsc ? elementToInsert.compareTo(array[j]) < 0 : elementToInsert.compareTo(array[j]) > 0) {
                            // Move the element towards the end index of the array
                            array[j + increment] = array[j];
                            indexToInsert = j;
                        } else {
                            break;
                        }
                    }
                    // Insert the element to the position if the position is found
                    if (indexToInsert != -1) {
                        array[indexToInsert] = elementToInsert;
                    }
                }
            }
        }
        return array;
    }

    /**
     *  Sort by inserting the element towards the end index of the array with the optimization of diminishing increment
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] sortByInsertingToTheEndWithDiminishingIncrement(T[] array, int startIndex, int endIndex, boolean isAsc) {
        if (array == null) {
            return null;
        }
        /**
         *  Diminish the increment that divides the array into different sub-arrays
         *  -- This process continues until the increment reaches zero (which means the increment used in the last effective traversal is 1)
         */
        for (int increment = array.length / 2; increment > 0; increment = increment / 2) {
            /**
             *  Use the increment to divide the arrays into different sub-arrays and process them iteratively
             *  -- The elements with the index interval of increment consist of a sub-array, each of which has a start index (i.e., startIndexOfSubArray)
             */
            for (int startIndexOfSubArray = startIndex; startIndexOfSubArray < startIndex + increment; startIndexOfSubArray++) {
                /**
                 *  For each sub-array, compute its end index (i.e., endIndexOfSubArray)
                 *  -- Hence the sub-array with start index (i.e., startIndexOfSubArray) and end index (i.e., endIndexOfSubArray) can be sorted by insert-sort algorithm
                 */
                int endIndexOfSubArray = getEndIndexOfSubArray(startIndexOfSubArray, startIndex, endIndex, increment);
                /**
                 *  Perform an insert-sort on each sub-array
                 *  -- Here the process is the same as insert-sort algorithm (hence here can also be optimized by binary search)
                 */
                for (int i = endIndexOfSubArray; i > startIndexOfSubArray; i = i - increment) {
                    // Get the element to insert
                    T elementToInsert = array[i - increment];
                    /**
                     *  Find the position to insert: default value is -1, which means no need to insert
                     *  -- If the element has the same value as the one to insert, break the loop that compares the element to insert and the elements in the sorted sub-sequence
                     *     -- It ensures the positions of the elements that have the same value will NOT be interchanged
                     */
                    int indexToInsert = -1;
                    for (int j = i; j <= endIndexOfSubArray; j = j + increment) {
                        if (isAsc ? elementToInsert.compareTo(array[j]) > 0 : elementToInsert.compareTo(array[j]) < 0) {
                            // Move the element towards the start index of the array
                            array[j - increment] = array[j];
                            indexToInsert = j;
                        } else {
                            break;
                        }
                    }
                    // Insert the element to the position if the position is found
                    if (indexToInsert != -1) {
                        array[indexToInsert] = elementToInsert;
                    }
                }
            }
        }
        return array;
    }

    /**
     *  Get the end index of the sub-array that are divided by the increment
     *
     * @param startIndexOfSubArray the start index of the sub-array divided by the increment
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param increment the increment used to divide the array into different sub-arrays
     * @return
     */
    private int getEndIndexOfSubArray(int startIndexOfSubArray, int startIndex, int endIndex, int increment) {
        int elementCount = endIndex - startIndex + 1;
        int maxMultiple = BigDecimal.valueOf(elementCount).divide(BigDecimal.valueOf(increment), RoundingMode.FLOOR).intValue();
        int endIndexOfSubArray = startIndexOfSubArray + maxMultiple * increment;
        return endIndexOfSubArray > endIndex ? startIndexOfSubArray + (maxMultiple - 1) * increment : endIndexOfSubArray;
    }

    /*** Details of Binary Insert Sort ***/
    /**
     *  Sort by inserting the element towards the start index of the array with the optimization of binary search
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] sortByInsertingToTheStartWithBinarySearch(T[] array, int startIndex, int endIndex, boolean isAsc) {
        if (array == null) {
            return null;
        }
        for (int i = startIndex; i < endIndex; i++) {
            // Get the element to insert
            T elementToInsert = array[i + 1];
            /*
             *  Find the position to insert with binary search
             *  -- Why indexToInsert is lowIndex
             *     -- When the elements need to be moved towards the end index of the array, it should be ensured there is no element omitted
             *     -- lowIndex covers more elements
             */
            int lowIndex = startIndex;
            int highIndex = i;
            while (lowIndex <= highIndex) {
                int midIndex = (lowIndex + highIndex) / 2;
                if (isAsc ? elementToInsert.compareTo(array[midIndex]) < 0 : elementToInsert.compareTo(array[midIndex]) > 0) {
                    highIndex = midIndex - 1;
                } else {
                    lowIndex = midIndex + 1;
                }
            }
            int indexToInsert = lowIndex;
            // Move the elements after the position to insert towards the end index of the array
            for (int j = i; j >= indexToInsert; j--) {
                array[j + 1] = array[j];
            }
            // Insert the element to the position
            array[indexToInsert] = elementToInsert;
        }
        return array;
    }

    /**
     *  Sort by inserting the element towards the end index of the array with the optimization of binary search
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] sortByInsertingToTheEndWithBinarySearch(T[] array, int startIndex, int endIndex, boolean isAsc) {
        if (array == null) {
            return null;
        }
        for (int i = endIndex; i > startIndex; i--) {
            // Get the element to insert
            T elementToInsert = array[i - 1];
            /*
             *  Find the position to insert with binary search
             *  -- Why indexToInsert is highIndex
             *     -- When the elements need to be moved towards the start index of the array, it should be ensured there is no element omitted
             *     -- highIndex covers more elements
             */
            int lowIndex = i;
            int highIndex = endIndex;
            while (lowIndex <= highIndex) {
                int midIndex = (lowIndex + highIndex) / 2;
                if (isAsc ? elementToInsert.compareTo(array[midIndex]) > 0 : elementToInsert.compareTo(array[midIndex]) < 0) {
                    lowIndex = midIndex + 1;
                } else {
                    highIndex = midIndex - 1;
                }
            }
            int indexToInsert = highIndex;
            // Move the elements before the position to insert towards the start index of the array
            for (int j = i; j <= indexToInsert; j++) {
                array[j - 1] = array[j];
            }
            // Insert the element to the position
            array[indexToInsert] = elementToInsert;
        }
        return array;
    }

    /*** Details of (Straight) Insert Sort ***/
    /**
     *  Sort by inserting the element towards the start index of the array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] sortByInsertingToTheStart(T[] array, int startIndex, int endIndex, boolean isAsc) {
        if (array == null) {
            return null;
        }
        for (int i = startIndex; i < endIndex; i++) {
            // Get the element to insert
            T elementToInsert = array[i + 1];
            /**
             *  Find the position to insert: default value is -1, which means no need to insert
             *  -- If the element has the same value as the one to insert, break the loop that compares the element to insert and the elements in the sorted sub-sequence
             *     -- It ensures the positions of the elements that have the same value will NOT be interchanged
             */
            int indexToInsert = -1;
            for (int j = i; j >= startIndex; j--) {
                if (isAsc ? elementToInsert.compareTo(array[j]) < 0 : elementToInsert.compareTo(array[j]) > 0) {
                    // Move the element towards the end index of the array
                    array[j + 1] = array[j];
                    indexToInsert = j;
                } else {
                    break;
                }
            }
            // Insert the element to the position if the position is found
            if (indexToInsert != -1) {
                array[indexToInsert] = elementToInsert;
            }
        }
        return array;
    }

    /**
     *  Sort by inserting the element towards the end index of the array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] sortByInsertingToTheEnd(T[] array, int startIndex, int endIndex, boolean isAsc) {
        if (array == null) {
            return null;
        }
        for (int i = endIndex; i > startIndex; i--) {
            // Get the element to insert
            T elementToInsert = array[i - 1];
            /**
             *  Find the position to insert: default value is -1, which means no need to insert
             *  -- If the element has the same value as the one to insert, break the loop that compares the element to insert and the elements in the sorted sub-sequence
             *     -- It ensures the positions of the elements that have the same value will NOT be interchanged
             */
            int indexToInsert = -1;
            for (int j = i; j <= endIndex; j++) {
                if (isAsc ? elementToInsert.compareTo(array[j]) > 0 : elementToInsert.compareTo(array[j]) < 0) {
                    // Move the element towards the start index of the array
                    array[j - 1] = array[j];
                    indexToInsert = j;
                } else {
                    break;
                }
            }
            // Insert the element to the position if the position is found
            if (indexToInsert != -1) {
                array[indexToInsert] = elementToInsert;
            }
        }
        return array;
    }

    /*** Details of Select Sort ***/
    /**
     *  Sort by selecting the element towards the start index of the array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] sortBySelectingToTheStart(T[] array, int startIndex, int endIndex, boolean isAsc) {
        if (array == null) {
            return null;
        }
        for (int i = startIndex; i < endIndex; i++) {
            /**
             *  Select the element to swap: default value is the start index of each iteration, which means no need to swap
             *  -- If the element has the same value as the selected element, skip updating the selected index and move the index
             */
            int selectedIndex = i;
            for (int j = i + 1; j <= endIndex; j++) {
                if (isAsc ? array[j].compareTo(array[selectedIndex]) < 0 : array[j].compareTo(array[selectedIndex]) > 0) {
                    selectedIndex = j;
                }
            }
            // Swap the selected element if the selected element is not the default one
            if (i != selectedIndex) {
                array = arrayService.swap(array, i, selectedIndex);
            }
        }
        return array;
    }

    /**
     *  Sort by selecting the element towards the end index of the array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] sortBySelectingToTheEnd(T[] array, int startIndex, int endIndex, boolean isAsc) {
        if (array == null) {
            return null;
        }
        for (int i = endIndex; i > startIndex; i--) {
            /**
             *  Select the element to swap: default value is the start index of each iteration, which means no need to swap
             *  -- If the element has the same value as the selected element, skip updating the selected index and move the index
             */
            int selectedIndex = i;
            for (int j = i - 1; j >= startIndex; j--) {
                if (isAsc ? array[j].compareTo(array[selectedIndex]) > 0 : array[j].compareTo(array[selectedIndex]) < 0) {
                    selectedIndex = j;
                }
            }
            // Swap the selected element if the selected element is not the default one
            if (i != selectedIndex) {
                array = arrayService.swap(array, i, selectedIndex);
            }
        }
        return array;
    }

    /*** Details of Bubble Sort ***/
    /**
     *  Sort by bubbling the element towards the start index of the array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] sortByBubblingToTheStart(T[] array, int startIndex, int endIndex, boolean isAsc) {
        if (array == null) {
            return null;
        }
        for (int i = 0; i < endIndex - startIndex; i++) {
            /**
             *  Swap the neighboring elements if necessary iteratively
             *  -- For the elements that have the same value, skip the comparison and move the index
             */
            for (int j = endIndex; j > startIndex + i; j--) {
                if (isAsc ? array[j].compareTo(array[j - 1]) < 0 : array[j].compareTo(array[j - 1]) > 0) {
                    array = arrayService.swap(array, j, j - 1);
                }
            }
        }
        return array;
    }

    /**
     *  Sort by bubbling the element towards the end index of the array
     *
     * @param array the array to be sorted
     * @param startIndex the start index of the array
     * @param endIndex the end index of the array
     * @param isAsc if the elements of the array are sorted in ascending order
     * @param <T> the generic type that extends Comparable interface
     * @return the sorted array
     */
    private <T extends Comparable<T>> T[] sortByBubblingToTheEnd(T[] array, int startIndex, int endIndex, boolean isAsc) {
        if (array == null) {
            return null;
        }
        for (int i = 0; i < endIndex - startIndex; i++) {
            /**
             *  Swap the neighboring elements if necessary iteratively
             *  -- For the elements that have the same value, skip the comparison and move the index
             */
            for (int j = startIndex; j < endIndex - i; j++) {
                if (isAsc ? array[j].compareTo(array[j + 1]) > 0 : array[j].compareTo(array[j + 1]) < 0) {
                    array = arrayService.swap(array, j, j + 1);
                }
            }
        }
        return array;
    }
}
