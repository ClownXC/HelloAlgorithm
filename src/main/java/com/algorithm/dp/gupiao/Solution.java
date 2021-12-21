package com.algorithm.dp.gupiao;

public class Solution {


    public int maxProfit(int[] prices) {

        int n = prices.length;
        if (n == 0) return 0;

        int res = 0;
        int minV = prices[0];

        for (int i = 1; i < n; i++){
            res = Math.max(res, prices[i] - minV);
            minV = Math.min(minV, prices[i]);
        }

        return res;


    }

    public int maxProfit_II(int[] prices) {

        int n = prices.length;
        if (n == 0) return 0;

        int res = 0;
        int minV = prices[0];

        for (int i = 1; i < n; i++){
            res = Math.max(res, prices[i] - minV);
            minV = Math.min(minV, prices[i]);
        }

        return res;


    }


}
