package DynamicProgramming;

//game b/w two player such they can pick the element of array from their end such that their value(sum of picked elements) is maximum
public class GamePickFromEnd {

    static class Game {
        int first;    // pick left end
        int second;   // pick right end

        Game(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) {
        int[] arr = {8, 15, 3, 7};
        pickupOptimal(arr);
    }

    public static void pickupOptimal(int[] arr) {

        int length = arr.length;
        Game[][] mem = new Game[length][length];
        for (int len = 1; len <= length; len++) {
            for (int i = 0; i < length - len + 1; i++) {
                int j = i + len - 1;
                if (i == j) {
                    mem[i][j] = new Game(arr[i], 0);
                } else {
                    int leftPick = arr[i] + mem[i + 1][j].first;
                    int rightPick = arr[j] + mem[i][j - 1].first;

                    if (leftPick > rightPick) {
                        mem[i][j] = new Game(leftPick, mem[i + 1][j].second);
                    } else {
                        mem[i][j] = new Game(rightPick, mem[i][j - 1].second);
                    }
                }
            }
        }
        System.out.println(mem[0][length - 1].first); //best value
    }
}
