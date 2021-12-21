package com.algorithm.sort;

import java.util.Arrays;

public class QuickSort {


    public static void main(String[] args) {

        int[] arr = {0,0,1,2,4,2,2,3,1,4};

        new QuickSort().sort(arr);

        System.out.println(Arrays.toString(arr));

    }


    private void swap(int a, int b) {
        a = a ^ b; //00000000000000000000000000000001
        b = a ^ b; //00000000000000000000000000000010
        a = a ^ b; //00000000000000000000000000000011
    }


    /**
     *
     * @param arr
     * @param begin
     * @param end
     * @return
     */
    private int getPivot(int[] arr, int begin, int end) {

        swap(begin, (int) (Math.random() * (end - begin) + begin));
        int pivot = arr[begin];
        end--;


        while (begin < end) {

            while (begin < end) {
                if (arr[end] > pivot) {
                    end--;
                } else {
                    arr[begin++] = arr[end];
                    break;
                }
            }

            while (begin < end) {
                if (arr[begin] < pivot) {
                    begin++;
                } else {
                    arr[end--] = arr[begin];
                    break;
                }
            }
        }

        return begin;

    }

    public void sort(int[] arr) {
        sort(arr, 0, arr.length);
    }

    /**
     *
     * @param arr
     * @param start
     * @param end
     */
    private void sort(int[] arr, int start, int end) {

        if (end - start < 2) return;
        int pivot = getPivot(arr, start, end);
        sort(arr, 0, pivot);
        sort(arr, pivot + 1, end);

    }
}
