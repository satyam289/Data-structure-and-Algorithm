package Array;

import java.util.ArrayList;

public class TrappingOfRainWater {

    public static void main(String[] args) {
        int[] arr = {3, 1, 0, 2, 0, 4, 2, 1};
        calculateWaterTrap(arr);
        calculateWaterTrap2(arr);
        calculateWaterTrapOptimised(arr);
    }

    //Time Complexity : 0(n^2)
    public static void calculateWaterTrap(int[] arr) {    // O(n2)
        int leftmax = 0, rightmax = 0, sum = 0;

        for (int i = 0; i < arr.length; i++) {           //getting Max left
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > leftmax)
                    leftmax = arr[j];
            }

            for (int j = i + 1; j < arr.length; j++) {    //getting Max right
                if (arr[j] > rightmax)
                    rightmax = arr[j];
            }

            int min = Math.min(rightmax, leftmax);
            if ((min - arr[i]) > 0) {                              // min = 0 , water will fall down (can't be stored)
                 sum += (min - arr[i]); // amount of water is stored
            }     
            leftmax = 0;
            rightmax = 0;
        }
        System.out.println("Unit of water trapped by apporach 1: " + sum);
    }
    
    //Time Complexity : 0(n) Space Complexity : 0(n)
    public static void calculateWaterTrap2(int[] arr) {  //0(n)
        int[] leftArray = new int[arr.length];
        int[] rightArray = new int[arr.length];
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];
            if (value > max) {
                leftArray[i] = max;
                max = value;
                continue;
            }
            leftArray[i] = max;
        }
        max = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            int value = arr[i];
            if (value > max) {
                rightArray[i] = max;
                max = value;
                continue;
            }
            rightArray[i] = max;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            int value = Math.min(rightArray[i], leftArray[i]);
            if (value - arr[i] > 0) {   //skipping value if current height is greater, so negative
                 sum += value - arr[i];
            } 
        }
        System.out.println("Unit of water trapped 2nd Apporach " + sum);
    }
    
    //Time Complexity : 0(n) Space Complexity : 0(1)
    public static void calculateWaterTrapOptimised(int[] arr){
        int low = 0;
        int high = arr.length -1 ;
        int leftMax = 0;
        int rightMax = 0;
        int sum = 0;
        while(low < high) {
            if(arr[low] < arr[high]) {
                if(arr[low] > leftMax){
                    leftMax = arr[low];
                }else{
                    sum += (leftMax - arr[low]);
                }
                low++;
            } else {
                if(arr[high] > rightMax){
                    rightMax = arr[high];
                }else{
                    sum += (rightMax - arr[high]);
                }
                high--;
            }
        }
        System.out.println("The unit of water trapped optimised " + sum);
    }    
}

//https://www.interviewbit.com/problems/container-with-most-water/
class MostWaterContainer {

    // Brute Force Approach
    public int maxArea(ArrayList<Integer> A) {
        int max_area = 0;
        for (int i = 0; i < A.size(); i++) {
            for (int j = i + 1; j < A.size(); j++) {
                int base_diff = Math.abs(j - i);
                int minHeight = Math.min(A.get(j), A.get(i));
                max_area = Math.max(max_area, base_diff * minHeight);
            }
        }
        return max_area;
    }

    // TIme Complexity: 0(n)
    public int maxAreaOPtimised(ArrayList<Integer> A) {
        int i =0;
        int j= A.size() -1;
        int max_area = 0;
        while( i < j ){
            int minHeight = Math.min(A.get(i), A.get(j));
            int area = minHeight*(j-i);
            max_area = Math.max(max_area, area);
            if(A.get(i) < A.get(j)){
                i++;
            }else{
                j--;
            }
        }
        return max_area;
    }
}
}