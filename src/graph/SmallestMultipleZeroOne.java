package graph;

import java.util.ArrayDeque;
import java.util.Deque;

//https://www.interviewbit.com/problems/smallest-multiple-with-0-and-1/
public class SmallestMultipleZeroOne {
    public String multiple(int A) {
        if (A == 1)
            return "1";
        Deque<Integer> queue = new ArrayDeque<>();

        int[] parent = new int[A];
        int[] state = new int[A];
        int[] steps = new int[] { 0, 1 };
        for (int i = 0; i < A; i++) {
            parent[i] = -1;
            state[i] = -1;
        }
        queue.offer(1);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (curr == 0) {
                break;
            }
            for (int step : steps) {
                int next = (curr * 10 + step) % A;
                if (parent[next] == -1) {
                    parent[next] = curr;
                    state[next] = step;
                    queue.offer(next);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int s = 0;
        while (s != 1) {
            sb.append(String.valueOf(state[s]));
            s = parent[s];
        }
        sb.append("1");
        return sb.reverse().toString();
    }
}
