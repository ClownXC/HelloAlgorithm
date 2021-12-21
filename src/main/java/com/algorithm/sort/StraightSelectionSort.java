package com.algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author chen
 */
public class StraightSelectionSort {

    public static void sort(long[] arr){

        int len = arr.length;
        for(int i = 0; i < len - 1; i++){

            int maxIndex = i;
            long max = arr[i];

            // 获取最小值以及其下标
            for(int j = i + 1; j < len - 1; j++){
                if(max <= arr[j]){
                    maxIndex = j;
                    max = arr[j];
                }
            }

            // 交换值
            if(maxIndex != i){
                arr[maxIndex] = arr[i];
                arr[i] = max;
            }
        }

    }

    public static void main(String[] args) {


        long[] arr = {-1, -10, 1, -9, 100, 30, 3, 6, 8, 9, 0};


        long []arr2 = new long[800000];
        for(int i = 0; i < 800000; i++){
            arr2[i] = (int)Math.random() * 800000;
        }


        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("Before:------ "+format.format(date));

        StraightSelectionSort.sort(arr2);
//        System.out.println(Arrays.toString(arr));


        Date date2 = new Date();
        System.out.println("After :------ "+format.format(date2));

    }







    }
