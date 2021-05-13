package StackQueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

// https://leetcode.com/problems/frog-jump/
public class FrogJump {

    // Brute Force
    public static boolean canCross(int[] stones) {

        int n = stones.length;
        List<Integer>[] jumphistory = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            jumphistory[i] = new ArrayList<>();
        }
        jumphistory[0].add(0);

        for (int i = 0; i < stones.length; i++) {
            for (int j = 0; j < i; j++) {
                for (int previousJump : jumphistory[j]) {
                    if (stones[j] + previousJump + 1 == stones[i] || stones[j] + previousJump == stones[i]
                            || stones[j] + previousJump - 1 == stones[i]) {
                        jumphistory[i].add(stones[i] - stones[j]); // storing either of previousJump - 1, previousJump,
                                                                   // previousJump + 1
                        break;
                    }
                }
            }
        }
        return jumphistory[n - 1].size() > 0;
    }

    // Better
    public static boolean canCross2(int[] stones) {
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        int n = stones.length;

        for (int i = 0; i < n; i++) {
            map.put(stones[i], new HashSet<>());
        }

        map.get(stones[0]).add(0);

        for (int i = 0; i < n; i++) {

            int stone = stones[i];
            HashSet<Integer> set = map.get(stone);

            for (int val : set) {

                if (map.containsKey(stone + val - 1)) {
                    if (val - 1 > 0) {
                        map.get(stone + val - 1).add(val - 1);
                    }
                }
                if (map.containsKey(stone + val)) {
                    if (val > 0) {
                        map.get(stone + val).add(val);
                    }
                }
                if (map.containsKey(stone + val + 1)) {
                    map.get(stone + val + 1).add(val + 1);
                }
            }
        }
        return map.get(stones[n - 1]).size() > 0;
    }

    // optimised
    public static boolean canCross3(int[] stones) {
        HashSet<Integer> stonesPosition = new HashSet<>();
        for (int stone : stones) {
            stonesPosition.add(stone);
        }
        int lastStone = stones[stones.length - 1];
        Stack<Integer> positions = new Stack<>();
        Stack<Integer> preJump = new Stack<>();
        positions.add(0);
        preJump.add(0);
        while (!positions.isEmpty() || !preJump.isEmpty()) {
            int position = positions.pop();
            int jump = preJump.pop();

            for (int i = jump - 1; i <= jump + 1; i++) {
                if (i <= 0) {
                    continue;
                }
                int nextPosition = position + i;
                if (nextPosition == lastStone) {
                    return true;
                } else if (stonesPosition.contains(nextPosition)) {
                    positions.add(nextPosition);
                    preJump.add(i);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] stones = { 0, 1, 3, 5, 6, 8, 12, 17 };
        System.out.print(canCross(stones));
        System.out.print(canCross2(stones));
        System.out.print(canCross3(stones));
    }
}