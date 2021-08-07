package Array;

import java.util.ArrayList;

//Given an array and a value, remove all the instances of that value in the array.
public class RemoveElement {

    public int removeElement(ArrayList<Integer> a, int b) {
        int n = a.size();
        int i, j;

        for (i = 0, j = 0; j < n; j++) {
            if (a.get(j) != b) {
                a.set(i, a.get(j));
                i++;
            }
        }

        return i;
    }

    public int removeElement2(ArrayList<Integer> a, int b) {
        int n = a.size();
        int i, j;

        for (i = 0, j = 0; j < n; j++) {
            if (a.get(j) != b) {
                a.set(i, a.get(j));
                i++;
            }
        }
        int k = i;
        while (k < a.size()) {
            a.remove(k);
        }

        return i;
    }
}
