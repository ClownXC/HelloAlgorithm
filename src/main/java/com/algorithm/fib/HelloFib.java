package com.algorithm.fib;

public class HelloFib {


    /**
     *
     * @param n
     * @return
     */
    public static int fib1(int n){
        if (n <= 1) return n;
        return fib1(n - 1) + fib1(n - 2);
    }


    /**
     *
     * @param n
     * @return
     */
    public static int fib2(int n){
        if (n <= 1) return n;
        int first = 0;
        int second = 1;
        for (int i = 0; i < n - 1; i++){
            int num = second + first;
            first = second;
            second = num;
        }

        return second;

    }

    public static void main(String[] args) {
        System.out.println(fib1(70));
    }
}



