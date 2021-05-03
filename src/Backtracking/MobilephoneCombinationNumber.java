package Backtracking;

import java.util.ArrayList;
import java.util.HashMap;

// https://www.geeksforgeeks.org/mobile-numeric-keypad-problem/
public class MobilephoneCombinationNumber {

    public static void main(String[] args) {
        System.out.println(no_ways_validNumber(2));
        System.out.println(combinationDp(2));
    }

    public static int no_ways_validNumber(int numberOfTimes) {
        if (numberOfTimes <= 0)
            return 0;
        if (numberOfTimes == 1)
            return 10;

        int[][] keypad = { { '1', '2', '3' }, 
                           { '4', '5', '6' }, 
                           { '7', '8', '9' }, 
                           { '*', '0', '#' } };
        int total = 0;
        for (int i = 0; i < 4; i++) {  // keypad row
            for (int j = 0; j < 3; j++) {  // keypad column
                if (keypad[i][j] != '*' && keypad[i][j] != '#') {  // skipping
                    total += countValidNumberUtil(numberOfTimes, i, j, keypad);
                }
            }
        }
        return total;
    }

    private static int countValidNumberUtil(int numberOfTimes, int i, int j, int[][] keypad) {
        int[] rowkey = { 0, 1, 0, -1, 0 };
        int[] colkey = { 0, 0, 1, 0, -1 };
        int row, col, total = 0;
        if (keypad == null || numberOfTimes <= 0)
            return 0;
        if (numberOfTimes == 1) // base condition @here 1
            return 1;

        for (int k = 0; k < 5; k++) {  // allowed movement : left, right, top bottom
            row = i + rowkey[k];
            col = j + colkey[k];
            if (row >= 0 && row < 4 && col >= 0 && col < 3 && keypad[row][col] != '*' && keypad[row][col] != '#') // checking boundary option
                total += countValidNumberUtil(numberOfTimes - 1, row, col, keypad);
        }
        return total;
    }

    // Dynamic Programming
    public static int combinationDp(int numberOfTimes) {
        char[][] keypad = {{'1', '2', '3'}, 
                           {'4', '5', '6'}, 
                           {'7', '8', '9'}, 
                           {'*', '0', '#'}};
        if (numberOfTimes == 0)
            return 0;
        if (numberOfTimes == 1)
            return 10;

        int[] row = {1, 0, -1, 0, 0};
        int[] col = {0, 1, 0, -1, 0};
        int[][] cost = new int[10][numberOfTimes + 1]; // keypad number + no of times pressing key
        for (int i = 0; i < 10; i++) {
            cost[i][0] = 0; // when no key is pressed
            cost[i][1] = 1; // when one key is pressed
        }

        for (int k = 2; k <= numberOfTimes; k++) {

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {

                    if (keypad[i][j] != '*' && keypad[i][j] != '#') {

                        cost[keypad[i][j] - '0'][k] = 0;
                        for (int move = 0; move < 5; move++) {
                            int new_row = row[move] + i;
                            int new_col = col[move] + j;
                            if (new_row >= 0 && new_row < 4 && new_col >= 0 && new_col < 3 && keypad[new_row][new_col] != '*' && keypad[new_row][new_col] != '#') {
                                cost[keypad[i][j] - '0'][k] += cost[keypad[new_row][new_col] - '0'][k - 1];
                            }
                        }
                    }
                }
            }
        }
        int total = 0;
        for (int i = 0; i < 10; i++) { // column sum
            total += cost[i][numberOfTimes];
        }
        return total;
    }

    /*
     * Given a digit string, return all possible letter combination that the number(keypad)
     *  https://www.interviewbit.com/problems/letter-phone/
     * https://www.geeksforgeeks.org/iterative-letter-combinations-of-a-phone-number/
     */
    String[] KeyPad = new String[] { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public ArrayList<String> letterCombinations1(String input) {
        ArrayList<String> result = new ArrayList<String>();
        addPosssibleCombination(input, result, "", 0);
        return result;
    }

    private void addPosssibleCombination(String input, ArrayList<String> result, String str, int index) {
        if (index >= input.length()) {
            result.add(str);
            return;
        }
        int num = input.charAt(index) - '0';
        String keypadStr = KeyPad[num];

        for (int j = 0; j < keypadStr.length(); j++) {
            String newStr = str + keypadStr.charAt(j);
            addPosssibleCombination(input, result, newStr, index + 1);
        }
    }

    // Similar Apporach using map
    public ArrayList<String> letterCombinations2(String a) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("4", "ghi");
        map.put("5", "jkl");
        map.put("6", "mno");
        map.put("7", "pqrs");
        map.put("8", "tuv");
        map.put("2", "abc");
        map.put("3", "def");
        map.put("9", "wxyz");
        map.put("0", "0");
        map.put("1", "1");
        ArrayList<String> result = new ArrayList<String>();
        if (a == null || a.length() == 0) {
            return result;
        }
        ArrayList<Character> temp = new ArrayList<Character>();
        addpossibleCombination(a, temp, result, map);
        return result;
    }

    private void addpossibleCombination(String a, ArrayList<Character> temp, ArrayList<String> res, HashMap<String, String> map) {
        if (a.length() == 0) {
            char[] arr = new char[temp.size()];
            for (int i = 0; i < temp.size(); i++) {
                arr[i] = temp.get(i);
            }
            res.add(String.valueOf(arr));
            return;
        }
        String num = a.substring(0, 1);
        String let = map.get(num);
        for (int i = 0; i < let.length(); i++) {
            temp.add(let.charAt(i));
            addpossibleCombination(a.substring(1), temp, res, map);
            temp.remove(temp.size() - 1); //back tracking
        }
    }
}
