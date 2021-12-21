package com.algorithm.tree;

import org.omg.CORBA.ARG_OUT;

import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {

        int arr[] = {4, 6, 8, 5, 9};
//        data2Heap(arr, 1, arr.length);
        heapsort(arr);
        System.out.println(Arrays.toString(arr));


    }




    public static void heapsort(int arr[]){

        int temp = 0;

        for (int i = arr.length / 2 - 1; i >= 0; i-- ){
            data2Heap(arr, i, arr.length);
        }

        for (int j = arr.length - 1; j > 0; j--){
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            data2Heap(arr, 0, j);

        }

    }

    /**
     *
     * @param arr: 待调整的数组
     * @param i: 表示非叶子节点在数组中的索引
     * @param length: 需要继续调整的节点数量
     */
    public static void data2Heap(int arr[], int i, int length){

        int temp = arr[i];

        for (int k = i*2 + 1; k < length; k = k*2 + 1){

            if(k + 1 < length && arr[k] < arr[k + 1]){
                k++;
            }
            if(arr[k] > temp){
                arr[i] = arr[k];
                i = k;
            }else {
                break;
            }

        }
        arr[i] = temp;
    }


}
