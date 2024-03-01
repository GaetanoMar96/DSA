package commontechniques;

import java.util.Arrays;
import java.util.List;

public class PrefixSum {

    /**
     * Get total sum between 2 indices in an array using prefix sum
     * @param nums input nums
     * @param queries queries
     * @return total
     */
    public int getTotal(int[] nums, List<int[]> queries) {
        int[] prefix = new int[nums.length];
        Arrays.fill(prefix, 0);
        prefix[0] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            prefix[i] = nums[i] + prefix[i-1];
        }
        int tot = 0;
        for (int[] query : queries) {
            if (query[0] == 0) {
                tot += prefix[query[1]];
            } else {
                tot += prefix[query[1]] - prefix[query[0]] + nums[query[0]];
            }
        }
        return tot;
    }
}
