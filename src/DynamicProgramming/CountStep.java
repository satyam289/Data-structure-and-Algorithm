package DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class CountStep {

    public static void main(String[] args) {

        System.out.println(noofwaysTostepUp(4));
        System.out.println(noofwaysTostepUpDynamic(4));
    }

    public static int noofwaysTostepUp(int distance) {   // maximum 3 step allowed
        if (distance < 0)
            return 0;
        if (distance == 0)
            return 1;
        return noofwaysTostepUp(distance - 1) + noofwaysTostepUp(distance - 2)
                + noofwaysTostepUp(distance - 3);
    }

    public static int noofwaysTostepUpDynamic(int distance) {
        int[] mem = new int[distance+1];
        mem[0] = 1;
        mem[1] = 1;
        mem[2] = 2;
        for (int i = 3; i <= distance; i++) {
            mem[i] = mem[i - 1] + mem[i - 2] + mem[i - 3];
        }
        return mem[distance];
    }

}
