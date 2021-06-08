package DynamicProgramming;

//https://www.interviewbit.com/problems/shortest-common-superstring/
//https://www.geeksforgeeks.org/shortest-superstring-problem/
//:Todo Travelling Salesman Problem (NP hard) Dp apporach
public class ShortestSuperString {

    static String str = "";

    // Brute Force Apporach
    public static String findShortestSuperstring(String[] A) {
        int len = A.length;

        while (len != 1) {
            int start = 0, end = 0;
            int max = Integer.MIN_VALUE;
            String resStr = "";
            for (int i = 0; i < len; i++) {
                for (int j = i + 1; j < len; j++) {
                    int count = findCommon(A[i], A[j]);
                    if (count > max) {
                        max = count;
                        start = i;
                        end = j;
                        resStr = str;
                    }
                }
            }
            len--;
            if (max == Integer.MIN_VALUE) {
                A[0] += A[len];
            } else {
                A[start] = resStr;
                A[end] = A[len];
            }
        }
        return A[0];
    }

    public static int findCommon(String s1, String s2) {

        int len1 = s1.length();
        int len2 = s2.length();
        int max = Integer.MIN_VALUE;

        for (int i = 1; i <= Math.min(len1, len2); i++) {
            // suffix of s1 to prefix of s2
            if (s1.substring(len1 - i).equals(s2.substring(0, i))) {
                if (i > max) {
                    max = i;
                    str = s1 + s2.substring(i);
                }
            }

            // prefix of s2 to suffix of s1
            if (s1.substring(0, i).equals(s2.substring(len2 - i))) {
                if (i > max) {
                    max = i;
                    str = s2 + s1.substring(i);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String[] arr = { "catgc", "ctaagt", "gcta", "ttca", "atgcatc" };
        System.out.println("The Shortest Superstring is " + findShortestSuperstring(arr));
    }
}
