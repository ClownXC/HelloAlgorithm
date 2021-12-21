package com.algorithm.dp;

public class Leetcode_64 {

    public int minPathSum(int[][] grid) {

        int m = grid.length;
        if (m == 0) {
            return 0;
        }

        int n = grid[0].length;
        if (n == 0) {
            return 0;
        }

        int[][] dp = new int[m][n];
        int[][] pi = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                    continue;
                }

                int temp = Integer.MAX_VALUE;
                if (i > 0) {
                    temp = Math.min(temp, dp[i - 1][j]);
                    if (temp == dp[i - 1][j]) {
                        pi[i][j] = 0;
                    }
                }

                if (j > 0) {
                    temp = Math.min(temp, dp[i][j - 1]);
                    if (temp == dp[i][j - 1]) {
                        pi[i][j] = 1;
                    }
                }
                dp[i][j] = temp + grid[i][j];
            }
        }
        int[] path = new int[m + n - 1];
        int x = m - 1;
        int y = n - 1;

        for (int p = 0; p < m + n - 1; ++p) {
            path[p] = grid[x][y];
            if (pi[x][y] == 0) {
                --x;
            } else {
                --y;
            }
        }

        for (int p = m + n - 2; p >= 0; --p){
            System.out.println(path[p]);
        }




        return dp[m - 1][n - 1];
    }


    public int minPathSumOptimizer(int[][] grid) {

        int m = grid.length;
        if (m == 0) {
            return 0;
        }

        int n = grid[0].length;
        if (n == 0) {
            return 0;
        }

        int[][] dp = new int[2][n];

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i % 2][j] = grid[i][j];
                    continue;
                }
                int temp = Integer.MAX_VALUE;
                if (i > 0) {
                    temp = Math.min(temp, dp[1 - i % 2][j]);
                }

                if (j > 0) {
                    temp = Math.min(temp, dp[i % 2][j - 1]);
                }
                dp[i % 2][j] = temp + grid[i][j];
            }
        }




        return dp[m - 1][n - 1];


    }


    public static void main(String[] args) {


    }
}
