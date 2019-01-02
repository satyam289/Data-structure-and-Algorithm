package Array;

//Kadane Algorithm

public class LargestSumSubArray {

    public static void main(String[] args) {
        int[] a = {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println("Maximum contiguous sum is " + maxSubArraySum(a));
    }

    static int maxSubArraySum(int a[]) {

        int max_so_far = Integer.MIN_VALUE, current_sum = 0;

        for (int i = 0; i < a.length; i++) {

            current_sum = current_sum + a[i];
            if (current_sum > max_so_far)
                max_so_far = current_sum;
            if (current_sum < 0)
                current_sum = 0;
        }
        return max_so_far;
    }
}
