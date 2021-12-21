package com.algorithm.search;

import com.algorithm.sort.leetcode.Hindex_274;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen
 */
public class BinarySearch {


    public static void main(String[] args) {


        int[] arr = {-1, -10, 1, -9, 100, 30, 3, 6, 8, 9, 0, 1};

        int res = BinarySearch.searchFirstIndex(arr, 0, arr.length, 1);
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
    public static int searchFirstIndex(int[] arr, int left, int right, int findVal){

        int mid = (left + right)/2;


        if(left > right){
            return -1;
        }

        if(findVal > arr[mid]){
            return searchFirstIndex(arr, left + 1, mid, findVal);
        }else if(findVal < arr[mid]){
            return searchFirstIndex(arr, left, mid-1, findVal);
        }else{
            return mid;
        }



    }



    public static int binarySearch(int[] arr, int data){

        int low = 0;
        int high = arr.length - 1;


        while ( low <= high){
            int mid = low + (high - low) / 2;

            if (data > arr[mid]){
                low = mid + 1;
            }else if (data < arr[mid]){
                high = mid - 1;
            }else {
                return mid;
            }
        }

        return -1;


    }



    /**
     *
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static List<Integer> searchAll(int[] arr, int left, int right, int findVal){

        int mid = (left + right)/2;
        List<Integer> valList = new ArrayList();



        if(left > right){
            return new ArrayList<>();
        }

        if(findVal > arr[mid]){
            return searchAll(arr, left + 1, mid, findVal);
        }else if(findVal < arr[mid]){
            return searchAll(arr, left, mid-1, findVal);
        }else{
            int temp = mid -1 ;

            while (true){

                if(temp < 0 || arr[temp] != findVal){
                    break;
                }

                valList.add(temp);
                temp -= 1;
            }

            temp = mid + 1;
            while (true){

                if(temp > arr.length || arr[temp] != findVal){
                    break;
                }

                valList.add(temp);
                temp -= 1;
            }

            valList.add(mid);
            return valList;
        }



    }
}
