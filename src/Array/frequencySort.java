package Array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

public class frequencySort {

	private static class frequencyComparator implements Comparator<Integer> {
		HashMap<Integer, Integer> base;
		frequencyComparator(HashMap<Integer, Integer> m) {
			this.base = m;
		}

		@Override
		public int compare(Integer i1, Integer i2) {
			Integer a1 = base.get(i1);
			Integer a2 = base.get(i2);
			return a1.compareTo(a2) == 0 ? i1.compareTo(i2) : -(a1.compareTo(a2)); // if same frequency of value of map,
			// return least value key
		}
	}

	public static void main(String[] args) {

		int[] ar = new int[] { 5, 2, 8, 8, 5, 5, 8, 1, 9, 0, 1, 1, 0, 1 };
		HashMap<Integer, Integer> m1 = new HashMap<>();
		for (int i = 0; i < ar.length; i++) {
			if (m1.containsKey(ar[i])) {
				int count = m1.get(ar[i]);
				m1.put(ar[i], count + 1);
			} else
				m1.put(ar[i], 1);
		}
		frequencyComparator f = new frequencyComparator(m1);
		TreeMap<Integer, Integer> t = new TreeMap<>(f);
		t.putAll(m1);
		System.out.println(t);
		int k = 0;
		for (Entry<Integer, Integer> entry : t.entrySet()) {
			for (int j = 0; j < entry.getValue(); j++) {
				ar[k++] = entry.getKey();
			}
		}
		System.out.println(Arrays.toString(ar));
	}
}

