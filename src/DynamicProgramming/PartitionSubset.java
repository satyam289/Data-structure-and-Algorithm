package DynamicProgramming;

import java.util.HashMap;

//https://leetcode.com/problems/partition-equal-subset-sum/
/*
Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
*/
public class PartitionSubset {
    
    public static boolean canPartition(int[] nums) {
        if (nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        return canPartitionRec(nums, 0, 0, sum / 2, new HashMap<String, Boolean>());
    }

    private static boolean canPartitionRec(int[] nums, int index, int currentSum, int total,
            HashMap<String, Boolean> map) {
        String str = index + " " + currentSum;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        if (currentSum > total || index >= nums.length) {
            return false;
        }
        if (currentSum == total) {
            return true;
        }
        boolean found = canPartitionRec(nums, index + 1, currentSum, total, map)
                || canPartitionRec(nums, index + 1, currentSum + nums[index], total, map);
        map.put(str, found);
        return found;
    }

    public static void main(String[] args) {
        int[] arr = { 5, 5, 1, 11 };
        System.out.println(canPartition(arr));
    }
}
