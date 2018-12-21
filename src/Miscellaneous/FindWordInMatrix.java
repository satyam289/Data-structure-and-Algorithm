package Miscellaneous;

//https://www.geeksforgeeks.org/search-a-word-in-a-2d-grid-of-characters/

public class FindWordInMatrix {

    public static void main(String[] args) {
       char [][] input={
                         {'G','E','E','K','S','F','O','R','G','E','E','K','S'},
                         {'G','E','E','K','S','Q','U','I','Z','G','E','E','K'},
                         {'I','D','E','Q','A','P','R','A','C','T','I','C','E'}
                       };
        findWord(input,"GEEP");
    }


    private static void findWord(char[][] mat, String word) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (findWordUtils(i, j, mat, word)) {
                    System.out.print("found");
                    return;
                }
            }
        }
        System.out.println("NOT FOUND");
    }

    private static boolean findWordUtils(int row, int col, char[][] mat, String word) {

        if (mat[row][col] != word.charAt(0))
            return false;
        int[] x = {1, 0, -1, 0, 1, -1, 1, -1};
        int[] y = {0, 1, 0, -1, 1, -1, -1, 1};

        for (int i = 0; i < x.length; i++) {  // start with first character
            int Nrow = row + x[i];
            int Ncol = col + y[i];
            int k;
            for (k = 1; k < word.length(); k++) {
                if (Nrow >= mat.length || Ncol >= mat[0].length || Nrow < 0 || Ncol < 0)  //boundary condition
                    break;
                if (mat[Nrow][Ncol] != word.charAt(k))  //un-matching
                    break;

                Nrow += x[i];
                Ncol += y[i];
            }
            if (k == word.length())     //last word
                return true;
        }
        return false;
    }

}
