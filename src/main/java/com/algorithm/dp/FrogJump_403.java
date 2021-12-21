package com.algorithm.dp;


public class FrogJump_403 {


    public static boolean jump(int[] nums){




        if(nums[1] != 1 || nums[0] != 0){
            return false;
        }

        int len = nums.length;
        boolean []dp = new boolean[len+1];

        dp[0] = true;
        dp[1] = true;

        for(int i = 2; i <= len; i++){

            int skip = nums[i-1] - nums[i-2];

            dp[i] = (dp[i-1] && (nums[i-1] + skip == nums[i]))
                    || (dp[i-1] && (nums[i-1] + skip + 1 == nums[i]))
                    || (dp[i-1] && (nums[i-1] + skip - 1 == nums[i]));

        }

        return dp[len];


    }


    public static void main(String[] args) {


        int [] stones = {0,1,3,5,6,8,12,17};

    }
}
