package Miscellaneous;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/*
Given :
Heights : A list of heights of N persons standing in a queue
Infronts : A list of numbers corresponding to each person (P) that gives the number of persons who are taller than P and standing in front of P
You need to return list of actual order of persons’s height
*/
//https://leetcode.com/problems/queue-reconstruction-by-height/
//https://www.interviewbit.com/problems/order-of-people-heights/
public class OrderPeopleHeight {

    public static ArrayList<int[]> order(int[][] people) {

        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                }
                return b[0] - a[0];
            }
        });
        ArrayList<int[]> result = new ArrayList<>();
        for (int[] ppl : people) {
            result.add(ppl[1], ppl);
        }
        return result;
    }

    public static void main(String[] atgs) {
        int[][] input = { { 5, 0 }, { 3, 1 }, { 2, 2 }, { 6, 0 }, { 1, 3 }, { 4, 2 } };
        ArrayList<int[]> result = order(input);
        for (int[] newPosition : result) {
            System.out.print("{" + newPosition[0] + "," + newPosition[1] + "}");
        }
    }
}
