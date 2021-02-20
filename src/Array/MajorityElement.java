package Array;


//https://www.geeksforgeeks.org/majority-element/

import java.util.Arrays;

public class MajorityElement {

    public static void main(String[] args) {

        int[] arr = {1, 1, 2, 1, 3, 5, 1};
        findMajorityBruteForce(arr);
        findMajoritybySorting(arr);
        findMajorityByOptimised(arr);
    }

    //0(n2)
    private static void findMajorityBruteForce(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            for (int j = i; j < arr.length; j++) {
            if(arr[i] == arr[j]){
                count++;
                if (count > (arr.length / 2)) {
                    System.out.println("The majority number is : " + arr[i]);
                    return;
                }
              }
            }
        }
        System.out.println("No Majority");
    }

    //0(nlogn)
    private static void findMajoritybySorting(int[] arr) {
        Arrays.sort(arr);
        int count = 1, i = 0, j = 1;

        while (j < arr.length) {
            if (arr[i] == arr[j]) {
                count++;
                j++;
                if (count > (arr.length / 2)) {
                    System.out.println("The majority number is : " + arr[i]);
                    return;
                }
            } else {
                count = 1;
                i = j;
            }
        }
        System.out.println("No Majority");
    }

    //0(n)   Using Moore’s Voting Algorithm
    private static void findMajorityByOptimised(int[] arr) {
        int majorNumber = findMajority(arr);
        if (majorNumber == -1) {
            System.out.println("No Majority");
            return;
        }
        int count = 0;
        for (int value : arr) {   // recheck
            if (value == majorNumber) {
                count++;
            }
        }
        if (count > (arr.length / 2)) {
            System.out.println("The majority number is : " + majorNumber);
        } else {
            System.out.println("No Majority");
        }
    }
    
    
    private static int findMajority(int[] arr) {
        int count = 1;
        int majorityTill = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == majorityTill)
                count++;
            else {
                count--;
                if (count == 0) {
                    majorityTill = arr[i];
                    count = 1;
                }
            }
        }
        return count == 0 ? -1 : majorityTill;
    }
}
