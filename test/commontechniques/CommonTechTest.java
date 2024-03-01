package commontechniques;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CommonTechTest {

    @Test
    void getTotalBetweenRanges() {
        PrefixSum prefixSum = new PrefixSum();
        int[] nums = {3, 0, -2, 6, -3, 2};
        List<int[]> queries = new ArrayList<>();
        queries.add(new int[]{0,2});
        queries.add(new int[]{2,5});
        queries.add(new int[]{0,5});
        Assertions.assertEquals(10, prefixSum.getTotal(nums, queries));
    }

    @Test
    void getMaximumSubarray() {
        KadaneAlgo kadaneAlgo = new KadaneAlgo();
        int[] nums = {-2, 2, 5, -11, 6};
        Assertions.assertEquals(7, kadaneAlgo.maximumSubarray(nums));
    }

    @Test
    void getMaximumSubarrayAllNegatives() {
        KadaneAlgo kadaneAlgo = new KadaneAlgo();
        int[] nums = {-2, -3, -5, -11, -6};
        Assertions.assertEquals(-2, kadaneAlgo.maximumSubarray(nums));
    }
}
