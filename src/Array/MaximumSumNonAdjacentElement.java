package Array;

//https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
public class MaximumSumNonAdjacentElement {

    public static int findMaxSum(int[] arr) {
        int incl = arr[0];
        int excl = 0;

        for (int i = 1; i < arr.length; i++) {
            int temp = incl;
            incl = Math.max(incl, excl + arr[i]);
            excl = Math.max(excl, temp);
        }
        return Math.max(incl, excl);
    }

    public static void main(String[] args) {
        int[] input = { 4, 1, 1, 4, 2, 1 };
        System.out.println(findMaxSum(input));
    }
}
