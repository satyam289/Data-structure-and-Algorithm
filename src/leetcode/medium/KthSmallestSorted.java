package leetcode.medium;

import java.util.*;

public class KthSmallestSorted {
    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 5, 9 },
                { 10, 11, 13 },
                { 12, 13, 15 }
        };
        int k = 8;
        KthSmallestSorted obj = new KthSmallestSorted();
        int value = obj.kthSmallest(matrix, k);
        System.out.println(value);

        System.out.println(obj.kthSmallestBinarySearch(matrix, k));
    }

    private int kthSmallest(int[][] matrix, int k) {

        int n = matrix.length;
        Queue<Element> minHeap = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            minHeap.offer(new Element(matrix[0][i], 0, i));
        }
        int res = 0;
        for (int i = 0; i < k; i++) {
            Element ele = minHeap.poll();
            res = ele.number;
            if (ele.row + 1 < n) {
                minHeap.offer(new Element(matrix[ele.row + 1][ele.col], ele.row + 1, ele.col));
            }
        }
        return res;
    }

    class Element implements Comparable<Element> {
        int number;
        int row;
        int col;

        public Element(int number, int row, int col) {
            this.number = number;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Element e) {
            return Integer.compare(this.number, e.number);
        }
    }

    public int kthSmallestBinarySearch(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];

        int mid;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            int j = n - 1;
            int count = 0;
            for (int i = 0; i < n; i++) {
                while (j >= 0 && matrix[i][j] > mid) {
                    j--;
                }
                count += j + 1;
            }
            if (count < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
