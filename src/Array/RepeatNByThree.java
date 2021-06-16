package Array;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://www.interviewbit.com/problems/n3-repeat-number/
//https://www.geeksforgeeks.org/n3-repeated-number-array-o1-space/
//Moore Voting Algorithm (Similar to Majority Number)
public class RepeatNByThree {

    public int findMajorityNum(final List<Integer> a) {

        int count1 = 0, count2 = 0, num1 = -1, num2 = -1;
        for (int i = 0; i < a.size(); i++) {
            if (num1 == a.get(i)) {
                count1++;
            } else if (num2 == a.get(i)) {
                count2++;
            } else if (count1 == 0) {
                num1 = a.get(i);
                count1++;
            } else if (count2 == 0) {
                num2 = a.get(i);
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) == num1) {
                count1++;
            } else if (a.get(i) == num2) {
                count2++;
            }
        }
        if (count1 > a.size() / 3) {
            return num1;
        }
        if (count2 > a.size() / 3) {
            return num2;
        }
        return -1;
    }

    public int findMajorityNum2(final List<Integer> a) {
        int maxInteger = -1;
        int maxCount = 0;
        if (a.isEmpty()) {
            return -1;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i : a) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
            if (map.get(i) > maxCount) {
                maxCount = map.get(i);
                maxInteger = i;
            }
        }
        return (maxCount > (a.size() / 3)) ? maxInteger : -1;
    }
}
