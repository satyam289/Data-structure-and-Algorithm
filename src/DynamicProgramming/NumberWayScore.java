package DynamicProgramming;

//https://www.geeksforgeeks.org/count-number-ways-reach-given-score-game/
public class NumberWayScore {

    public int noOfWay(int[] score, int total) {
        int[] T = new int[total + 1];
        T[0] = 1;
        for (int i = 1; i < total; i++) {
            for (int j = 0; j < score.length; j++) {
                if (i >= score[j]) {
                    T[i] += T[i - score[j]];
                }
            }
        }
        return T[total];
    }

    public int noOfWay2(int[] score, int total) {
        int T[] = new int[total + 1];
        T[0] = 1;
        for (int i = 0; i < score.length; i++) {
            for (int j = 1; j < score.length; j++) {
                if (score[i] <= j) {
                    T[j] += T[j - score[i]];
                }
            }
        }
        return T[total];
    }
}
