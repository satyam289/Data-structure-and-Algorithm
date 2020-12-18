package DynamicProgramming;

import java.util.Arrays;

public class PaintHouse {

    private static class Pair {
        int firstindex;
        int secondindex;
    }

    public static void main(String[] args) {
        int[][] input = {{1, 2, 1}, {1, 4, 5}, {2, 6, 1}, {3, 3, 2}};
        //  System.out.println(painthouse(input));
        System.out.println(dynamic(input));
    }

    public static int painthouse(int[][] cost) {
        int[][] table = new int[cost.length][cost[0].length];
        return painthouse(cost, 0, -1, table);
    }

    public static int painthouse(int[][] cost, int house, int pre, int[][] table) {
        if (cost.length == 0 || cost[0].length == 0)
            return -1;
        if (house == cost.length)
            return 0;
        int min = Integer.MAX_VALUE;
        int val;
        for (int j = 0; j < cost[0].length; j++) {
            if (j == pre)
                continue;
            if (table[house][j] != 0) {
                val = table[house][j];
            } else {
                val = cost[house][j] + painthouse(cost, house + 1, j, table);
                table[house][j] = val;
            }
            min = Math.min(min, val);
        }
        return min;
    }

    private static int dynamic(int[][] cost) {
        if (cost.length == 0 || cost[0].length == 0)
            return -1;
        int[][] table = new int[cost.length][cost[0].length];
        for (int j = 0; j < cost[0].length; j++) {
            table[0][j] = cost[0][j];
        }
        System.out.println(Arrays.toString(table[0]));
        for (int i = 1; i < cost.length; i++) {
            Pair p = findMinSecondMin(table[i - 1]);
            for (int j = 0; j < cost[0].length; j++) {
                table[i][j] = cost[i][j];
                if (j == p.firstindex)
                    table[i][j] += table[i - 1][p.secondindex];
                else
                    table[i][j] += table[i - 1][p.firstindex];
            }
            System.out.println(Arrays.toString(table[i]));
        }
        // System.out.println(Arrays.toString(table[cost.length-2]));
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < cost[0].length; j++) {
            min = Math.min(min, table[cost.length - 1][j]);
        }
        return min;
    }

    private static Pair findMinSecondMin(int[] h) {
        Pair p = new Pair();
        if (h[0] < h[1]) {
            p.secondindex = 1;
            p.firstindex = 0;
        } else {
            p.secondindex = 0;
            p.firstindex = 1;
        }

        for (int i = 2; i < h.length; i++) {
            if (h[i] < h[p.firstindex]) {
                p.secondindex = p.firstindex;
                p.firstindex = i;
            } else if (h[i] < h[p.secondindex]) {
                p.secondindex = i;
            }
        }
        return p;
    }

}

 