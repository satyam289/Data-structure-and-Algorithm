package Array;

public class CountPositiveNumberMatrix {


	public static void main(String[] args) {

		int[][] m = {{-3, -2, 1}, {-2, 1, 2}, {2, 5, 6}, {3, 7, 9}};    // matrix is sorted both column wise and row wise
		int count = 0, row = m.length, col = m[0].length;   //assuming all column is same
		int size = row;
		int colSize = col;
		for (int i = 0; i < row; i++) {

			if (m[0][i] > 0) {
				count += size * (colSize - i);          //when first element in row is  positive , stop and count the remaining item
				break;
			}
			for (int j = 0; j < col; j++) {
				//System.out.print(m[i][j]+" ");
				if (m[j][i] <= 0) {                           //skip , when value is less
					continue;
				}
				// System.out.println(m[j][i]);

				if (m[j][i] > 0) {
					count++;
					col = j;                               //no need of next iteration
				}
				count += ((size - 1) - (col));                    //add the remaining item of that row
			}
			// System.out.println();
		}
		System.out.println("No of Non negative Number in maxtrix : " + count);
	}

}

