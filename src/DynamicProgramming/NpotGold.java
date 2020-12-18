package DynamicProgramming;

import java.util.Arrays;

public class NpotGold {

	private static class Pot {
		int first;
		int second;
		int pick;

		Pot(int first, int second, int pick) {
			this.first = first;
			this.second = second;
			this.pick=pick;
		}
		public String toString() {
			return "("+first+","+second+","+pick+")";
		}
	}

	public static void main(String[] args) {
		int [] goldvaluepot= {3,9,1,2};
		npot(goldvaluepot);
	}

	public static void npot(int[] potvalue) {
		int len = potvalue.length;
		Pot[][] table = new Pot[potvalue.length][potvalue.length];
		for (int i = 0; i < len; i++) {
			table[i][i] = new Pot(potvalue[i], 0,i);
		}
		for (int l = 2; l <= len; l++) {
			for (int i = 0; i < len - l + 1; i++) {
				int j = i + l - 1;
				if (potvalue[j]+table[i][j - 1].second > potvalue[i]+table[i + 1][j].second)
					table[i][j] = new Pot(potvalue[j]+table[i][j - 1].second, table[i][j - 1].first,j);
				else
					table[i][j] = new Pot(potvalue[i]+table[i + 1][j].second, table[i + 1][j].first,i);
			}
		}
		System.out.println(Arrays.toString(table[0]));
		System.out.println(Arrays.toString(table[1]));
		System.out.println(Arrays.toString(table[2]));
		System.out.println(Arrays.toString(table[3]));
		System.out.println(table[0][len - 1].first);
		printSequence(potvalue, table);
	}
	
	public static void printSequence(int[] pots, Pot[][] moves){
        int i = 0;
        int j = pots.length - 1;
        int step;
        System.out.println("Move by two player: ");
        boolean playerno =true;
        for (int k = 0; k < pots.length; k++) {
            step = moves[i][j].pick;
            //this is the value of pick and its index
            String details=playerno?"player 1":"player 2";
            System.out.println(details+" picks gold value: " + pots[step] + " " + " at index: " + step + " ");
            if (step <= i) {
                i = i + 1;
            } else {
                j = j - 1;
            }
            playerno=!playerno;
        }
    }
}