package Math_Bit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class PerfectFactorSum {

    public static void main(String[] args) {
        // System.out.println("the no of perfect square sum item required
        // "+getPerfectsum(15));
        System.out.println(numSquaresUsingDP(101));
    }

    // bfs apporach
    public static int getPerfectsum(int n) {
        List<Integer> list = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> q = new LinkedList<>();
        for (int i = 1; (i * i) <= n; i++) {
            int square = i * i;
            if (square == n) {
                return 1;
            }

            list.add(square);
            q.add(square);
            visited.add(square);
        }
        int count = 1;
        while (q.size() > 0) {
            count = count + 1;
            int intialsize = q.size();
            for (int i = 1; i <= intialsize; i++) {
                int number = q.poll();
                for (int j = 1; j <= list.size(); j++) {
                    int sum = list.get(j - 1) + number;
                    if (sum == n) {
                        // System.out.println(i+" "+list.get(j-1));
                        return count;
                    }
                    if (!visited.contains(sum)) {
                        visited.add(sum);
                        q.add(sum);
                    }
                    if (sum > n)
                        break;
                }
            }
        }
        return count;
    }

    // dynammic 2nd apporach
    public static int numSquaresUsingDP(int n) {

        int count = (int) Math.floor(Math.sqrt(n));
        System.out.println(count);
        int[] t = new int[n + 1];
        t[0] = 0;
        for (int i = 1; i < t.length; i++) {
            t[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= count; j++) {
                if (i < j * j) {
                    break;
                }
                t[i] = Math.min(t[i], t[i - j * j] + 1);
            }
        }
        System.out.println(Arrays.toString(t));
        return t[n];
    }
}
