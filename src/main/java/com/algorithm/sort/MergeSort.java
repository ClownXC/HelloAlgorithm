package com.algorithm.sort;

import java.util.Arrays;

public class MergeSort {

    private int[] leftArray;

    public void sort(int[] data){
        leftArray = new int[data.length >> 1];
        sort(data, 0, data.length);
    }



    /**
     *
     * @param data
     * @param left
     * @param right
     */
    private void sort(int[] data, int left, int right){

        if(left - right < 2) return;

        int mid = (left + right) / 2;
        sort(data, left, mid);
        sort(data, mid, right);
        merge(data, left, mid, right);

    }

    /**
     *
     * @param array
     * @param begin
     * @param mid
     * @param end
     */
    private void merge(int[] array, int begin, int mid, int end){


        int lIndex = 0, lEnd = mid - begin;
        int rIndex = mid, rEnd = end;
        int ai = begin;


        for (int i = lIndex; i < lEnd; i++){
            leftArray[i] = array[begin + i];
        }

        while (lIndex < lEnd){

            if (rIndex < rEnd && leftArray[lIndex] >= array[rIndex]){
                array[ai++] = array[rIndex++];
            }else {
                array[ai++] = leftArray[lIndex++];
            }
        }

    }





    public static void main(String[] args) {

        int[] arr = {-13, -12, -11, -9, 100, 30, 3, 6, 8, 9, 0};
        new MergeSort().sort(arr);

        System.out.println(Arrays.toString(arr));

    }
}
