package Miscellaneous;

/*
https://www.interviewbit.com/problems/gas-station/
https://www.geeksforgeeks.org/find-a-tour-that-visits-all-stations/
Suppose there is a circle. There are n petrol pumps on that circle. You are given two sets of data.
 
The amount of petrol that every petrol pump has.
Distance from that petrol pump to the next petrol pump.
Calculate the first point from where a truck will be able to complete the circle (The truck will stop at each petrol pump and it has infinite capacity).
*/
public class PetrolPump {

    private static class PetrolCost {
        int amountPetrol;
        int distance;

        PetrolCost(int amountPetrol, int distance) {
            this.amountPetrol = amountPetrol;
            this.distance = distance;
        }
    }

    // Brute Force : 0(n^2)
    public static int isSuccessful(PetrolCost[] petrolInfos) {
        int start = 0;
        int end = 1;
        int n = petrolInfos.length;
        int diff = petrolInfos[start].amountPetrol - petrolInfos[end].distance;
        // If current amount of petrol in truck becomes less than 0, then
        // remove the starting petrol pump from tour
        while (start != end || diff < 0) {
            // If current amount of petrol in truck becomes less than 0, then
            // remove the starting petrol pump from tour
            while (diff < 0 && start != end) {
                // Remove starting petrol pump. Change start
                diff -= petrolInfos[start].amountPetrol - petrolInfos[start].distance;
                start = (start + 1) % n;
                // If 0 is being considered as start again, then there is no solution possible
                if (start == 0) {
                    return -1;
                }
            }
            // Add a petrol pump to current tour
            diff += petrolInfos[end].amountPetrol - petrolInfos[end].distance;
            end = (end + 1) % n;
        }
        return start;
    }

    // Time Complexity : 0(n)
    public static int isSuccessful2(PetrolCost[] petrolInfos) {
        int diffFuel = 0;
        int total = 0;
        int currDiff = 0;
        int start = 0;
        for (int i = 0; i < petrolInfos.length; i++) {
            diffFuel = petrolInfos[i].amountPetrol - petrolInfos[i].distance;
            if (currDiff >= 0) {
                currDiff += diffFuel;
            } else {
                currDiff = diffFuel;
                start = i;
            }
            total += diffFuel;
        }
        return total >= 0 ? start : -1;
    }

    public static void main(String[] args) {
        PetrolCost[] petrolInfos = new PetrolCost[4];
        petrolInfos[0] = new PetrolCost(4, 6);
        petrolInfos[1] = new PetrolCost(6, 5);
        petrolInfos[2] = new PetrolCost(7, 3);
        petrolInfos[3] = new PetrolCost(4, 5);
        System.out.println(isSuccessful(petrolInfos));
        System.out.println(isSuccessful2(petrolInfos));
    }
}
