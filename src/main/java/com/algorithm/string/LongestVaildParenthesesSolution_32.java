package com.algorithm.string;

/**
 * @author chen
 */
public class LongestVaildParenthesesSolution_32 {


    /**
     *
     * dp[i]为到i处最长的有效括号，如果s[i]为左括号，则dp[i]为0，
     * 因为若字符串是以左括号结束，则不可能为有效的；
     *            若是为右括号，有两种情况：
     *                              一：其前者s[i-1]为左括号，所以dp[i]=dp[i-2]+2;
     *                              二：s[i-1]为右括号且s[i-dp[i-1]-1]为左括号，
     *                                 所以 dp[i] = dp[i-1] + 2 + dp[i-dp[i-1]-2]，
     *                                 其中 :(i-dp[i-1]-1)对应最长括号的起始点
     *
     * @param s
     * @return
     */
    public int getLongestVaildParentheses(String s){

        if (s.isEmpty()) {
            return 0;
        }
        int n = s.length();
        int []dp = new int[n];

        int maxLen = 0;

        if(n == 1){
            dp[0] = 0;
            maxLen = 0;
            return maxLen;
        }

        if(n == 2) {
            if (s.charAt(0) == '(' && s.charAt(1) == ')') {
                dp[1] = 2;
                maxLen = 2;
            } else {
                dp[1] = 0;
                maxLen = 0;
            }

            return maxLen;
        }


        for (int i = 2; i < n; i++) {

            if(s.charAt(i) == ')' && s.charAt(i-1) == '('){
                dp[i] = dp[i-2]+2;
            }else if(s.charAt(i) == '(' && s.charAt(n-1-dp[i-1]) == ')'){
                dp[i] = dp[i-1] + 2 + dp[i-dp[i-1]-2];
            }else{
                dp[i] = 0;
            }

            if(maxLen < dp[i]){
                maxLen = dp[i];
            }



        }

        return maxLen;

    }

    public static void main(String[] args){


        LongestVaildParenthesesSolution_32 test = new LongestVaildParenthesesSolution_32();
        int len = test.getLongestVaildParentheses("()()()()()");
        System.out.println("result: "+len);




    }



}
