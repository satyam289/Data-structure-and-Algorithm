package Array;

public class CountPositiveNumberMatrix {

	public static void main(String[] args) {
		int[][] m = {{-3, -2, 1}, {-2, 1, 2}, {2, 5, 6}, {3, 7, 9}};    // matrix is sorted both column wise and row wise
		countPositiveNumberBrute(m);
		countPositiveOptimized(m);
	}

	// 0(n2)
	private static void countPositiveNumberBrute(int[][] m) {
		int count = 0;
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length;) {
				if (m[i][j] <= 0) {
					j++;
				}else{
					count += (m[0].length - j);
					break;
				}
			}
		}
		System.out.println("No of Non negative Number in matrix : " + count);
	}

	//0(n)
	public static void countPositiveOptimized(int [][] arr){
	    int i = 0;
	    int j = arr[0].length-1;
	    int NegativeCount = 0;
		while (i < arr.length && j>=0 ) {
			if (arr[i][j] <= 0) {
				NegativeCount += (arr.length - j - 2);
				i++;
			}else{
	    		j--;
			}
	    }
	    int positiveCount = ((arr.length) * (arr[0].length)) - NegativeCount;
		System.out.println("No of Non negative Number in matrix : " + positiveCount);
	}
}

