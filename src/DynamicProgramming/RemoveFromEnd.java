package DynamicProgramming;

//https://www.geeksforgeeks.org/remove-minimum-elements-either-side-2min-max/
public class RemoveFromEnd {

    // Target: 2* min > max
    public static int removeFromEnd(int[] input) {
        return removeFromEnd(input, 0, input.length);
    }

    // T(n) = 2T(n-1) + O(n) ===> O(n x 2^n)
    public static int removeFromEnd(int[] input, int low, int high) {
        if (low > high) {
            return input.length;
        }
        int min = min(input, low, high);
        int max = max(input, low, high);
        if (2 * min > max) {
            return 0;
        }
        return Math.min(removeFromEnd(input, low, high - 1), removeFromEnd(input, low + 1, high)) + 1;
    }

    private static int min(int[] input, int low, int high) {
        int min = Integer.MAX_VALUE;
        for (int i = low; i < high; i++) {
            if (input[i] < min) {
                min = input[i];
            }
        }
        return min;
    }

    private static int max(int[] input, int low, int high) {
        int max = Integer.MIN_VALUE;
        for (int i = low; i < high; i++) {
            if (input[i] > max) {
                max = input[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] input = { 4, 5, 100, 9, 10, 11, 12, 15, 200 };
        System.out.println(removeFromEnd(input));
        System.out.println(removeFromEndDynamic(input));
    }

    // 0(n^3)
    public static int removeFromEndDynamic(int[] input) {
        int n = input.length;
        int[][] T = new int[n][n];
        for (int len = 1; len <= input.length; len++) {
            for (int i = 0; i < input.length - len + 1; i++) {
                int j = i + len - 1;
                int min = min(input, i, j);
                int max = max(input, i, j);

                if (2 * min > max) {
                    T[i][j] = 0;
                } else {
                    T[i][j] = Math.min(T[i + 1][j], T[i][j - 1]) + 1;
                }
            }
        }
        return T[0][n - 1] + 1;
    }

    // 0(n2)
    static int minRemovalsDP(int arr[], int n) {

        int longest_start = -1, longest_end = 0;
        for (int start = 0; start < n; start++) {
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

            for (int end = start; end < n; end++) {
                int val = arr[end];
                if (val < min) {
                    min = val;
                }
                if (val > max) {
                    max = val;
                }
                if (2 * min <= max) {
                    break;
                }
                if (end - start > longest_end - longest_start || longest_start == -1) {
                    longest_start = start;
                    longest_end = end;
                }
            }
        }
        if (longest_start == -1) {
            return n;
        }
        return (n - (longest_end - longest_start + 1));
    }
}
