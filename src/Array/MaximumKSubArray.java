package Array;

public class MaximumKSubArray {

    public static void main(String[] args) {
        int[] arr = {11, -8, 16, 24, -2, 3};
        findMaxSumOfKSize(arr, 3);
    }

    //sliding window
    private static void findMaxSumOfKSize(int[] arr, int k) {

        int sum = 0, maxsum;
        int startindex = 0;
        for (int i = 0; i < k; i++)
            sum += arr[i];
        maxsum = sum;
        for (int i = k; i < arr.length; i++) {
            sum = sum + arr[i] - arr[i - k];
            if (sum > maxsum) {
                maxsum = sum;
                startindex = i - k;
            }
        }
        System.out.println("The max sum :" + maxsum + " start at index :" + startindex);
    }
}
