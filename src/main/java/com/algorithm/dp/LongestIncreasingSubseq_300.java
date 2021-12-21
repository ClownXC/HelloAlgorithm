package com.algorithm.dp;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;

public class LongestIncreasingSubseq_300 {


    public static int longIncreasing(int[] nums){

        if(nums == null || nums.length == 0){
            return 0;
        }
        boolean [][]dp = new boolean[nums.length][nums.length];
        List<Integer> list = new ArrayList<>();

        dp[0][0] = true;

        for(int i = 0; i<nums.length; i++){

            int res = 0;
            for(int j = i+1; j< nums.length; j++){

                if(nums[j] > nums[j-1]){
                    dp[i][j] = dp[i][j-1];
                }else{
                    res = j-i;
                    list.add(res);
                    break;
                }
            }

        }

        for(int i : list){
            System.out.println("------"+i+"------");
        }

        return list.get(3);






    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7,101,18};
        System.out.println(LongestIncreasingSubseq_300.longIncreasing(nums));
    }
}
