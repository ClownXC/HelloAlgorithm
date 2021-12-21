package com.algorithm.dp;

public class Leetcode_674 {


    public int findLengthOfLCIS(int[] nums) {

        int n = nums.length;
        if (n == 0){
            return 0;
        }

        int []dp = new int[n];
        int res = 0;

        for (int i = 0; i < n; i++){

            dp[i] = 1;
            if (nums[i] > nums[i - 1]){
                dp[i] = dp[i - 1] + 1;
            }

            res = Math.max(res, dp[i]);
        }
        return res;
    }


    public int findLengthOfLCISOptimizer(int[] nums) {

        int res = 0;

        return res;



    }


    public static void main(String[] args) {

    }

}
