package Array;

//https://leetcode.com/problems/word-search/
public class WordSearch {
    
    public static boolean wordSearch(char[][] board, String word) {
        boolean [][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    return wordSearchDfs(board, visited, i, j, word, 0);
                }
            }
        }
        return false;
    }

    private static boolean wordSearchDfs(char[][] board, boolean[][] visited, int row, int col, String word, int wordIndex) {
        if (wordIndex == word.length()) {
            return true;
        }
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }
        if (visited[row][col] || board[row][col] != word.charAt(wordIndex)) {
            return false;
        }
        /*** Alernate to visited  ***
        char temp = board[row][col];
        board[row][col] = '';
        */
        visited[row][col] = true;
        boolean found = wordSearchDfs(board, visited, row - 1, col, word, wordIndex + 1)
                || wordSearchDfs(board, visited, row + 1, col, word, wordIndex + 1)
                || wordSearchDfs(board, visited, row, col + 1, word, wordIndex + 1)
                || wordSearchDfs(board, visited, row, col - 1, word, wordIndex + 1);

        visited[row][col] = false;
        /*
        board[row][col] = temp;
        */
        return found;
    }

    public static void main(String [] args){
        char [][] board = {{'A','B','C','E'},
                           {'S','F','C','S'},
                           {'A','D','E','E'}};
        String word = "ABCCED";
        System.out.println(wordSearch(board, word));
    }
}
