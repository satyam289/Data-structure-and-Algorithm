package Array;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://www.interviewbit.com/problems/compare-version-numbers/
/*
If version1 > version2 return 1,
If version1 < version2 return -1,
otherwise return 0.
*/
public class CompareVersion {

    public int compareVersion(String v1, String v2) {
        ArrayList<String> a = new ArrayList<>(Arrays.asList(v1.split("[.]")));
        ArrayList<String> b = new ArrayList<>(Arrays.asList(v2.split("[.]")));

        while (a.size() > b.size())
            b.add("0");
        while (a.size() < b.size())
            a.add("0");

        // int i=0,j=0;

        for (int i = 0, j = 0; i < a.size() && j < b.size(); i++, j++) {
            double d1 = Double.parseDouble(a.get(i));
            double d2 = Double.parseDouble(b.get(j));

            if (d1 > d2)
                return 1;
            else if (d1 < d2)
                return -1;
        }

        return 0;
    }

    public int compareVersion2(String S1, String S2) {
        StringTokenizer v1St = new StringTokenizer(S1, ".");
        StringTokenizer v2St = new StringTokenizer(S2, ".");
        int v1Count = v1St.countTokens();
        int v2Count = v2St.countTokens();
        if (v1Count < v2Count) {
            for (int i = 0; i < v1Count; i++) {
                BigInteger v1 = new BigInteger(v1St.nextToken());
                BigInteger v2 = new BigInteger(v2St.nextToken());
                if (!v1.equals(v2)) {
                    return v1.compareTo(v2);
                }
            }
            while (v2St.hasMoreTokens()) {
                if (!new BigInteger(v2St.nextToken()).equals(BigInteger.ZERO))
                    return -1;
            }
        } else if (v2Count < v1Count) {
            for (int i = 0; i < v2Count; i++) {
                BigInteger v1 = new BigInteger(v1St.nextToken());
                BigInteger v2 = new BigInteger(v2St.nextToken());
                if (!v1.equals(v2)) {
                    return v1.compareTo(v2);
                }
            }
            while (v1St.hasMoreTokens()) {
                if (!new BigInteger(v1St.nextToken()).equals(BigInteger.ZERO))
                    return 1;
            }
        } else {
            for (int i = 0; i < v1Count; i++) {
                BigInteger v1 = new BigInteger(v1St.nextToken());
                BigInteger v2 = new BigInteger(v2St.nextToken());
                if (!v1.equals(v2))
                    return v1.compareTo(v2);
            }
        }
        return 0;
    }

    public int compareVersion3(String A, String B) {
        String[] s1 = A.split("\\.");
        String[] s2 = B.split("\\.");
        int i = 0;
        while (i < s1.length || i < s2.length) {
            BigInteger version1 = i < s1.length ? new BigInteger(s1[i]) : BigInteger.ZERO;
            BigInteger version2 = i < s2.length ? new BigInteger(s2[i]) : BigInteger.ZERO;
            int comparison = version1.compareTo(version2);
            if (comparison == 0) {
                i++;
                continue;
            }
            return comparison;
        }
        return 0;
    }
}
