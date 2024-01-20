package kadane;

import java.util.Arrays;

public class KadaneAlgo {

    public static void main(String[] args) {
        int[] nums = {-1,-4,-7,-8,-2,-5};
        //if all elements are negative get max one
        int max = Arrays.stream(nums).max().getAsInt();
        if (max < 0) {
            System.out.println(max);
            return;
        }
        System.out.println(maximumSubarray(nums));
    }

    /**
     * check curr sum, if it became negative set it to zero
     * @param nums array
     */
    public static int maximumSubarray(int[] nums) {
        int currsum = 0;
        int maxsum = Integer.MIN_VALUE;
        for (int num : nums) {
            if (currsum + num < 0) {
                currsum = 0;
                continue;
            }
            currsum = currsum + num;
            maxsum = Math.max(maxsum, currsum);
        }
        return maxsum;
    }
}
