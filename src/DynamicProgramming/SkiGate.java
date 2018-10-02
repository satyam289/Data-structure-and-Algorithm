package DynamicProgramming;

import java.util.HashMap;


public class SkiGate {

	public static void main(String[] args) {
		int [] gates= {15, 13, 5, 7, 4, 10, 12, 8, 2, 11, 6, 9 , 3};
       System.out.println(maxnumberofgate(gates,2));
	}

	public static int maxnumberofgate(int[] gates, int noofdirection) {
		HashMap<Index, Integer> hm = new HashMap<Index, Integer>();
		return maxnumberofgate(gates, noofdirection, 0, -1, false, hm);
	}

	private static int maxnumberofgate(int[] gates, int noofdirection, int current, int previous, boolean isRight,HashMap<Index, Integer> hm) {
		if (current >= gates.length)
			return 0;
		 Index index = new Index(noofdirection, current, isRight, previous);
		if (hm.containsKey(index)) {
			return hm.get(index);
		}
		int a = 0, b = 0;
		if ((isRight && gates[current] < previous) || (!isRight && gates[current] > previous)) {
			a = 1+maxnumberofgate(gates, noofdirection, current + 1, gates[current], isRight, hm);
			if (noofdirection > 0) {
				b = 1+maxnumberofgate(gates, noofdirection - 1, current + 1, gates[current], !isRight, hm);
			}
		}
		int c = maxnumberofgate(gates, noofdirection, current + 1, previous, isRight, hm);
		int max = Math.max(a, Math.max(b, c));
		hm.put(index, max);
		return max;
	}

}
class Index{
	int remainingDirectionChanges;
	int current;
	boolean isRight;
	int preItem;
	Index(int remainingDirectionChanges, int current, boolean isRight,int  preItem){
		this.remainingDirectionChanges=remainingDirectionChanges;
		this.current=current;
		this.isRight=isRight;
		this.preItem=preItem;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + current;
		result = prime * result + (isRight ? 1231 : 1237);
		result = prime * result + preItem;
		result = prime * result + remainingDirectionChanges;
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
		if (current != other.current)
			return false;
		if (isRight != other.isRight)
			return false;
		if (preItem != other.preItem)
			return false;
		if (remainingDirectionChanges != other.remainingDirectionChanges)
			return false;
		return true;
	}
	
}
