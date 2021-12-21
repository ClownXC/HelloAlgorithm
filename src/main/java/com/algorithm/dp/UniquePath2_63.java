package com.algorithm.dp;

/**
 *
 */
public class UniquePath2_63 {

    public static int uniquePaths(int[][] obstacleGrid){

        int m = obstacleGrid.length;
        if(m == 0) return 0;


        int n = obstacleGrid[0].length;
        if (n == 0) return 0;

        int [][]dp = new int[m][n];


        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j ++){


                if (obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                    continue;
                }

                if(i == 0 && j == 0){
                    dp[i][j] = 1;
                    continue;
                }

                dp[i][j] = 0;
                if (i > 0){
                    dp[i][j] += dp[i-1][j];
                }

                if (j > 0){
                    dp[i][j] += dp[i][j-1];
                }
            }
        }

        return dp[m-1][n-1];

    }




    public static void main(String[] args) {

    }
}
