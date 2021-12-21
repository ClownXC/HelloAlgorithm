package com.algorithm.sort.leetcode;

/**
 * @author chen
 */
public class SortColors_75 {

    /**
     *
     * @param nums
     */
    public static void sortColors(int[] nums) {


        if(nums==null || nums.length==0){
            return;
        }

        int left = 0;
        int right = nums.length - 1;

        for (int i = 0; i <= right; i++) {
            if (nums[i] == 0) {
                swap(nums, i, left++);

            } else if (nums[i] == 2) {
                swap(nums, i--, right--);
            }
        }
    }

    /**}
     *
     * @param nums
     * @param i
     * @param j
     */
    private static void swap(int[] nums, int i, int j) {
        int temp;

        temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }




    public static void main(String[] args) {


        int [] test = {0,0,1,0};
        SortColors_75.sortColors(test);

        for(int i : test){
            System.out.print(i+" ");
        }

    }
}
