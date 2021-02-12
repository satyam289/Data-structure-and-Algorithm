package Miscellaneous;

import java.util.ArrayList;

/**
 * Given N people on an MxM grid, find the point that requires the least total
 * distance covered by all people to meet at that point. Consider a 5x5 grid
 * with 3 people; one at X(1,2), Y(4,2) and Z(3,3).
 */
public class LeastDistanceMeetPeople {

    //We need to find the meeting point(x,y) for these people where the total distance covered by all three is the minimum.
    // They can travel in all directions: horizontally, vertically, and diagonally. The minimum distance point, in this case, is (3,3).

    // 1) Distance between two points 2) Centroid of a two-dimensional region
    
    private static class point {
        private int x;
        private int y;
    
        point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    
        int getX() {
            return x;
        }
    
        void setX(int x) {
            this.x = x;
        }
    
        int getY() {
            return y;
        }
    
        void setY(int y) {
            this.y = y;
        }
    
        double calculate_distance(point p) {
            double distance;
            distance = Math.sqrt((p.x - this.x) * (p.x - this.x) + (p.y - this.y) * (p.y - this.y));
            return distance;
        }
    
        double calculate_sum_of_distances(
        ArrayList < point > points) {
            double distance_sum;
            distance_sum = 0;
            for (int i = 0; i < points.size(); i++) {
                distance_sum += this.calculate_distance(points.get(i));
            }
            return distance_sum;
        }
    
    };
    
    static class distance {
        public point shortest_distance_travelled_2(int m, ArrayList<point> points) {
            point min_pt = new point(0, 0);
            double x = 0;
            double y = 0;

            // calculate the centroid
            point centroid = new point(0, 0);
            for (int i = 0; i < points.size(); i++) {
                x += points.get(i).getX();
                y += points.get(i).getY();
            }
            centroid.setX((int) Math.round(x / points.size()));
            centroid.setY((int) Math.round(y / points.size()));

            // initialize the min_pt to centroid
            min_pt.setX(centroid.getX());
            min_pt.setY(centroid.getY());

            double min_distance = min_pt.calculate_sum_of_distances(points);

            // checking points surrounding the potential centroid
            for (int i = min_pt.getX() - 1; i < min_pt.getX() + 2; i++) {
                for (int j = min_pt.getY() - 1; j < min_pt.getY() + 2; j++) {
                    if (i < 1 || j > m) {
                        continue;
                    }
                    point pt = new point(i, j);
                    double distance = pt.calculate_sum_of_distances(points);
                    if (distance < min_distance) {
                        min_distance = distance;
                        min_pt.setX(pt.getX());
                        min_pt.setY(pt.getY());
                    }
                }
            }

            return min_pt;
        }

        public static void test_grid1() {
            System.out.println("Testing grid 1...\n");
            int m = 5; // size of the grid
            ArrayList<point> points = new ArrayList<point>();
            points.add(new point(1, 2));
            points.add(new point(3, 3));
            points.add(new point(4, 2));

            System.out.println("Solution 2:");
            distance d = new distance();
            point pt = d.shortest_distance_travelled_2(m, points);
            System.out.println("Shortest Distance Point = p(" + pt.getX() + ", " + pt.getY() + ")");
        }

        public static void test_grid2() {
            System.out.println("Testing grid 2...\n");
            int m = 10; // size of the grid
            ArrayList<point> points = new ArrayList<point>();
            points.add(new point(1, 1));
            points.add(new point(3, 5));
            points.add(new point(6, 2));
            points.add(new point(7, 7));
            points.add(new point(8, 4));

            System.out.println("Solution 2:");
            distance d = new distance();
            point pt = d.shortest_distance_travelled_2(m, points);
            System.out.println("Shortest Distance Point = p(" + pt.getX() + ", " + pt.getY() + ")");
        }

        public static void test_grid3() {
            System.out.println("Testing grid 3...\n");
            int m = 8; // size of the grid
            ArrayList<point> points = new ArrayList<point>();
            points.add(new point(4, 3));
            points.add(new point(5, 5));
            points.add(new point(2, 7));

            System.out.println("Solution 2:");
            distance d = new distance();
            point pt = d.shortest_distance_travelled_2(m, points);
            System.out.println("Shortest Distance Point = p(" + pt.getX() + ", " + pt.getY() + ")");
        }

        public static void main(String[] args) {

            test_grid1();
            test_grid2();
            test_grid3();

            System.out.println("\nCompleted!\n");
        }
    }
}
