#include <vector>
#include <map>

using namespace std;

int solve(const vector<int> &A)
{
    int n = A.size();
    if (n < 3)
        return n;

    vector<vector<int>> dp(n, vector<int>(n, -1));
    map<int, int> pos;

    for (int i = 0; i < n; i++)
    {
        for (int j = i + 1; j < n; j++)
        {
            dp[i][j] = 2;
            int dif = A[j] - A[i];
            int need = 2 * A[i] - A[j];
            if (pos.count(need) == 0)
                continue;

            dp[i][j] = max(dp[i][j], dp[pos[need]][i] + 1);
        }
        pos[A[i]] = i;
    }

    int ans = 2;
    for (int i = 0; i < n; i++)
    {
        for (int j = i + 1; j < n; j++)
        {
            ans = max(ans, dp[i][j]);
        }
    }
    return ans;
}

int wrostLongestAP(const vector<int> &A)
{
    int s = A.size(), ans = 2;
    if (s <= 1)
        return s;
    for (int i = 0; i < s; i++)
    {
        int first = A[i];
        for (int j = i + 1; j < s; j++)
        {
            long long last = A[j];
            long long cd = A[j] - A[i];
            int len = 2;
            for (int k = j + 1; k < s; k++)
            {
                if (A[k] - last == cd)
                {
                    last = A[k];
                    len++;
                }
            }
            ans = max(ans, len);
        }
    }
    return ans;
}

/*
package DynamicProgramming;
import java.util.Arrays;
import java.util.HashMap;

// https://www.interviewbit.com/problems/longest-arithmetic-progression/
// https://www.geeksforgeeks.org/longest-arithmetic-progression-dp-35/
public class LongestAP {

    public static void main(String[] args) {
        System.out.println(longestAP(new int[] { 2, 4, 10, 11, 16, 22, 28 })); // 4, 10, 16, 22, 28
        System.out.println(longest_AP_Odd_Len_Sorted(new int[] { 2, 4, 10, 11, 16, 22, 28 })); // 4, 10, 16, 22, 28
        System.out.println(longest_GP_Odd_Len_Sorted(new int[] { 4, 10, 12, 36 })); // 4*3^0 , 4*3^1 , 4*3^2
    }

    public int longestAP_Best(int[] A) {
        int n = A.length;
        if (n <= 2)
            return n;
        Arrays.sort(A);
        int dp[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][n - 1] = 2;
        }
        int max = 0;
        for (int i = n - 2; i >= 0; i--) {
            int j = i - 1;
            int k = i + 1;
            while (j >= 0 && k < n) {
                if (A[j] + A[k] < 2 * A[i])
                    k++;
                else if (A[j] + A[k] > 2 * A[i]) {
                    dp[j][i] = 2;
                    j--;
                } else {
                    dp[j][i] = dp[i][k] + 1;  // j -> i -> k 
                    max = Math.max(max, dp[j][i]);
                    j--;
                    k++;
                }
            }
            if (k == n) {   // Update all the above col when k reach extreme end 
                while (j >= 0) {
                    dp[j][i] = 2;
                    j--;
                }
            }
        }
        return max;
    }

     //Generic -works for both even odd
     //Time Complexity : 0(N2)
    public static int longestAP(int[] A) {
        int len = A.length;
        if(len == 1){
            return 1;
        }
        HashMap<Integer, Integer>[] dp = new HashMap[len];
        int maxVal = 1;

        for (int i = 0; i < len; i++) {
            HashMap<Integer, Integer> curMap = new HashMap<>();
            for (int j = 0; j < i; j++) {
                HashMap<Integer, Integer> preMap = dp[j];
                int diff = A[i] - A[j];
                int newVal = preMap.getOrDefault(diff, 0) + 1;
                curMap.put(diff, newVal);
                maxVal = Math.max(maxVal, newVal);
            }
            dp[i] = curMap;
        }
        return maxVal + 1;
    }

    //Works Only when 1) odd length and 2) Given arr is sorted 
    //Time Complexity : 0(N2) (expands round centre)
    public static int longest_AP_Odd_Len_Sorted(int[] arr) {
        int max = 0;
        for (int j = 1; j < arr.length; j++) {
            int i = j - 1;
            int k = j + 1;
            
            while (i >= 0 && k < arr.length) {
                if (2 * arr[j] == (arr[k] + arr[i])) { // 2b = a+c
                    if ((k - i) > max)
                        max = k - i;
                    k++;
                    i--;
                } else if (2 * arr[j] > arr[i] + arr[k]) {
                    k++;
                } else {
                    i--;
                }
            }
        }
        return max+1;
    }

    //Works Only when 1) odd length and 2) Given arr is sorted
    public static int longest_GP_Odd_Len_Sorted(int[] arr) {
        int max = 0;
        for (int j = 1; j < arr.length; j++) {
            int i = j - 1;
            int k = j + 1;
            while (i >= 0 && k < arr.length) {
                if (arr[j] * arr[j] == arr[i] * arr[k]) { // b^2 = a*c
                    if ((k - i) > max)
                        max = k - i;
                    k++;
                    i--;
                } else if (arr[j] * arr[j] > arr[i] * arr[k]) {
                    k++;
                } else {
                    i--;
                }
            }
        }
        return max+1;
    }
}
*/