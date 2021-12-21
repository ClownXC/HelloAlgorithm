package com.algorithm.dp;

public class HouseRobber_198 {


    public static int hobber(int[] nums){

        if(nums.length <= 2){
            return 0;
        }

        int []dp = new int[nums.length];

        for(int i = 2; i < nums.length; i++){
            Math.max(nums[i-1] + dp[i-1], nums[i-1] + dp[i-1]);
        }

        return 0;



    }



    public static void main(String[] args) {

    }
}
