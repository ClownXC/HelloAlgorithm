package com.algorithm.recall.l79;


public class Solution {


    private static final int [][]direct = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private int m, n;
    Boolean[][] visited;




    private boolean inArea(int x, int y){
        return x >=0 && x <= m && y >= 0 && y < n;
    }


    /**
     *
     * @param board
     * @param word
     * @param index
     * @param startX
     * @param startY
     * @return
     */
    private boolean searchWord(final char[][] board,
                               final String word,
                               int index,
                               int startX,
                               int startY){

        char[] wordArr = word.toCharArray();

        if (index == wordArr.length - 1){
            return board[startX][startY] == wordArr[index];
        }

        if (board[startX][startY] == wordArr[index]){

            visited[startX][startY] = true;

            for (int i = 0; i < 4; i++){
                int nextX = startX + direct[i][0];
                int nextY = startY + direct[i][1];
                if (inArea(nextX, nextY) && !visited[nextX][nextY]
                        && searchWord(board, word, index + 1, nextX, nextY)){

                    return true;

                }

            }

            visited[startX][startY] = false;
        }

        return false;

    }


    /**
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(final char[][] board, String word){

        m = board.length;
        assert (m > 0);
        n = board[0].length;

        visited = new Boolean[m][n];

        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if (searchWord(board, word, 0, i, j)){
                    return true;
                }
            }
        }
        return false;
    }


}
