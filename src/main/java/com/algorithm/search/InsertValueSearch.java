package com.algorithm.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen
 */
public class InsertValueSearch {


    public static void main(String[] args) {


        int[] arr = new int[100];

        for(int i = 0; i < 100; i++){
            arr[i] = i;
        }


        int res = InsertValueSearch.search(arr, 0, arr.length-1, 1);
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

        if(left > right || findVal < arr[0] || findVal > arr[arr.length - 1]){
            return -1;
        }
        int i = 1;
        System.out.println("==================插值查找---->第"+ i++ + ":次 查找==================");

        int mid = left + ((findVal - arr[left]) / (arr[right] - arr[left]))
                * (left - right);

        System.out.println("===mid---->"+ mid);
        System.out.println("===findVal---->"+ findVal);
        System.out.println("===arr[mid]---->"+ arr[mid]);


        if(findVal > arr[mid]){
            return search(arr, left + 1, mid, findVal);
        }else if(findVal < arr[mid]){
            return search(arr, left, mid-1, findVal);
        }else{
            return mid;
        }



    }


}
