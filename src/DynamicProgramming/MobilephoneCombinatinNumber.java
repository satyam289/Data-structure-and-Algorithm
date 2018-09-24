package DynamicProgramming;

import java.util.Arrays;
import java.util.Map;

public class MobilephoneCombinatinNumber {

	public static void main(String[] args) {
		System.out.println(no_ways_validNumber(2));
	}

	public static int no_ways_validNumber(int totalnumber, int i, int j, int[][] keypad) {
		int[] rowkey = { 0, 1, 0, -1, 0 };
		int[] colkey = { 0, 0, 1, 0, -1 };
		int row = 0, col = 0, total = 0;
		if (keypad == null || totalnumber <= 0)
			return 0;
		if (totalnumber == 1)
			return 1;

		for (int k = 0; k < 5; k++) {
			row = i + rowkey[k];
			col = j + colkey[k];
			if (row >= 0 && row < 4 && col >= 0 && col < 3 && keypad[row][col] != '*' && keypad[row][col] != '#')
				total += no_ways_validNumber(totalnumber - 1, row, col, keypad);
		}
		return total;
	}

	public static int no_ways_validNumber(int totalnumber) {
		if (totalnumber <= 0)
			return 0;
		if (totalnumber == 1)
			return 10;

		int[][] keypad = { { '1', '2', '3' }, { '4', '5', '6' }, { '7', '8', '9' }, { '*', '0', '#' } };
		int total = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				if (keypad[i][j] != '*' && keypad[i][j] != '#') {
					total += no_ways_validNumber(totalnumber, i, j, keypad);
				}
			}
		}
		return total;
	}

	// ---------------------------------dp Apporach-----------------------------------------------
	
	static int combination(int number) {
		char[][] keypad = { { '1', '2', '3' }, { '4', '5', '6' }, { '7', '8', '9' }, { '*', '0', '#' } };
		if (number == 0)
			return 0;
		if (number == 1)
			return 10;
		int[] row = { 1, 0, -1, 0, 0 };
		int[] col = { 0, 1, 0, -1, 0 };
		int[][] cost = new int[10][number + 1];
		for (int i = 0; i < 10; i++) {
			cost[i][0] = 0;
			cost[i][1] = 1;
		}
		for (int k = 2; k <= number; k++) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 3; j++) {
					if (keypad[i][j] != '*' && keypad[i][j] != '#') {
						int rows = 0, cols = 0;
						cost[keypad[i][j] - '0'][k] = 0;
						for (int move = 0; move < 5; move++) {
							rows = row[move] + i;
							cols = col[move] + j;
							if (rows >= 0 && rows < 4 && cols >= 0 && cols < 3 && keypad[rows][cols] != '*'
									&& keypad[rows][cols] != '#') {
								cost[keypad[i][j] - '0'][k] += cost[keypad[rows][cols] - '0'][k - 1];
							}
						}
					}
				}
			}
		}
		int total = 0;
		for (int i = 0; i < 10; i++) {
			total += cost[i][number];
		}
		return total;
	}

	// ------------------------------------------- possible way of alhabaet word (keypad) from mobile numberpad---------

	/*
	 * HashMap<Integer,String> hm=new HashMap(); int count = 0;
	 * hm.put(0,"");hm.put(1,"");hm.put(2,"abc");hm.put(3,"def");hm.put(4,"ghi");
	 * hm.put(5,"jkl");hm.put(6,"mno");hm.put(7,"pqrs");hm.put(8,"tuv");hm.put(9,
	 * "wxyz"); noofcombine(new int[]{2,0,9},0, new char[3],3,hm, count);
	 * System.out.println(count);
	 * 
	 */

	static void noofcombine(int[] number, int currentdig, char[] output, int n, Map<Integer, String> hm, int count) {

		if (currentdig == n) {
			count++;
			System.out.println(Arrays.toString(output));
		} else {
			for (int i = 0; i < hm.get(number[currentdig]).length(); i++) {
				// System .out.println(Arrays.toString(output));
				output[currentdig] = hm.get(number[currentdig]).toCharArray()[i];
				noofcombine(number, currentdig + 1, output, n, hm, count);
				if (number[currentdig] == '0' || number[currentdig] == '1') {
					return;
				}

			}
		}

	}

}
