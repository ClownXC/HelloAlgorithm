package com.algorithm.dp;

import com.sun.corba.se.pept.transport.ReaderThread;

public class HouseRobber2_199 {

    public static void main(String[] args) {

    }

    public int rob(int[] nums) {

        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int []dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = nums[0];

        for (int i = 2; i <= n; i++){
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[n];

    }


    public int robII(int[] nums) {

        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int[] A = new int[n - 1];


        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++){
            A[i] = nums[i];
        }

        res = Math.max(res, rob(A));

        for (int i = 0; i < n - 1; i++){
            A[i] = nums[i + 1];
        }

        res = Math.max(res, rob(A));

        return res;








    }




    public int robOptimizer(int[] nums) {

        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];


        int old = 0;
        int now = nums[0];
        for (int i = 2; i <= n; i++){
            int temp = Math.max(old + nums[i - 1], now);
            old = now;
            temp = now;
        }
        return now;

    }





}
