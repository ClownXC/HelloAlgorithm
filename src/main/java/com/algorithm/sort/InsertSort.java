package com.algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertSort {


    public static void sort(long[] arr){


        long insertVal = 0;
        int insertIndex = 0;

        for(int i = 1; i < arr.length; i++) {

            insertVal = arr[i];
            insertIndex = i - 1;

            // ...
            while(insertIndex >= 0 && insertVal < arr[insertIndex]){

                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex --;
            }

            // ...
            if(insertIndex + 1 != i){
                arr[insertIndex + 1] = insertVal;
            }


        }

    }

    public static void main(String[] args) {


        long[] arr = {-13, -12, -11, -9, 100, 30, 3, 6, 8, 9, 0};


        long []arr2 = new long[800000];
        for(int i = 0; i < 800000; i++){
            arr2[i] = (int)Math.random() * 800000;
        }


        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("Before:------ "+format.format(date));

        InsertSort.sort(arr2);
//        System.out.println(Arrays.toString(arr));


        Date date2 = new Date();
        System.out.println("After :------ "+format.format(date2));
    }









}
