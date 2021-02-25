package DynamicProgramming;

import java.util.Arrays;

public class MinimumCostTrainTicket {

	public static void main(String[] args) {
		int input[][] = { { 0, 15, 80, 90 }, 
				          { -1, 0, 40, 50 }, 
				          { -1, -1, 0, 70 }, 
				          { -1, -1, -1, 0 } };
		minCost(input);
	}

	public static void minCost(int ticket[][]) {

		assert ticket != null && ticket.length > 0 && ticket.length == ticket[0].length;
		int[] t = new int[ticket.length];
		int[] path = new int[ticket.length];

		for (int j = 0; j < ticket[0].length; j++) {
			t[j] = ticket[0][j];
			path[j] = j - 1;
		}

		for (int i = 1; i < ticket.length; i++) {
			for (int j = i + 1; j < ticket[0].length; j++) {
				if (t[j] > ticket[i][j] + t[i]) {
					t[j] = ticket[i][j] + t[i];
					path[j] = i;
				}
			}
		}
		System.out.println(Arrays.toString(t));
		System.out.println(Arrays.toString(path));
        int journey=ticket.length-1;
        while(journey>0) {
        	System.out.println("going from "+path[journey] +" station to "+journey+" station");
        	journey=path[journey];
        }
	}

}
