package DynamicProgramming;

public class EggDrop {

    public static int eggDrop(int eggs, int floors) {

        int[][] table = new int[eggs + 1][floors + 1];
        for (int i = 0; i <= floors; i++) {
            table[1][i] = i;
        }
        for (int i = 2; i <= eggs; i++) {
            for (int j = 1; j <= floors; j++) {
                if (i > j) {
                    table[i][j] = table[i - 1][j];
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int k = i; k <= j; k++) {
                        min = Math.min(min, Math.max(table[i - 1][k - 1], table[i][j - k]) + 1);
                    }
                    table[i][j] = min;
                }
            }
        }
        return table[eggs][floors];
    }

    public static void main(String[] args) {
        System.out.println(eggDrop(2, 5));
    }
}
