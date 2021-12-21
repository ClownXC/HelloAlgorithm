package com.algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author chen
 */
public class ShellSort {

    /**
     * 交换法
     * @param arr
     */
    public static void sort(long[] arr){


        long temp = 0;
        for(int gap = arr.length / 2; gap > 0; gap /= 2){

            // ...
            for(int i = gap; i < arr.length; i++){

                for(int j = i - gap; j >= 0; j -= gap){

                    if(arr[j] > arr[j + gap]){
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;

                    }
                }

            }

        }

    }


    /**
     * 位移法
     * @param arr
     */
    public static void sortOptimize(long[] arr){


        long insertVal = 0;
        int insertIndex = 0;

        for(int gap = arr.length / 2; gap > 0; gap /= 2){

            // ...
            for(int i = gap; i < arr.length; i++){

                insertVal = arr[i];
//                insertIndex = i - gap;
                insertIndex = i;

//                if(arr[i] < arr[i - gap]){

                    while(insertIndex-gap >= 0 && arr[i] < arr[i - gap]) {

                        arr[i] = arr[i - gap];
                        insertIndex -= gap;
                    }
//                }

//                if(insertIndex + gap != i) {
//                    arr[insertIndex + gap] = insertVal;
                    arr[insertIndex] = insertVal;
//                }

            }

        }






    }

    public static void main(String[] args) {


        long[] arr = {-1, -10, 1, -9, 100, 30, 3, 6, 8, 9, 0};


//        long []arr2 = new long[80000];
//        for(int i = 0; i < 80000; i++){
//            arr2[i] = (int)Math.random() * 80000;
//        }
//
//
//        Date date = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//
//
//
//
//
        ShellSort.sortOptimize(arr);
        System.out.println(Arrays.toString(arr));
    }









}
