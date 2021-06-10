#include <vector>
#define mod ((int)1e9 +7)
#define ll long long 

using namespace std;

/*       |f(n+1)   f(n) |   | 1  0 | (n -1)
  f(n) = |              | = |      |
         |f(n)    f(n-1)|   | 1  1 |
*/
class fibonacci
{ 
private:
    vector<vector<ll>> multiply(vector<vector<ll>> &A, vector<vector<ll>> &B)
    {
        //vector<vector<ll>> C(A);
        vector<vector<ll>> C(2, vector<ll>(2, 0));
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                long long sum = 0;
                for (int k = 0; k < 2; k++)
                    sum = (sum + (A[i][k] * B[k][j]) % mod) % mod;
                C[i][j] = sum;
            }
        }
        return C;
    }

    void mat_power(vector<vector<ll>> &mat, int n)
    {
        if (n == 1)
            return;
        mat_power(mat, n / 2);
        mat = multiply(mat, mat);
        if (n & 1)
        {
            vector<vector<ll>> m = {{1, 1}, {1, 0}};
            mat = multiply(mat, m);
        }
        return;
    }

public:
    int solve(int n)
    {
        vector<vector<long long>> mat = {{1, 1}, {1, 0}};
        if (n == 0)
            return 0;
        mat_power(mat, n - 1);
        return mat[0][0];
    }
};
/*
package Math_Bit;

import java.util.HashMap;
import java.util.Map;

// https://www.interviewbit.com/problems/find-nth-fibonacci/
public class FibonacciSeries {
    
    static final int mod = (int) 1e9 + 7;

    public static int fibOptimal(int A) {
        long[][] base = {{ 1, 1 }, 
                         { 1, 0 }};

        long[][] result = power(base, A - 1);
        return (int) result[0][0] % mod;
    }

    private static long[][] power(long[][] mat, int n) {
        if (n == 1) {
            return mat;
        }
        long[][] new_mat = power(mat, n / 2);

        new_mat = mul(new_mat, new_mat);
        if (n % 2 == 1) {
            new_mat = mul(new_mat, mat); // execute only once @here base mat = { { 1, 1 }, { 1, 0 } }
        }
        return new_mat;
    }

    private static long[][] mul(long[][] first, long[][] second) {
        long[][] res = new long[first.length][second[0].length];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {

                for (int k = 0; k < first[0].length; k++) {
                    res[i][j] += first[i][k] * second[k][j];
                }
            }
        }
        return res;
    }

    public static int fib1(int A, Map<Integer, Integer> map) {  // recursion with memorization
        if (A == 0) {
            return 0;
        }
        if (A == 1) {
            return 1;
        }
        if (map.containsKey(A)) {
            return map.get(A);
        }
        int val = (fib1(A - 1, map) + fib1(A - 2, map))% 1000000007;
        map.put(A, val);
        return val;
    }
    
    //Time Complexity:  0(n)
    public static int fib2(int n) {
        int a = 0;
        int b = 1;
        if (n == 0) {
            return a;
        }
        if (n == 1) {
            return b;
        }

        int sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return sum;
    }

    public static void main(String [] args){
        System.out.println(fib1(10, new HashMap<>()));
        System.out.println(fib2(10));
        System.out.println(fibOptimal(10));
        System.out.println(fibfast(10));
    }

    public static int fibfast(int n) {
        long ans[][] = new long[][]{{1,1},{1,0}}; 
        if(n == 0)
            return 0;
        power2(ans, n - 1);
        return (int)(ans[0][0] % mod);
    }

    private static  void power2(long [][]ans , int n) {
        if(n == 1 || n == 1)
            return ;
        long [][]temp = new long[][]{{1,1},{1,0}}; 
        power2(ans, n / 2);
        multiply2(ans, ans);
        if(n % 2 == 1)
            multiply2(ans, temp);
    }

    private static void multiply2(long F[][], long M[][]) {
        long x = F[0][0] % mod * M[0][0] % mod + F[0][1] % mod * M[1][0] % mod;
        long y = F[0][0] % mod * M[0][1] % mod + F[0][1] % mod * M[1][1] % mod;
        long z = F[1][0] % mod * M[0][0] % mod + F[1][1] % mod * M[1][0] % mod;
        long w = F[1][0] % mod * M[0][1] % mod + F[1][1] % mod * M[1][1] % mod;
        F[0][0] = x % mod;
        F[0][1] = y % mod;
        F[1][0] = z % mod;
        F[1][1] = w % mod;
    }
}

*/