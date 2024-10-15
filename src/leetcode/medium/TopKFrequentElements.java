package leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class TopKFrequentElements {

    public static void main(String[] args) {
        int[] nums = { 1, 1, 1, 2, 2, 3 };
        int k = 2;
        List<Integer> values = getTopKElements(nums, k);
        System.out.println(values.toString());
    }

    // Time Complexity : 0(n)
    public static List<Integer> getTopKElements(int[] nums, int k) {

        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] list = new ArrayList[nums.length + 1];

        for (Map.Entry<Integer, Integer> map : freqMap.entrySet()) {
            int frequency = map.getValue();
            if (list[frequency] == null) {
                list[frequency] = new ArrayList<>();
            }
            list[frequency].add(map.getKey());
        }

        List<Integer> res = new ArrayList<>();
        // reading length in reverse order act as sorted data
        for (int i = list.length - 1; i >= 0; i--) {
            if (list[i] == null) {
                continue;
            }
            for (int j = 0; j < list[i].size() && res.size() < k; j++) {
                res.add(list[i].get(j));
            }
        }
        return res;
    }
}
