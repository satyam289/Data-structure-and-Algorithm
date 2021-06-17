package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;

public class TextJustify {

	public static void main(String[] args) {
		String[] s = { "Tushar", "likes", "to", "code" };
		wordjustify(s, 10);
	}

	private static void wordjustify(String[] word, int limit) {

		int n = word.length;
		int[][] table = new int[n][n];
		for (int i = 0; i < n; i++) {
			table[i][i] = limit - word[i].length();
			for (int j = i + 1; j < n; j++) {
				table[i][j] = table[i][j - 1] - word[j].length() - 1; // 1 extra for space
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (table[i][j] < 0) {
					table[i][j] = Integer.MAX_VALUE;
				} else {
					table[i][j] = (int) Math.pow(table[i][j], 2);
				}
			}
		}

		int[] mincost = new int[n];
		int[] result = new int[n];

		for (int i = n - 1; i >= 0; i--) {
			mincost[i] = table[i][n - 1];
			result[i] = n;

			for (int j = n - 1; j > i; j--) {
				if (table[i][j - 1] == Integer.MAX_VALUE) {
					continue;
				}
				if (mincost[j] + table[i][j - 1] < mincost[i]) {
					mincost[i] = mincost[j] + table[i][j - 1];
					result[i] = j;
				}
			}
		}
		System.out.println(Arrays.toString(mincost));
		System.out.println("Min cost is " + mincost[0]);
		StringBuilder sb = new StringBuilder();
		int startRange = 0;
		do {
			int endRange = result[startRange];
			for (int k = startRange; k < endRange; k++) {
				sb.append(word[k] + " ");
			}
			sb.append("\n");
			startRange = endRange;
		} while (startRange < n);
		System.out.println(sb.toString());
	}

	// Different Question (text justify)
	// https://www.interviewbit.com/problems/justified-text/
	public ArrayList<String> fullJustify(ArrayList<String> words, int maxWidth) {
		ArrayList<String> result = new ArrayList<String>();
		if (words == null || words.size() == 0) {
			return result;
		}
		int count = 0;
		int last = 0;
		for (int curr = 0; curr < words.size(); curr++) {
			count = count + words.get(curr).length();
			if (count + curr - last > maxWidth) { // @here (i - last) represent number of space character or no of new

				int wordsLen = count - words.get(curr).length();
				int spaceLen = maxWidth - wordsLen;
				int eachLen = 1;
				int extraLen = 0;
				if (curr - last - 1 > 0) {
					eachLen = spaceLen / (curr - last - 1); // @here -1 means (no of words - 1), no space at end
					extraLen = spaceLen % (curr - last - 1);
				}
				StringBuilder sb = new StringBuilder();
				for (int k = last; k < curr - 1; k++) {
					sb.append(words.get(k));
					int ce = 0;
					while (ce < eachLen) {
						sb.append(" "); // Add equally divided space
						ce++;
					}

					if (extraLen > 0) {
						sb.append(" "); // Add extra space prefer left side
						extraLen--;
					}
				}
				sb.append(words.get(curr - 1)); // Add last words in the line

				while (sb.length() < maxWidth) { // if only one word in this line,fill left with space
					sb.append(" ");
				}
				result.add(sb.toString());
				last = curr;
				count = words.get(curr).length(); // reset count
			}
		}
		// case: last line
		StringBuilder sb = new StringBuilder();
		for (int i = last; i < words.size() - 1; i++) {
			sb.append(words.get(i) + " ");
		}
		sb.append(words.get(words.size() - 1));
		while (sb.length() < maxWidth) {
			sb.append(" ");
		}
		result.add(sb.toString());
		return result;
	}
}
