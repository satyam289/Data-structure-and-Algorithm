package DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class ScrambledString {

	public static void main(String[] args) {

		//System.out.println(isScrambleString("great", "gtear"));
		//System.out.println(isScrambleString("great" ,"reatg"));
		System.out.println(isScrambleString("ty" ,"yt"));
	}

	public static boolean isScrambleString(String input1, String input2) {
		Map<Index, Boolean> hm = new HashMap<>();
		return isScrambleString(input1.toCharArray(), input2.toCharArray(), 0, input1.length() - 1, 0,
				input2.length() - 1, hm);
	}

	private static boolean isScrambleString(char[] input1, char[] input2, int start1, int end1, int start2, int end2,
			Map<Index, Boolean> hm) {
		if (start1 > end1 || start2 > end2) {
			return false;
		}
		if ((end1 - start1) != (end2 - start2)) {
			return false;
		}
        Index index=new Index(start1, start2, end1, end2);
		if (hm.containsKey(index)) {
			return hm.get(index);
		}
		boolean flag = true;
		for (int i = start1, j = start2; i <= end1 && j <= end2; i++, j++) {
			if (input1[i] != input2[j]) {
				flag = false;
				break;
			}
		}
		if (flag) {
			hm.put(new Index(start1, start2, end1, end2), true);
			return true;
		}
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (Character ch : input1) {
			map.compute(ch, (ch1, val) -> {
				if (val == null)
					return 1;
				else
					return val + 1;
			});
		}
		for (Character ch : input2) {
			map.compute(ch, (ch1, val) -> {
				if (val == null)
					return val = -1;
				else
					return val - 1;
			});
		}
		long size = map.values().stream().filter(val -> val != 0).count();
		if (size > 0) {
			System.out.println("Noo!!" + size);
			hm.put(new Index(start1, start2, end1, end2), false);
			return false;
		}
		for (int len = 0; len < end1 - start1; len++) {
			System.out.println("heyyy!!");
			if (isScrambleString(input1, input2, start1, start1 + len, start2, start2 + len, hm)
					&& isScrambleString(input1, input2, start1 + len + 1, end1, start2 + len + 1, end2, hm)) {
				hm.put(index, true);
				return true;
			}
			System.out.println("yepp!!");
			if (isScrambleString(input1, input2, start1, start1 + len, end2 - len, end2, hm)
					&& isScrambleString(input1, input2, start1 + len + 1, end1, start2, end2 - len - 1, hm)) {
				hm.put(index, true);
				return true;
			}
		}
		hm.put(index, false);
		return false;
	}

	public static class Index {
		int start1;
		int start2;
		int end1;
		int end2;

		public Index(int start1, int start2, int end1, int end2) {
			this.start1 = start1;
			this.start2 = start2;
			this.end1 = end1;
			this.end2 = end2;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + end1;
			result = prime * result + end2;
			result = prime * result + start1;
			result = prime * result + start2;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Index other = (Index) obj;
			if (end1 != other.end1)
				return false;
			if (end2 != other.end2)
				return false;
			if (start1 != other.start1)
				return false;
			if (start2 != other.start2)
				return false;
			return true;
		}

	}

}
