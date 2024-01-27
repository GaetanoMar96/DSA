package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortAlgo {

    /**
     * check curr min and put curr min at the beginning of the array
     * @param nums array to sort
     */
    public static void selectionSort(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            int currmin = i;
            for (int j = i+1; j < nums.length; j++) {
                if(nums[j] < nums[currmin])
                    currmin = j;
            }
            int temp = nums[i];
            nums[i] = nums[currmin];
            nums[currmin] = temp;
        }
    }

    /**
     * check where to place the key value, moving elements greater of key to the right
     * @param nums array to sort
     */
    public static void insertionSort(int[] nums) {
        for(int i = 1; i < nums.length; i++) {
            int key = nums[i];
            int j = i - 1;
            while (j >= 0 && key < nums[j]) {
                nums[j+1] = nums[j];
                j--;
            }
            nums[j+1] = key;
        }
    }

    /**
     * Perform a quick sort, find a pivot, call the method recursively onf left and right
     * and then merge the 2 portions together
     * @param nums array to sort
     */
    public static int[] quickSort(int[] nums) {
        if (nums.length <= 1) {
            return nums;
        }
        int pivot = nums[0];
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < pivot) {
                left.add(nums[i]);
            } else {
                right.add(nums[i]);
            }
        }
        int[] sortedleft = quickSort(left.stream().mapToInt(v->v).toArray());
        int[] sortedright = quickSort(right.stream().mapToInt(v->v).toArray());
        int[] sorted = new int[sortedleft.length + 1 + sortedright.length];
        System.arraycopy(sortedleft, 0, sorted, 0, sortedleft.length);
        sorted[sortedleft.length] = pivot;
        System.arraycopy(sortedright, 0, sorted, sortedleft.length + 1, sortedright.length);
        return sorted;
    }

    /**
     * Perform a merge sort if l < h continue to call recursively on Left and right of the array
     * and then merge the 2 portions together
     * @param l lower bound
     * @param h upper bound
     * @param nums array to sort
     */
    public static void mergeSort(int l, int h, int[] nums) {
        if (l < h) {
            int mid = (l+h) / 2;
            mergeSort(l, mid, nums);
            mergeSort(mid + 1, h, nums);
            merge(l, h, mid, nums);
        }
    }

    public static void merge(int l, int h, int mid, int[] nums) {
        int[] left = Arrays.copyOfRange(nums, l, mid + 1);
        int[] right = Arrays.copyOfRange(nums, mid+1, h + 1);
        int i = 0, j = 0, k = l;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                nums[k] = left[i];
                i++;
            } else {
                nums[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < left.length) {
            nums[k] = left[i];
            i++;
            k++;
        }
        while (j < right.length) {
            nums[k] = right[j];
            j++;
            k++;
        }
    }
}
