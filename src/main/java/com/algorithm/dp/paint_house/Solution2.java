package com.algorithm.dp.paint_house;

public class Solution2 {

    public int minCostII(int[][] costs) {

        int n = costs.length;
        if (n == 0){
            return 0;
        }

        int K = costs[0].length;
        int[][] dp = new int[n + 1][K];
        int min1, min2;

        int min = 0;
        int secondMin = 0;

        int id1 = 0, id2 = 0;
        for (int i = 0; i < K; i++){
            dp[0][i] = 0;
        }
        for (int i = 1; i <= n; i++){
            min1 = min2 = Integer.MAX_VALUE;
            for (int j = 0; j < K; j++){
                if (dp[i - 1][j] < min1){
                    min2 = min1;
                    id2 = id1;
                    min1 = dp[i - 1][j];
                    id1 = j;
                }else {
                    if (dp[i - 1][j] < min2){
                        min2 = dp[i - 1][j];
                        id2 = j;
                    }
                }
            }
            for (int j = 0; j < K; j++){
                dp[i][j] = costs[i - 1][j];
                if (j != id1){
                    dp[i][j] += min1;
                }else {
                    dp[i][j] += min2;
                }

            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < K; i++){
            res = Math.min(res, dp[n][i]);
        }

        return res;
    }

}
