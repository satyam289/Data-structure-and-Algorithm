package Array;

public class FindCommonThreeSortedArray {

    public static void main(String[] args) {
        int ar1[] = {1, 5, 10, 20, 40, 80};
        int ar2[] = {6, 7, 20, 80, 100};
        int ar3[] = {3, 4, 15, 20, 30, 70, 80, 120};
        findCommon(ar1, ar2, ar3);
    }

   // Approach 1: Read all elements from first array in temp array, then compare with all remaining array , keep common

    // Approach 2: Having each pointer all the array , compare with all, keep the common , increase pointer lesser value
    //0(N1 + N2 + N3)
    public static void findCommon(int ar1[], int ar2[], int ar3[]) {
        int i = 0, j = 0, k = 0;
        while (i < ar1.length && j < ar2.length && k < ar3.length) {

            if (ar1[i] == ar2[j] && ar2[j] == ar3[k]) {
                System.out.print(ar1[i] + " ");
                i++;j++;k++;
            }
            else if (ar1[i] < ar2[j])
                i++;
            else if (ar2[j] < ar3[k])
                j++;
            else                 //smallest
                k++;
        }
    }
}
