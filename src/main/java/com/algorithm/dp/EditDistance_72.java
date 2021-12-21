package com.algorithm.dp;

/**
 *
 *
 * 我们首先要比较word1[i-1]和word2[j-1]是不是相同，如果相同的话，我们就不用做任何操作，所以此时f(i,j)=f(i−1,j−1)
 *
 * f(i,j)=f(i−1,j−1)（i和j都向前挪一个位置）。
 *
 * 接着对于不相同的时候我们的情况比较复杂，我们有三种处理手段，
 * 分别是insert、replace和remove。我们先看insert操作。
 * 我们insert完之后，也就是word1中的元素会保持不变，而j会向前挪一个位置，
 * 也就是f(i,j)=f(i,j−1)+1
 * f(i,j)=f(i,j−1)+1。接着考虑replace操作，
 * replace会减少word1和word2中一个需要比较的元素（i和j会向前挪一个位置），
 * 也就是f(i,j)=f(i−1,j−1)+1f(i,j)=f(i−1,j−1)+1。
 * 我们接着考虑最后一个remove操作，这个就很容易了，
 * word1中会减少一个需要比较的元素，而我们j的位置不变，也就是f(i,j)=f(i−1,j)+1
 *
 * f(i,j)=f(i−1,j)+1。所以我们最后的结果相当三者取最小值即可。
 * f(i,j)=min(f(i−1,j),f(i,j−1),f(i−1,j−1))+1  if word1[i]≠word2[j]
 *
 *
 * f(i,j)=min(f(i−1,j),f(i,j−1),f(i−1,j−1))+1  if word1[i]̸​=word2[j]
 * f(i,j)=f(i−1,j−1)   if word1[i]=word2[j]

 *
 */
public class EditDistance_72 {


    /**
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        if(m == 0) return n;
        if(n == 0) return m;


        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;//即第一列的值，可看作删
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;//即第一行的值，可看作增
        }



        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[m][n];
    }


    public static void main(String[] args) {


        System.out.println("result:---"
                +EditDistance_72.minDistance("horse","ros"));

    }
}


