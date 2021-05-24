package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

/* If he reach same point (starting point) by travelling all other points exactly once
* If we permute all combination, choose the least min distance covered . Time complexity : n!
* Ref Video: https://www.youtube.com/watch?v=-JjA4BLQyqE
* Held Karp Method (DP)
* Time complexity - O(2^n * n^2)
* Space complexity - O(2^n)
*/
public class TravellingSaleMan {

    private static class Index {
        int currentVertex;
        Set<Integer> vertexSet;

        public Index(int i, Set<Integer> set) {
            this.currentVertex = i;
            this.vertexSet = set;
        }

        @Override
        public int hashCode() {
            int result = currentVertex;
            result = 31 * result + (vertexSet != null ? vertexSet.hashCode() : 0);
            return result;
        }

        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Index index = (Index) obj;
            if (currentVertex != index.currentVertex) {
                return false;
            }
            return !(vertexSet != null ? !vertexSet.equals(index.vertexSet) : index.vertexSet != null);
        }
    }

    public static int minCost(int[][] distance) {

        Map<Index, Integer> minCostDp = new HashMap<>();
        Map<Index, Integer> parent = new HashMap<>();
        List<Set<Integer>> allset = generateCombination(distance.length - 1);

        for (Set<Integer> set : allset) {
            for (int currVertex = 1; currVertex < distance.length; currVertex++) {

                if (set.contains(currVertex)) {
                    continue;
                }
                Index index = new Index(currVertex, set);
                int minCost = Integer.MAX_VALUE;
                int minPreVextex = 0;
                Set<Integer> copySet = new HashSet<>(set);

                for (int preVertex : set) {
                    int cost = distance[preVertex][currVertex] + getCost(copySet, preVertex, minCostDp);
                    if (cost < minCost) {
                        minCost = cost;
                        minPreVextex = preVertex;
                    }
                }
                if (set.size() == 0) {
                    minCost = distance[0][currVertex];
                }
                minCostDp.put(index, minCost);
                parent.put(index, minPreVextex);
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < distance.length; i++) {
            set.add(i);
        }
        int min = Integer.MAX_VALUE;
        int preVertex = -1;
        Set<Integer> copySet = new HashSet<>(set);
        for (int k : set) {
            int cost = distance[k][0] + getCost(copySet, k, minCostDp);
            if (cost < min) {
                min = cost;
                preVertex = k;
            }
        }
        parent.put(new Index(0, set), preVertex);
        printTour(parent, distance.length);
        return min;
    }

    private static int getCost(Set<Integer> set, int preVertex, Map<Index, Integer> minCostDP) {
        set.remove(preVertex);
        int cost = minCostDP.get(new Index(preVertex, set));
        set.add(preVertex);
        return cost;
    }

    private static void printTour(Map<Index, Integer> parent, int totalVextex) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < totalVextex; i++) {
            set.add(i);
        }
        Integer start = 0;
        Deque<Integer> stack = new LinkedList<>();
        while (true) {
            stack.push(start);
            set.remove(start);
            start = parent.get(new Index(start, set));
            if (start == null) {
                break;
            }
        }
        StringJoiner joiner = new StringJoiner("-->");
        stack.forEach(v -> joiner.add(String.valueOf(v)));
        System.out.println(joiner.toString());
    }

    private static List<Set<Integer>> generateCombination(int n) {
        int[] input = new int[n];
        for (int i = 0; i < input.length; i++) {
            input[i] = i + 1;
        }
        List<Set<Integer>> allset = new ArrayList<>();
        int[] result = new int[input.length];
        generateCombination(input, 0, 0, allset, result);
        Collections.sort(allset, new Comparator<Set<Integer>>() {
            @Override
            public int compare(Set<Integer> o1, Set<Integer> o2) {
                return o1.size() - o2.size();
            }
        });
        return allset;
    }

    private static void generateCombination(int[] input, int start, int pos, List<Set<Integer>> allset, int[] result) {
        if (pos == input.length) {
            return;
        }
        Set<Integer> set = createSet(result, pos);
        allset.add(set);
        for (int i = start; i < input.length; i++) {
            result[pos] = input[i];
            generateCombination(input, i + 1, pos + 1, allset, result);
        }
    }

    private static Set<Integer> createSet(int[] result, int pos) {
        if (pos == 0) {
            return new HashSet<>();
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < pos; i++) {
            set.add(result[i]);
        }
        return set;
    }

    public static void main(String[] args) {
        int[][] graph = { { 0, 1, 15, 6 }, { 2, 0, 7, 3 }, { 9, 6, 0, 12 }, { 10, 4, 8, 0 } };
        System.out.println("The minimum cost " + minCost(graph));
    }
}
