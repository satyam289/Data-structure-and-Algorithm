package DynamicProgramming;

import java.util.Arrays;

public class TextJustify {

	public static void main(String[] args) {
		String[] s = { "Tushar", "likes", "to", "code" };
		wordjustify(s, 10);
	}

	private static void wordjustify(String[] word, int limit) {

		int no = word.length;
		int[][] table = new int[no][no];
		for (int i = 0; i < no; i++) {
			table[i][i] = limit - word[i].length();
			for (int j = i+1; j < no; j++) {
				table[i][j] = table[i][j-1] - word[j].length()-1;
			}
		}
		
		for (int i = 0; i < no; i++) {
			for (int j = i; j < no; j++) {
				if (table[i][j] < 0) {
					table[i][j] = Integer.MAX_VALUE;
				} else {
					table[i][j] = (int) Math.pow(table[i][j], 2);
				}
			}
		}
		
		int[] mincost = new int[no];
		int[] result = new int[no];

		for (int i = no - 1; i >= 0; i--) {
			mincost[i] = table[i][no - 1];
			result[i] = no;
			for (int j = no - 1; j >= i; j--) {
				if (table[i][j-1] == Integer.MAX_VALUE) {
					continue;
				} else {
					if (mincost[j-1] + table[i][j] < mincost[i]) {
						mincost[i] = mincost[j-1] + table[i][j];
						result[i] = j;
					}
				}
			}
		}
		//System.out.println(Arrays.toString(mincost));
		System.out.println("Min cost is " + mincost[0]);
		StringBuilder sb = new StringBuilder();
		int i = 0;
		int j = no;
		do {
			int temp = result[i];
			for (int k = i; k < temp; k++) {
				sb.append(word[k] + " ");
			}
			sb.append("\n");
			i = temp;
		} while (i < no);
		System.out.println(sb.toString());

	}

}
