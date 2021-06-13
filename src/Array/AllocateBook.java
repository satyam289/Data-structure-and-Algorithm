package Array;

import java.util.ArrayList;

//https://www.interviewbit.com/problems/allocate-books/
//https://www.geeksforgeeks.org/allocate-minimum-number-pages/
/*
You have to allocate books to B number of students 
so that maximum number of pages alloted to a student is minimum.
*/
public class AllocateBook {

    public static int books(int[] books, int noOfStudent) {
        int n = books.length;
        if (noOfStudent > n) {
            return -1;
        }
        int max = 0;
        int sumPages = 0;
        for (int i = 0; i < n; i++) {
            sumPages += books[i];
            max = Math.max(max, books[i]);
        }
        int start = max; // start off with book at maximum page which is minimum
        int end = sumPages;
        int result = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (isPossible(books, noOfStudent, mid)) {
                end = mid - 1;
                result = mid;
            } else {
                start = mid + 1;
            }
        }
        return result;
    }

    // Possible to distribute among maxStudets having less than allowed page
    private static boolean isPossible(int[] book, int maxStudents, int maxAllowPage) {
        int pageSum = 0;
        int numberofStudent = 1;
        for (int i = 0; i < book.length; i++) {
            pageSum += book[i];
            if (pageSum > maxAllowPage) {
                pageSum = book[i]; // reset point to next student
                numberofStudent++;
                if (numberofStudent > maxStudents) {
                    return false;
                }
            }
        }
        return true;
    }

    // https://www.interviewbit.com/problems/painters-partition-problem/
    public int paint(int A, int B, ArrayList<Integer> C) {
        long total = 0, max = Long.MIN_VALUE;
        for (int c : C) {
            total += c;
            max = Math.max(max, c);
        }
        long l = max, h = total;

        while (l < h) {
            long mid = (l + h) / 2;
            if (isRequiredPainters(C, A, mid))
                h = mid;
            else
                l = mid + 1;
        }
        long ans = ((l % 10000003) * (B % 10000003)) % 10000003;
        return (int) ans;
    }

    private boolean isRequiredPainters(ArrayList<Integer> A, int maxPainter, long k) {
        long total = 0, reqPainters = 1;
        for (Integer a : A) {
            total += a;
            if (total > k) {
                total = a;
                reqPainters++;
                if (reqPainters > maxPainter) {
                    return false;
                }
            }
        }
        return true;
    }
}
