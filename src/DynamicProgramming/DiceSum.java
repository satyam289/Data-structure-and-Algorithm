package DynamicProgramming;

//https://www.geeksforgeeks.org/dice-throw-dp-30/
public class DiceSum {

	public static void main(String[] args) {
		System.out.println(noOfWays(2, 6, 9));
	}

	public static int noOfWays(int noOfDice, int face, int sum) {
		// Skip : sum is too low or too high
		if (sum < noOfDice || sum > noOfDice * face)
			return -1;

		int[][] table = new int[noOfDice + 1][sum + 1];
		// for only one dices
		for (int i = 1; i <= face && i <= sum; i++)
			table[1][i] = 1;

		for (int i = 2; i <= noOfDice; i++) {
			for (int j = 1; j <= sum; j++) {
				if (i > j || j > face * i) {
					table[i][j] = 0;
				} else {
					for (int f = 1; f <= face && f < j; f++) {
						table[i][j] += table[i - 1][j - f];
					}
				}
			}
		}
		return table[noOfDice][sum];
	}
}
