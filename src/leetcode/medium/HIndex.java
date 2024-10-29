package leetcode.medium;

import java.util.Arrays;

/*
 * from array of citation of researcher, compute researcher's h index
 * scientist has h index define as if N papers at least h citation each and other (N-h) papers have no more than h citations each
 */
public class HIndex {

    public static void main(String[] args) {
        int[] citation = { 3, 0, 6, 1, 5 };
        System.out.println(hIndex(citation));
        System.out.println(hIndex2(citation));
    }

    public static int hIndex(int[] citations) {
        int n = citations.length;
        int[] papers = new int[n + 1];

        for (int c : citations) {
            papers[Math.min(n, c)]++;
        }
        int k = n;
        for (int s = papers[n]; k > s; s += papers[k])
            k--;
        return k;
    }

    public static int hIndex2(int[] citation) {
        Arrays.sort(citation);
        int i = 0;
        while (i < citation.length && citation[citation.length - 1 - i] > i) {
            i++;
        }
        return i;
    }
}
