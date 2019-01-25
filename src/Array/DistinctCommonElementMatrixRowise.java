package Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DistinctCommonElementMatrixRowise {

    public static void main(String[] args) {
        int mat[][] = {{2, 1, 4, 3},
                {1, 2, 3, 2},
                {3, 6, 2, 3},
        };

        findDistinctRowElementInMatrix(mat);
        findDistinctRowElementInMatrixBest(mat);
    }

    // BruteForce : compare the one row to another for getting common elements lead to 0(n3)

    //Better Approach:  sort the element and then do find common b/w three sorted array 0(n2log(n))
    private static void findDistinctRowElementInMatrix(int[][] mat) {


        for (int i = 0; i < mat.length; i++)   // sorting every row
            Arrays.sort(mat[i]);
        FindCommonThreeSortedArray fcs = new FindCommonThreeSortedArray();
        fcs.findCommon(mat[0], mat[1], mat[2]);
        System.out.println();

    }

    //best Approach using Hash 0(n2)
    private static void findDistinctRowElementInMatrixBest(int[][] mat) {

        Set<Integer> global = ConcurrentHashMap.newKeySet();
        for(int i=0 ; i< mat[0].length; i++){
            global.add(mat[0][i]);
        }

        for(int i=1; i< mat.length; i++) {
            HashSet<Integer> temp = new HashSet<>();
            for (int j = 0; j < mat[0].length; j++) {
                 temp.add(mat[i][j]);

            }
            global.stream().forEach((val) ->{
                if(!temp.contains(val)){
                    global.remove(val);
                }
            });
        }
        global.stream().forEach(System.out::print);

    }

}


