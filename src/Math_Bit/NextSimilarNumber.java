package Math_Bit;

import java.util.Arrays;

//https://www.interviewbit.com/problems/next-similar-number/
public class NextSimilarNumber {

    public String solve(String s) {
        char[] a = s.toCharArray();
        int n = a.length;
        int in = findIndex(a, n);
        if (in == -1)
            return new String("-1");
        int i = n - 1;
        while (a[i] <= a[in]) {
            i--;
        }
        char t = a[in];
        a[in] = a[i];
        a[i] = t;
        reverse(a, in + 1, n);
        return new String(a);
    }

    private int findIndex(char[] a, int n) {
        for (int i = n - 2; i >= 0; i--) {
            if (a[i] < a[i + 1])
                return i;
        }
        return -1;
    }

    private void reverse(char[] a, int start, int n) {
        for (int i = start; i <= (start + n - 1) / 2; i++) {
            char t = a[i];
            a[i] = a[n - 1 + start - i];
            a[n - 1 + start - i] = t;
        }
    }

    public String solve2(String A) {
        int n = A.length();
        char arr[] = A.toCharArray();
        int i = n - 1;
        for (i = n - 1; i > 0; i--) {
            if (arr[i] > arr[i - 1]) {
                break;
            }
        }
        if (i == 0) {
            return "-1";
        }
        int ch = arr[i - 1];
        int min = i;
        for (int j = i + 1; j < n; j++) {
            if (arr[j] > ch && arr[j] < arr[min]) {
                min = j;
            }
        }
        char tmp = arr[i - 1];
        arr[i - 1] = arr[min];
        arr[min] = tmp;
        Arrays.sort(arr, i, n);
        return new String(arr);
    }

    public String solve3(String A) {
        int n = A.length();
        char[] ar = A.toCharArray();
        int[] temp = new int[10];

        for (int i = n - 1; i >= 0; i--) {
            int num = A.charAt(i) - '0';
            temp[num]++;
            int get = find(num, temp);
            if (get != -1) {
                ar[i] = (char) (get + '0');
                for (int j = 0, k = i + 1; j < 10; j++)
                    while (temp[j]-- > 0)
                        ar[k++] = (char) (j + '0');

                return new String(ar);
            }
        }
        return "-1";
    }

    private int find(int num, int[] ar) {
        for (int i = num + 1; i < 10; i++) {
            if (ar[i] > 0) {
                ar[i]--;
                return i;
            }
        }
        return -1;
    }
}
