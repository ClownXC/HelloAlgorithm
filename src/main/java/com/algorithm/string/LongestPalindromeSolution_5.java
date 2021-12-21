package com.algorithm.string;


/**
 * @author zxc
 *
 *  给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
 *  示例 1：
 *  输入: "babad"
 *  输出: "bab"
 *  注意: "aba"也是一个有效答案。
 *
 *  示例 2：
 *  输入: "cbbd"
 *  输出: "bb"
 *
 */
public class LongestPalindromeSolution_5 {


    /**
     *
     *
     * 首先我们从子问题入手，并将子问题的解保存起来，然后在求解后面的问题时，反复的利用子问题的解，可以极大的提示效率
     *
     * 由于最长回文子串是要求连续的，所以我们可以假设:
     *                                        j为子串的起始坐标，i为子串的终点坐标，
     *                                        其中 i 和 j 都是大于等于 0 并且小于字符串长度 length 的，
     *                                        且 j <= i，这样子串的长度就可以使用 i - j + 1 表示了
     *
     * 我们从长度为 1 的子串依次遍历，长度为 1 的子串肯定是回文的，其长度就是 1；
     * 然后是长度为 2 的子串依次遍历，只要 str[i] 等于 str[j] ，它就是回文的，其长度为 2；
     * 接下来就好办了,长度大于 2 的子串,如果它要满足是回文子串的性质
     *              就必须有 str[i] 等于 str[j],并且去掉两头的子串 str[j+1 ... i-1] 也一定是回文子串.
     *              所以我们使用一个数组来保存以 j 为子串起始坐标，i 为子串终点坐标的子串是否是回文的.
     *              由于我们是从子问题依次增大求解的，所以求解 [i ... j] 的问题时，比它规模更小的问题结果都是可以直接使用的了.
     *
     *
     * @param s
     * @return
     */
    public String longestPalindromeByDP(String s) {

        if (s.isEmpty()) {
            return s;
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];


        // 记录最长回文子串最长长度
        int maxLen = 1;
        // 记录最长回文子串起始位置
        int start = 0;

        for (int i = n - 2; i >= 0; i--) {

            dp[i][i] = true;

            for (int j = i + 1; j < n; j++) {

                dp[i][j] = (s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i+1][j-1]));
                // 如果 [i, j] 是回文子串，并且长度大于 max，则刷新最长回文子串
                if(dp[i][j] && (maxLen < j - i)){

                    maxLen = j-i+1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }


    /**
     *
     * Manacher是一个专门用作处理最长回文子串的方法，思想很巧妙，比较难以理解，这里直接借用了别人的讲解方法
     * 其实主要思想是，把给定的字符串的每一个字母当做中心，向两边扩展，这样来找最长的子回文串，这个叫中心扩展法，
     * 但是这个方法还要考虑到处理abba这种偶数个字符的回文串
     * Manacher法将所有的字符串全部变成奇数个字符。
     *
     * @param s
     * @return
     */
    public String longestPalindromeByManacher(String s) {
        return null;
    }







    public static void main(String[] args){


        LongestPalindromeSolution_5 test = new LongestPalindromeSolution_5();
        String len = test.longestPalindromeByDP("abcabcbb");
        System.out.println("result:------"+len);




    }

}
