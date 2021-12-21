package com.algorithm.recall.queen;

/**
 * @author zxc
 */
public class QueenQuestion {

    private static Integer QUEEN_NUM = 0;        //使用常量来定义，方便之后解N皇后问题
    private static int count = 0;

    static int[] chessboard;

    public QueenQuestion(Integer QUEEN_NUM) {
        this.QUEEN_NUM = QUEEN_NUM;
        chessboard = new int[QUEEN_NUM];
    }

    /**
     * @param n
     * @return
     */
    private boolean isConflict(int n) {


        for (int i = 0; i < n; i++){
            if (chessboard[i] == chessboard[n] || Math.abs(n - i) == Math.abs(chessboard[n] - chessboard[i])){
                return true;
            }
        }

        return false;
    }


    private void print(){

        count++;
        for (int i = 0; i < chessboard.length; i++){
            System.out.print(chessboard[i] + " ");
        }
        System.out.println();
    }


    /**
     *
     * @param n
     */
    private void putQueen(int n){

        if (n == QUEEN_NUM){
            print();
            return;
        }

        for (int i = 0; i < QUEEN_NUM; i++){
            chessboard[n] = i;
            if (! isConflict(n)){
                putQueen(n + 1);
            }
        }
    }

    public void put(){
        putQueen(0);
    }


    public static void main(String[] args) {

        QueenQuestion queen = new QueenQuestion(16);
        queen.put();
        System.out.println("count: " + count);

    }
}
