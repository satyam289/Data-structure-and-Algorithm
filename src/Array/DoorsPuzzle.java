package Array;

import java.util.Arrays;

class DoorsPuzzle {

    public static void getDoorCount(int doors) {
        boolean[] doorState = new boolean[doors+1];
        Arrays.fill(doorState, true); // first step, multiple of 1 that is every door is open 

        int count;
        for (int step = 2; step <= doors; step++) {
            int j = step;
            count = 1;
            while (j <= doors) {    // till last door
                doorState[j] = !doorState[j]; // flip the door
                j = step * (++count);  // mutiple of each step
            }
        }
        count = 0;
        for (int i = 1; i <= doors; i++) {
            if (doorState[i]) {
                count++;
            }
        }
        System.out.println(" Door[" + count + "] : Open");
    }

/**
 * You can figure out that for any given door, say door #38, you will visit it for every divisor it has. so  has 1 & 38, 
 * 2 & 19. so on pass 1 i will open the door, pass 2 i will close it,  pass 19 open, pass 38 close. For every pair of divisors 
 * the door will just end up back in its initial state. so you might think that every door will end up closed? 
 * well what about door #9. 9 has the divisors 1 & 9, 3 & 3. but 3 is repeated because 9 is a perfect square, 
 * so you will only visit door #9, on pass 1, 3, and 9 leaving it open at the end. only perfect square doors will be open at the end 
 */
    public static void secondApproach(int doors) {
        int[] doorState = new int[doors+1];
        for (int i = 1; i <= doors; i++) {
            doorState[i] = checkEvenOdd(i);
        }
        int count = 1;
        for (int i = 1; i <= doors; i++) {
            if (doorState[i] == 1){ // odd pair has open state
                count++;
            }
        }
       System.out.println("Door[" + count + "] : Open");
    }

    private static int checkEvenOdd(int num) {
        int count = 0, k;
        for (k = 2; k < num; k++) { // Ignoring 1 and number itself, it is true for every number
            if (num % k == 0)
                count++;  // counting the factors
        }
        if (count % 2 == 0) // even pair
            return 0;
        return 1; // odd pair
    }

    public static void main(String[] args){
        getDoorCount(100);
        secondApproach(100);
    }
}
