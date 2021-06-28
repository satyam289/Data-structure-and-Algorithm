package Array;

//https://leetcode.com/problems/word-search/
public class WordSearch {

    public static boolean wordSearch(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    return wordSearchDfs(board, visited, i, j, word, 0);
                }
            }
        }
        return false;
    }

    private static boolean wordSearchDfs(char[][] board, boolean[][] visited, int row, int col, String word,
            int wordIndex) {
        if (wordIndex == word.length()) {
            return true;
        }
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }
        if (visited[row][col] || board[row][col] != word.charAt(wordIndex)) {
            return false;
        }
        /***
         * Alernate to visited *** char temp = board[row][col]; board[row][col] = '';
         */
        visited[row][col] = true;
        boolean found = wordSearchDfs(board, visited, row - 1, col, word, wordIndex + 1)
                || wordSearchDfs(board, visited, row + 1, col, word, wordIndex + 1)
                || wordSearchDfs(board, visited, row, col + 1, word, wordIndex + 1)
                || wordSearchDfs(board, visited, row, col - 1, word, wordIndex + 1);

        visited[row][col] = false;
        /*
         * board[row][col] = temp;
         */
        return found;
    }

    public static void main(String[] args) {
        char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
        String word = "ABCCED";
        System.out.println(wordSearch(board, word));
    }

    // https://www.interviewbit.com/old/problems/word-search-board/
    // The same letter cell may be used more than once.
    public int iSWordFound(String[] board, String word) {
        int row = board.length;
        int col = board[0].length();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i].charAt(j) == word.charAt(0)) {
                    if (dfs(board, i, j, word, 0)) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    private boolean dfs(String[] board, int row, int col, String word, int k) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[row].length() || k > word.length()) {
            return false;
        }
        if (k == word.length()) {
            return true;
        }
        if (board[row].charAt(col) != word.charAt(k)) {
            return false;
        }
        return dfs(board, row, col - 1, word, k + 1) || dfs(board, row - 1, col, word, k + 1)
                || dfs(board, row, col + 1, word, k + 1) || dfs(board, row + 1, col, word, k + 1);
    }
}
