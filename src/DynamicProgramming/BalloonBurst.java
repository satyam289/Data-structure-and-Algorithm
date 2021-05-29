package DynamicProgramming;

public class BalloonBurst {
    private static class Cell {
        int cost;
        int lastBrust;

        public Cell(int cost, int lastBrust) {
            this.cost = cost;
            this.lastBrust = lastBrust;
        }
    }

    public static void ballonBurst(int[] arr) {
        int n = arr.length;
        Cell[][] table = new Cell[n][n];

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                int maxCost = 0;
                for (int k = i; k <= j; k++) {

                    int left = (k == i) ? 0 : table[i][k - 1].cost;
                    int right = (k == j) ? 0 : table[k + 1][j].cost;

                    int leftNum = (i == 0) ? 1 : arr[i - 1];
                    int rightNum = (j == n - 1) ? 1 : arr[j + 1];
                    int cost = left + leftNum * arr[k] * rightNum + right;
                    if (cost > maxCost) {
                        maxCost = cost;
                        table[i][j] = new Cell(maxCost, k);
                    }
                }
            }
        }
        System.out.println("The last Balloon to brust is " + arr[table[0][n - 1].lastBrust]);
        System.out.println("The maximum cost to brust balloon is " + table[0][n - 1].cost);
    }

    public static int maxBalloonBrustRec(int[] arr) {
        int maxCost = 0;
        for (int i = 0; i < arr.length; i++) {
            int leftNum = (i == 0) ? 1 : arr[i - 1];
            int rightNum = (i == arr.length - 1) ? 1 : arr[i + 1];
            int cost = leftNum * arr[i] * rightNum + maxBalloonBrustRec(formNewArray(arr, i));
            if (cost > maxCost) {
                maxCost = cost;
            }
        }
        return maxCost;
    }

    private static int[] formNewArray(int[] input, int ignoreIndex) {
        int[] newArr = new int[input.length - 1];
        int index = 0;
        for (int i = 0; i < input.length; i++) {
            if (i != ignoreIndex) {
                newArr[index++] = input[i];
            }
        }
        return newArr;
    }

    public static void main(String[] args) {
        int[] arr = { 3, 1, 5, 8 };
        ballonBurst(arr);
        System.out.println("The recusive apporach : " + maxBalloonBrustRec(arr));
    }
}
