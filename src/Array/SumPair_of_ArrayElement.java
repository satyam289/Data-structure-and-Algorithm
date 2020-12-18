package Array;

public class SumPair_of_ArrayElement {

    int[] ab;

    public static void main(String[] args) {
        SumPair_of_ArrayElement s = new SumPair_of_ArrayElement();
        int[] a = new int[]{3, 4, 6, 5, 1, 7, 2, 0};   //one way do iterate all element in array but that is wrost
        s.doquicksort(a, 0, a.length - 1);

        for (int i = 0; i < a.length; i++) {
            if (a[i] > 7)
                break;
            for (int j = i; j < a.length; j++) {
                if (a[j] > 7)
                    break;
                if (a[i] + a[j] == 7)
                    System.out.println("(" + a[i] + "," + a[j] + ")");
            }
        }
    }


    public void doquicksort(int[] a, int lower, int upper) {   //overall best(except sorted Array and small number of item in Array)
        int size = upper - lower + 1;
        if (size < 5) {                                 // For less element quick do lot of swapping, better use insertion sort or any other
            doInsertionSort(a);
        } else {
            if (lower < upper) {
                int mid = median(a, lower, upper);     //to avoid the worst case scenario (sorted Array)
                int pivot = a[mid];
                //int pivot=a[(lower+upper)/2];
                int index = partition(a, lower, upper, pivot);
                doquicksort(a, lower, index - 1);
                doquicksort(a, index + 1, upper);
            }
        }
    }

    public int partition(int[] a, int lower, int upper, int pivot) {
        int lowerptr = lower;
        int upperptr = upper;
        while (true) {
            while (a[lowerptr] < pivot)   //pointer got stop at pivot so need " lowerptr <upper " in case next time it will cross upperptr then program will terminate.
                lowerptr++;
            while (pivot < a[upperptr])   //same
                upperptr--;
            if (lowerptr >= upperptr)
                break;
            else {
                swap(a, lowerptr, upperptr);
                lowerptr++;
                upperptr--;
            }
        }
        return lowerptr;
    }

    public int median(int[] a, int left, int right) {   //making sure that middle element is proper , otherwise partition would be  worst (means more swapping leading to n2)
        int mid = (left + right) / 2;
        if (a[left] > a[mid])
            swap(a, left, mid);

        if (a[left] > a[right])
            swap(a, right, mid);

        if (a[mid] > a[right])
            swap(a, mid, right);
        return mid;
    }

    public void doInsertionSort(int[] a) {

        for (int i = 1; i < a.length; i++) {
            int j = i;
            int key = a[i];
            while (j > 0 && a[j - 1] > key) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = key;
        }
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
