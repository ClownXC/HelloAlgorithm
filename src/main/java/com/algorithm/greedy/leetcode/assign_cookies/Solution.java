package com.algorithm.greedy.leetcode.assign_cookies;

import java.util.Arrays;

public class Solution {

    public static int assign(int[] belly, int[] cookies){

        Arrays.sort(belly);
        Arrays.sort(cookies);

        int child = 0;
        int cookie = 0;

        while (child < belly.length && cookie < cookies.length){

            if (belly[child] <= cookies[cookie]){
                child++;
            }
            cookie++;


        }
        return child;


    }

    public static void main(String[] args) {

        int[] g = {1, 2};
        int[] c = {1, 2, 3};
        int res = assign(g, c);
        System.out.println(res);

    }

}
