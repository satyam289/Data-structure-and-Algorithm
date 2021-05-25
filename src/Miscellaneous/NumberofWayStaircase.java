package Miscellaneous;

//https://www.geeksforgeeks.org/count-ways-reach-nth-stair/
public class NumberofWayStaircase {

    // Todo Need Optimised
    public static int noOfWaysToReachTop(int steps) {
        if (steps <= 0) {
            return 0;
        }
        if (steps == 1 || steps == 2) {
            return steps;
        }
        return noOfWaysToReachTop(steps - 1) + noOfWaysToReachTop(steps - 2);
    }

    public static void main(String[] args) {
        System.out.println(noOfWaysToReachTop(4));
    }
}
