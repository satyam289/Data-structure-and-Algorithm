package Math_Bit;

/*
https://www.geeksforgeeks.org/find-the-missing-number/
  1 2 3 _ 5  
  1 5 2 _ 3
  @here missing number is 4
*/
public class MissingElement {

    public int findMissing(int[] arr, int n) {
        int total = (n + 1) * (n + 2) / 2;

        for (int i = 0; i < arr.length; i++) {
            total -= arr[i];
        }
        return total;
    }

    public int findMissing2(int[] arr, int n) {
        int x1 = arr[0];
        int x2 = 1;
        for (int i = 1; i < n; i++) {
            x1 = x1 ^ arr[i];
        }
        for (int i = 2; i < n + 1; i++) {
            x2 = x2 ^ i;
        }
        return x1 ^ x2;
    }
}
