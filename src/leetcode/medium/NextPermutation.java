package leetcode.medium;

import java.util.Arrays;

//rearranges lexicographically next greater permutation of numbers, if not sort in ascending order
public class NextPermutation {
   
    public static void main(String[] args) {
        int[] input1 = { 1, 2, 3 }; // 1, 3, 2
        int[] input2 = { 3, 2, 1 }; // 1, 2, 3
        int[] input3 = { 1, 1, 5 }; // 1, 5, 1
        System.out.println(Arrays.toString(nextPermutation(input3)));
    }

    public static int[] nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
        return nums;
    }

    private static void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
