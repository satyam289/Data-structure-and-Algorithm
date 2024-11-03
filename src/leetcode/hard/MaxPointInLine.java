package leetcode.hard;

import java.util.*;

public class MaxPointInLine {

    class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            this.x = a;
            this.y = b;
        }
    }

    public int maxPoints(Point[] points) {
        if (points.length < 3) {
            // for 0, 1, 2 points case
            return points.length;
        }
        int res = 1; // at least 1 point
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            int max = 0;
            int countSame = 0; // same points
            for (int j = i + 1; j < points.length; j++) {

                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    countSame++; // same point
                } else {
                    String key = normalized(points[i], points[j]);
                    if (map.containsKey(key)) { // on the existing line
                        int count = map.get(key) + 1;
                        if (count > max) { // update
                            max = count;
                        }
                    } else { // not in any line
                        map.put(key, 1);
                        if (max == 0) {
                            max++;
                        }
                    }
                }
            }
            res = Math.max(res, max + countSame + 1); // +1 starting point
            map.clear(); // clear map for next point
        }
        return res;
    }

    /*
     * use ax + by = c represent line
     * normalize a, b, c
     */
    public String normalized(Point p1, Point p2) {
        int a, b;
        float c;
        if (p1.x == p2.x) {
            // vertical
            b = 0;
            a = 1;
            c = p1.x;
        } else if (p1.y == p2.y) {
            // Horizontal
            a = 0;
            b = 1;
            c = p2.y;
        } else {
            int dx = p1.x - p2.x;
            int dy = p1.y - p2.y;
            // simplify
            int gcd = gcd(Math.abs(dx), Math.abs(dy));
            a = dy / gcd;
            b = dx / gcd;
            if (a * b < 0) { // negative slope case
                a = -1 * Math.abs(a);
                b = Math.abs(b);
            } else { // positive slope case
                a = Math.abs(a);
                b = Math.abs(b);
            }
            // c = ax + by
            c = a * p1.x + b * p1.y;
        }
        return a + "|" + b + "|" + c;
    }

    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
