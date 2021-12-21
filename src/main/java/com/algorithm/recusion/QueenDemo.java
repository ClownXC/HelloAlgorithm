package com.algorithm.recusion;

public class QueenDemo {


    private static final int QUEEN_COUNT = 8;
    private static int JUDGE_COUNT = 0;
    private static int METHOD_COUNT = 0;
    private int[] array = new int[QUEEN_COUNT];

    public static void main(String[] args) {

        QueenDemo queenDemo = new QueenDemo();
        queenDemo.put(0);

        System.out.println("METHOD_COUNT:           " + METHOD_COUNT);
        System.out.println("JUDGE_COUNT:           " + JUDGE_COUNT);


    }

    /**
     *
     */
    private void print(){

        METHOD_COUNT ++;
        for(int i = 0; i < QUEEN_COUNT; i++){
            System.out.print(array[i] + " ");
        }

        System.out.println();
    }

    /**
     *
     * @param n
     * @return
     */
    private boolean isConflict(int n){

        JUDGE_COUNT ++;
        for(int i = 0; i < n; i++){
            if(array[i] == array[n] || Math.abs(i-n) == Math.abs(array[i] - array[n])){
                return true;
            }
        }

        return false;

    }


    /**
     *
     * @param n
     */
    public void put(int n){
        if(n == QUEEN_COUNT){
            print();
            return;
        }

        for(int i = 0; i < QUEEN_COUNT; i++){
            array[n] = i;
            // wrong !!!
//            if(!isConflict(i)){
//                put(n+1);
//            }

            if(!isConflict(n)){
                put(n+1);
            }


        }


    }

}
