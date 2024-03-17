package leetcode.hard;

public class MedianOfTwoSortedArray {

    public static void main(String[] args) {
        int [] A = {1, 2};
        int [] B = {3, 4};
        double mediaValue = findMedianSorted(A, B);
        System.out.println(mediaValue);
    }

    public static double findMedianSorted(int[] A, int [] B) {
        int m = A.length;
        int n = B.length;
        if(m > n){
            return findMedianSorted(B, A);
        }
        int iMin = 0;
        int iMax = m;
        int halflen = (m+n+1)/2;
        while(iMin < iMax){
            int i = (iMin+ iMax)/2;
            int j = halflen - i;
            // Too far 
            if(iMin >= 0 && A[i-1] > B[j]){
                i=i-1;
            } else if (iMax < m &&  A[i] < B[j-1]){
                i=i+1;
            } else {
                // found partition
                int maxleft = 0;
                if(i==0)
                    maxleft = B[j-1];
                else if(j==0)
                    maxleft = A[i-1];
                else{
                    maxleft = Math.max(A[i-1], B[j-1]);
                } 
                // even length
                if((m+n) % 2 == 0){
                    return maxleft;
                }
                int maxright = 0;
                if(i==m)
                    maxright = B[j];
                else if(j==n)
                    maxright = A[i];
                else{
                    maxright = Math.min(A[i], B[j]);
                }
                // odd length
                return (maxleft + maxright)/2;          
            }
        }
        return 0.0;
    }
}
