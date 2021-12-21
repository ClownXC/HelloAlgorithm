package com.algorithm.dp;

/**
 *
 */
public class MaximumProductSub_152 {


    public static int maximumProduct(int[] nums){

        if(nums.length == 1){
            return nums[0];
        }

        int[] dp = new int[nums.length];
//        if(nums.length == 2){
//            return nums[0]
//        }

        dp[0] = nums[0];

        for(int i=1; i<nums.length; i++){

            if(nums[i] > 0){
                dp[i] = dp[i-1] * nums[i];
            }else{
                dp[i] = 1;
            }
        }

        return dp[nums.length-1];




    }



    public static void main(String[] args) {

        int [] nums = {2,-3,-2,5};
        System.out.println(MaximumProductSub_152.maximumProduct(nums));
    }



}
