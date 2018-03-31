import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NQueens {


    /**
     * Checks the 45° and 135° diagonals for an existing queen. For example, if the board is a 5x5
     * and you call checkDiagonal(board, 3, 1), The positions checked for an existing queen are
     * marked below with an `x`. The location (3, 1) is marked with an `o`.
     *
     * ....x
     * ...x.
     * x.x..
     * .o...
     * .....
     *
     * Returns true if a Queen is found.
     *
     * Do not modify this function (the tests use it)
     */
    public static boolean checkDiagonal(char[][] board, int r, int c) {
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }
        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y--;
        }
        return false;
    }


    public static boolean checkDiagonalOther(char[][] board, int r, int c) {
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }
        y = r + 1;
        x = c - 1;
        while (x >= 0 && y < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x--;
            y++;
        }

        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y--;
        }
        y = r + 1;
        x = c + 1;
        while (y < board[0].length && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y++;
        }
        return false;
    }

    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }


    public static List<char[][]> nQueensSolutions(int n) {

        List<char[][]> answers = new ArrayList<>();
        char[][] board = new char[n][n];

        for(int i = 0; i<n; i++){
            for(int b = 0; b<n;b++){
                board[i][b] = '.';
            }
        }
        // for loop for all the coloms in the bootom row of the board
        // if repQuess is a valid solution
        // add it to the answers
        // else, try the next one
        //for(int i = 0; i<n; i++){
            //board[i][0] = 'Q';
        int[][] queensPos = new int[n][2];
        repQueensSolutions(board, 0, 0, answers, queensPos);
            //else{
//                for(int p = 0; p<n; p++){
//                    for(int b = 0; b<n;b++){
//                        board[p][b] = '.';
//                    }
//                }
//            }
        //}



        //answers.add(board);

        //answers = copyOf(board);
        //char b = answers.add(0,0,'e');
        return answers;
    }

    public static boolean isValid(char[][] b){
        int count = 0;
        for(int i = 0; i < b.length; i++){
            for(int v = 0; v < b.length; v++){
                if(b[i][v] == 'Q'){
                    count++;
                }
            }
        }
        if(count == b.length){
            return true;
        }
        return false;
    }

    public static boolean checkVert(char[][] board, int row, int col){

        for(int i = 0; i < board.length; i++){
            if(board[col][i] == 'Q' && i != row){
                return true;
            }
        }
        return false;
    }


    public static boolean repQueensSolutions(char[][] board, int rowNum, int colInd, List<char[][]> answers, int[][] queenPos ) {
        // for the given row number
        // check if colInd =size
        // return board

        //for every col in the row
        // put a queen in
        // check vertically
        // if it fits, return true
        // else remove it and replace it with the .

        //return false

        if(rowNum >= board.length){
            if(isValid(board)){
                char[][] b = copyOf(board);
                answers.add(b);
                return true;
            }
            return false;
            //should this be true or false?
            // dose size work here?
        }

        for(int i=colInd; i< board.length; i++){
            if(i == 2 && rowNum == 0){
                int beene = 4;
            }
            if(!checkDiagonalOther(board,i, rowNum)){

                //char[][] tes = flipped(copyOf(board), i, rowNum);
                //System.out.println(checkDiagonal(tes, copyOf(board).length - rowNum,i));
                //boolean nn = checkDiagonal(tes,  i, board.length - rowNum);

                //if(!checkDiagonal(tes,   i,(board.length - 1) - rowNum)){
                    board[i][rowNum] = 'Q';
                    if(!checkVert(board, rowNum, i)){
                        //checkDiagonal(tes, i+1, (board.length - rowNum)+1)
                        repQueensSolutions(board, rowNum+1, 0, answers, queenPos);
                        board[i][rowNum] = '.';
                    }
                //}
            }
            board[i][rowNum] = '.';
            colInd++;
        }

        return false;

    }

    public static char[][] flipped(char[][] org, int a, int tt){
        char[][] b = new char[org.length][org[0].length];
        for(int i = org.length-1;i>=0; i--){
            for(int s = 0; s<org.length;s++){
                b[s][i] = org[s][org.length-1-i];
            }
        }
        System.out.println("ddddddd");
        System.out.println(checkDiagonal(b, a , org.length-1-tt));
        return b;
    }
//
//    public static char[][] checkFlipped(char[][] org, int col, int row, char[][] queens){
//        //char[][] b = new char[org.length][org[0].length];
//        for(int i = 0; i < queens.length; i++){
//            if()
//            for(int s = 0; s<org.length;s++){
//                b[s][i] = org[s][i];
//            }
//        }
//        return b;
//    }

}
