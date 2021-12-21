package com.algorithm.search.leetcode;

public class l35 {


    public static void main(String[] args) {

    }


    public int searchInsert(int[] nums, int target) {

        int index = -1;
        int begin = 0;
        int end = nums.length -1;

        while (index == -1){

            int mid = (begin + end ) / 2;
            if (target == nums[mid]){
                index = mid;
            }else if(target < nums[mid]){
                if(mid == 0 || target > nums[mid - 1]){
                    index = mid;
                }
                end = mid - 1;
            }else if(target > nums[mid]){
                if (mid == end || target < nums[mid + 1]){
                    index = mid + 1;
                }

                begin = mid + 1;
            }
        }

        return index;

    }


}
