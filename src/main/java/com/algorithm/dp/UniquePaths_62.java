package com.algorithm.dp;

public class UniquePaths_62 {



    public static int uniquePaths(int m, int n){

        if(m <= 0 || n <= 0){
            return 0;
        }

        int [][]dp = new int[m][n];

        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j ++){

                if(i == 0 || j ==0 ){
                    dp[i][j] = 1;
                }else{
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }


            }
        }

        return dp[m-1][n-1];

    }

    public static void main(String[] args) {
        System.out.println(UniquePaths_62.uniquePaths(7, 3));

    }
}
