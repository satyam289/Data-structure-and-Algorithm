package DynamicProgramming;

//https://www.interviewbit.com/problems/evaluate-expression-to-true/
public class WaysToEvaluateTrue {

    int MOD = 1003;
    public int countTrue(String A) {
        StringBuilder symbols = new StringBuilder();
        StringBuilder operators = new StringBuilder();

        for (int i = 0; i < A.length(); i++) {
            char ch = A.charAt(i);
            if (ch == 'T' || ch == 'F')
                symbols.append(ch);
            else
                operators.append(ch);
        }
        return count(symbols.toString().toCharArray(), operators.toString().toCharArray()) % MOD;
    }

    private int count(char exp[], char op[]) {

        int n = exp.length;
        int t[][] = new int[n][n];
        int f[][] = new int[n][n];
        for (int i = 0; i < n; ++i) {
            if (exp[i] == 'T')
                t[i][i] = 1;
            else
                f[i][i] = 1;
        }
        for (int gap = 1; gap < n; ++gap) {
            for (int i = 0, j = gap; j < n; ++i, ++j) {
                
                for (int g = 0; g < gap; ++g) {
                    int k = i + g;
                    switch (op[k]) {
                        case '&':
                            t[i][j] += t[i][k] * t[k + 1][j];
                            f[i][j] += t[i][k] * f[k + 1][j] + f[i][k] * t[k + 1][j] + f[i][k] * f[k + 1][j];
                            break;
                        case '|':
                            t[i][j] += t[i][k] * t[k + 1][j] + t[i][k] * f[k + 1][j] + f[i][k] * t[k + 1][j];
                            f[i][j] += f[i][k] * f[k + 1][j];
                            break;
                        case '^':
                            t[i][j] += t[i][k] * f[k + 1][j] + f[i][k] * t[k + 1][j];
                            f[i][j] += t[i][k] * t[k + 1][j] + f[i][k] * f[k + 1][j];
                            break;
                    }
                    t[i][j] %= MOD;
                    f[i][j] %= MOD;
                }
            }
        }
        return t[0][n - 1];
    }

    //RECURSIVE
    public int countTrue2(String A) {

        int[][][] dp = new int[A.length()][A.length()][2];
        for (int i = 0; i < A.length(); i++)
            for (int j = 0; j < A.length(); j++)
                for (int k = 0; k < 2; k++)
                    dp[i][j][k] = -1;

        return solve(0, A.length() - 1, dp, A, 1);
    }

    private int solve(int start, int end, int[][][] dp, String A, int truth) {
        // Truth 1 means 'T' or true value
        // Truth 0 means 'F' or false value
        if (start == end) {
            if (A.charAt(start) == 'T') {
                if (truth == 1)
                    return 1;
                return 0;
            } else if (A.charAt(start) == 'F') {
                if (truth == 1)
                    return 0;
                return 1;
            }
        }

        if (dp[start][end][truth] != -1)
            return dp[start][end][truth];

        int ans = 0;

        for (int i = start; i <= end; i++) {

            if (A.charAt(i) == '|') {
                int left_true = solve(start, i - 1, dp, A, 1);
                int left_false = solve(start, i - 1, dp, A, 0);

                int right_true = solve(i + 1, end, dp, A, 1);
                int right_false = solve(i + 1, end, dp, A, 0);

                int comb = 0;

                if (truth == 1) {
                    comb = ((left_true * right_true) % 1003 + (left_true * right_false) % 1003 + (left_false * right_true) % 1003) % 1003;
                } else {
                    comb = (left_false * right_false) % 1003;
                }

                ans = (ans + comb) % 1003;

            } else if (A.charAt(i) == '&') {
                int left_true = solve(start, i - 1, dp, A, 1);
                int left_false = solve(start, i - 1, dp, A, 0);

                int right_true = solve(i + 1, end, dp, A, 1);
                int right_false = solve(i + 1, end, dp, A, 0);

                if (truth == 1) {
                    ans = (ans + (left_true * right_true) % 1003) % 1003;
                } else {
                    int comb = ((left_false * right_false) % 1003 + (left_true * right_false) % 1003 + (left_false * right_true) % 1003) % 1003;
                    ans = (ans + comb) % 1003;
                }

            } else if (A.charAt(i) == '^') {
                int left_true = solve(start, i - 1, dp, A, 1);
                int left_false = solve(start, i - 1, dp, A, 0);

                int right_true = solve(i + 1, end, dp, A, 1);
                int right_false = solve(i + 1, end, dp, A, 0);

                int comb = 0;

                if (truth == 1) {
                    comb = ((left_true * right_false) % 1003 + (left_false * right_true) % 1003) % 1003;
                } else {
                    comb = ((left_true * right_true) % 1003 + (left_false * right_false) % 1003) % 1003;
                }
                ans = (ans + comb) % 1003;
            }
        }
        dp[start][end][truth] = ans;
        return ans;
    }
}
