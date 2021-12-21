package com.algorithm.dp;

public class  DecodeWays2_639 {


    public static int numDecodings(String ss){



        int n = ss.length();
        if(ss.length() == 0){
            return 0;
        }

        char[] s = ss.toCharArray();
        int []dp = new int[n + 1];

        dp[0] = 1;

        for (int i = 1; i <= n; i++){
            dp[i] = 0;
            if (s[i - 1] >= '1' && s[i-1] <= '9'){
                dp[i] += dp[i - 1];
            }
            if (i > 1){
                int j = 10 * (s[i - 2] - '0') + (s[i - 1] - '0');
                if (j >= 10 && j <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[n];

    }


    public static void main(String[] args) {

    }
}
