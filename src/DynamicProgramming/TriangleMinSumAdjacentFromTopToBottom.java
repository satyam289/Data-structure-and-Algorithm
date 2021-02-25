package DynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class TriangleMinSumAdjacentFromTopToBottom {

	public static void main(String[] args) {
		List<Integer> list1=new ArrayList<>();
		list1.add(3);
		list1.add(4);
		List<Integer> list2=new ArrayList<>();
		list2.add(1);
		List<List<Integer>> list=new ArrayList<List<Integer>>();
		list.add(list1);
		list.add(list2);
		getMinSumTriangle(list);
	}
	
	//bottom up apporach
	public static int getMinSumTriangle(List<List<Integer>> traingle) {

		int N = traingle.size();
		int[] table = new int[N];

		for (int j = 0; j < traingle.get(N - 1).size(); j++) {
			table[j] = traingle.get(N - 1).get(j);
		}

		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j < traingle.get(i).size(); j++) {
				table[j] = traingle.get(i).get(j) + Math.min(table[j], table[j + 1]);

			}
		}
		return table[0];
	}

}
