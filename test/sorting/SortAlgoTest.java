package sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SortAlgoTest {

    @Test
    void selectionSortOk() {
        int[] nums = {12,5,7,1,4,7,6,8,9,10};
        int[] sortedNums = getSortedNums(nums);
        SortAlgo.selectionSort(nums);
        Assertions.assertArrayEquals(sortedNums, nums);
    }

    @Test
    void insertionSortOk() {
        int[] nums = {12,5,7,1,4,7,6,8,9,10};
        int[] sortedNums = getSortedNums(nums);
        SortAlgo.insertionSort(nums);
        Assertions.assertArrayEquals(sortedNums, nums);
    }

    @Test
    void bubbleSortOk() {
        int[] nums = {12,5,7,1,4,7,6,8,9,10};
        int[] sortedNums = getSortedNums(nums);
        SortAlgo.bubbleSort(nums);
        Assertions.assertArrayEquals(sortedNums, nums);
    }

    @Test
    void quickSortOk() {
        int[] nums = {12,5,7,1,4,7,6,8,9,10};
        int[] sortedNums = getSortedNums(nums);
        Assertions.assertArrayEquals(sortedNums, SortAlgo.quickSort(nums));
    }

    @Test
    void mergeSortOk() {
        int[] nums = {12,5,7,1,4,7,6,8,9,10};
        int[] sortedNums = getSortedNums(nums);
        SortAlgo.mergeSort(0, nums.length - 1, nums);
        Assertions.assertArrayEquals(sortedNums, nums);
    }

    private int[] getSortedNums(int[] nums) {
        return Arrays.stream(nums).sorted().toArray();
    }
}
