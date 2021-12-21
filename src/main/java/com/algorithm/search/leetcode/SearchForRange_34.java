package com.algorithm.search.leetcode;

public class SearchForRange_34 {

    public int[] searchRange(int[] nums, int target) {


    }

    public int searchLeft(int[] nums, int target) {
        int index = -1;
        int begin = 0;
        int end = nums.length - 1;

        while (begin <= end) {

            int mid = (begin + end) / 2;
            if (target == nums[mid]) {

                while (mid > 0 && nums[mid - 1] == target){
                    mid--;
                }
                return mid;

            } else if (target < nums[mid]) {
                end = mid - 1;
            } else if (target > nums[mid]) {
                begin = mid + 1;
            }
        }

        return index;
    }


    public int searchRight(int[] nums, int target) {
        int index = -1;
        int begin = 0;
        int end = nums.length - 1;

        while (begin <= end) {

            int mid = (begin + end) / 2;
            if (target == nums[mid]) {

                while (mid < end && nums[mid + 1] == target){
                    mid++;
                }
                return mid;

            } else if (target < nums[mid]) {
                end = mid - 1;
            } else if (target > nums[mid]) {
                begin = mid + 1;
            }
        }

        return index;
    }

}
