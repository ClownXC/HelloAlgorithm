package com.algorithm.search;

import java.util.Arrays;

public class FibonacciSearch {


    private static final int FIB_SIZE = 100;
    public static void main(String[] args) {


        int[] arr = new int[100];

        for(int i = 0; i < 100; i++){
            arr[i] = i;
        }


        int res = FibonacciSearch.search(arr, 0, arr.length-1, 1);
        System.out.println("res:    "+res);


    }

    /**
     *
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static int search(int[] arr, int left, int right, int findVal){

        int mid = 0;
        int k = 0;
        int high = right;
        int low = left;
        int fib[] = getFib(FIB_SIZE);


        while (high > fib[k] - 1){
            k++;
        }
        int[] temp = Arrays.copyOf(arr, fib[k]);

        for(int i = high+1; i < temp.length; i++ ){
            temp[i] = arr[high];
        }

        while(low <= high){

            mid = low + fib[k - 1] - 1;

            if(findVal > temp[mid]){
                low = mid + 1;
                k -= 2;
            }else if(findVal < temp[mid]){
                high = mid - 1;
                k--;
            }else{

                if(mid >= high){
                    return high;
                }else {
                    return mid;
                }

            }

        }
        return -1;



    }

    /**
     *
     * @return
     */
    private static int[] getFib(int size){


        int[] fib = new int[size];
        fib[0] = 1;
        fib[1] = 1;

        for(int i = 2; i < fib.length; i++){
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;

    }





}
