package Backtracking;

public class TugofWar {

    public static int min_diff;

    public static void main(String[] args) {

        int arr[] = {23, 45, -34, 12, 0, 98, -99, 4, 189, -1, 4};
        tugOfWar(arr);
    }


    private static void tugOfWar(int arr[]) {

        int n = arr.length;
        boolean[] curr_elements = new boolean[n]; // the boolean array that contains the inclusion and exclusion of an element
        boolean[] soln = new boolean[n];
        min_diff = Integer.MAX_VALUE;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += arr[i];
            curr_elements[i] = soln[i] = false;
        }

        TOWUtil(arr, n, curr_elements, 0, soln, sum, 0, 0);

        printSolution(arr, n, soln);
    }

    private static void TOWUtil(int arr[], int n, boolean curr_elements[], int no_of_selected_elements, boolean soln[],
                                int sum, int curr_sum, int curr_position) {
        if (curr_position == n)   // out of bound
            return;

        if ((n / 2 - no_of_selected_elements) > (n - curr_position))   //checks that the numbers of elements left are not less than the number of elements
            return;

        TOWUtil(arr, n, curr_elements, no_of_selected_elements, soln, sum, curr_sum, curr_position + 1);  // not included

        no_of_selected_elements++;
        curr_sum = curr_sum + arr[curr_position];   // add the current element to the solution
        curr_elements[curr_position] = true;

        if (no_of_selected_elements == n / 2)  // // checks if a solution is formed
        {

            if (Math.abs(sum / 2 - curr_sum) < min_diff) {
                min_diff = Math.abs(sum / 2 - curr_sum);
                for (int i = 0; i < n; i++)
                    soln[i] = curr_elements[i];
            }
        } else {

            TOWUtil(arr, n, curr_elements, no_of_selected_elements, soln, sum, curr_sum, curr_position + 1); // included
        }
        curr_elements[curr_position] = false; // remove current element
    }

    private static void printSolution(int[] arr, int n, boolean[] soln) {
        System.out.print("The first subset is: ");
        for (int i = 0; i < n; i++) {
            if (soln[i] == true)
                System.out.print(arr[i] + " ");
        }
        System.out.print("\nThe second subset is: ");
        for (int i = 0; i < n; i++) {
            if (soln[i] == false)
                System.out.print(arr[i] + " ");
        }
    }

}
