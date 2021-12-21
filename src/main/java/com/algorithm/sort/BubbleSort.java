package com.algorithm.sort;


import java.util.Arrays;

public class BubbleSort {

//    /**
//     *
//     * @param arr
//     */
//    public static void sort(long[] arr){
//
//        long temp = 0;
//        for(int i = 0; i < arr.length - 1; i++){
//
//            for(int j = 0; j < arr.length - 1 - i; j++){
//
//                if(arr[j] > arr[j+1]){
//                    temp = arr[j];
//                    arr[j] = arr[j+1];
//                    arr[j+1] = temp;
//                }
//            }
//        }
//
//    }
//
//
//    /**
//     *
//     * @param arr
//     */
//    public static void sortOptimize(long[] arr){
//
//
//        long temp = 0;
//        boolean swap = false;
//
//        for(int i = 0; i < arr.length - 1; i++){
//
//            System.out.println("");
//            for(int j = 0; j < arr.length - 1 - i; j++){
//
//                if(arr[j] > arr[j+1]){
//
//                    swap = true;
//                    temp = arr[j];
//                    arr[j] = arr[j+1];
//                    arr[j+1] = temp;
//                }
//            }
//
//            if(!swap){
//                break;
//            }else{
//                swap = false;
//            }
//
//
//        }
//
//
//
//
//    }
//


    /**
     * @param arr
     */
    public static void sort(int[] arr) {



        for (int end = arr.length - 1; end > 0; end--) {

            boolean isSorted = true;
            for (int begin = 1; begin <= end; begin++) {
                if (arr[begin] < arr[begin - 1]) {
                    swap(arr[begin], arr[begin + 1]);
                    isSorted = false;
                }
            }
            if (isSorted) break;
        }

    }
    /**
     * 尾部已经局部有序，记录最后一次交换的位置
     * @param arr
     */
    public static void sort2(int[] arr) {



        for (int end = arr.length - 1; end > 0; end--) {

            int sortIdx = 1;
            for (int begin = 1; begin <= end; begin++) {
                if (arr[begin] < arr[begin - 1]) {
                    swap(arr[begin], arr[begin + 1]);
                    sortIdx = begin;
                }
            }
            end = sortIdx;
        }

    }




    private static void swap(int a, int b) {
        a = a ^ b; //00000000000000000000000000000001
        b = a ^ b; //00000000000000000000000000000010
        a = a ^ b; //00000000000000000000000000000011
    }


    public static void main(String[] args) {


        int[] arr = {-1, -10, 1, -9, 100, 30, 3, 6, 8, 9, 0};


        long[] arr2 = new long[80000];
        for (int i = 0; i < 80000; i++) {
            arr2[i] = (int) Math.random() * 80000;
        }


        BubbleSort.sort(arr);
        System.out.println(Arrays.toString(arr));


    }


}
