package commontechniques;

import java.util.Arrays;
import java.util.OptionalInt;

public class KadaneAlgo {

    /**
     * check curr sum, if it became negative set it to zero
     * @param nums array
     */
    public int maximumSubarray(int[] nums) {
        OptionalInt max = Arrays.stream(nums).max();
        if (max.isPresent() && max.getAsInt() < 0)
            return max.getAsInt();
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
