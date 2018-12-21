package Miscellaneous;

//https://www.geeksforgeeks.org/search-a-word-in-a-2d-grid-of-characters/

public class FindWordInMatrix {

    public static void main(String[] args) {
        char[][] input = {
                {'G', 'E', 'E', 'K', 'S', 'F', 'O', 'R', 'G', 'E', 'E', 'K', 'S'},
                {'G', 'E', 'E', 'K', 'S', 'Q', 'U', 'I', 'Z', 'G', 'E', 'E', 'K'},
                {'I', 'D', 'E', 'Q', 'A', 'P', 'R', 'A', 'C', 'T', 'I', 'C', 'E'}
        };
        findWord(input, "GEEK");
        findWordAllPosible(input, "GEEK");
    }


    private static void findWord(char[][] mat, String word) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (findWordUtils(i, j, mat, word)) {
                    System.out.println("found");
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

  //https://www.geeksforgeeks.org/find-all-occurrences-of-the-word-in-a-matrix/

    private static void findWordAllPosible(char[][] mat, String word) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (findAllposibleWordUtils(i, j, mat, word,-1,-1,0)) {
                    System.out.println("found");
                    return;
                }
            }
        }
        System.out.println("NOT FOUND");
    }

    private static boolean findAllposibleWordUtils(int row, int col, char[][] mat, String word, int preRow, int preCol, int index) {

        if (index == word.length())     //last word
            return true;
        if (row >= mat.length || col >= mat[0].length || row < 0 || col < 0 || row == preRow || col == preCol)  //boundary condition
            return false;
        if (mat[row][col] != word.charAt(index)) // un-matching
            return false;

        int[] x = {1, 0, -1, 0, 1, -1, 1, -1};
        int[] y = {0, 1, 0, -1, 1, -1, -1, 1};
        for (int i = 0; i < x.length; i++) {
            if (findAllposibleWordUtils(row + x[i], col + y[i], mat, word, row, col, index + 1))
                return true;
        }
        return false;
    }

}

