
package Miscellaneous;

//https://www.geeksforgeeks.org/validity-of-a-given-tic-tac-toe-board-configuration/
class ValidityTacToe {

    private static int[][] combination = { { 0, 1, 2 }, // first row
            { 3, 4, 5 }, // second row
            { 6, 7, 8 }, // third row
            { 0, 3, 6 }, // first column
            { 1, 4, 7 }, // second column
            { 2, 5, 8 }, // third column
            { 0, 4, 8 }, // first diagonal
            { 2, 4, 6 } }; // second diagonal

    private static boolean isWin(char[] board, char c) {
        for (int i = 0; i < 8; i++) {
            if (board[combination[i][0]] == c && board[combination[i][1]] == c && board[combination[i][2]] == c) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValid(char[] board) {
        int xCount = 0, oCount = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 'X') {
                xCount++;
            }
            if (board[i] == 'O') {
                oCount++;
            }
        }
        if (xCount == oCount || xCount == oCount + 1) {
            if (isWin(board, 'O')) {
                if (isWin(board, 'X')) {
                    return false;
                }
                return xCount == oCount;
            }
            if (isWin(board, 'X')) {
                return xCount == oCount + 1;
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        char board[] = { 'X', 'X', 'O', 'O', 'O', 'X', 'X', 'O', 'X' };
        if ((isValid(board))) {
            System.out.println("Given board is valid");
        } else {
            System.out.println("Given board is not valid");
        }
    }
}