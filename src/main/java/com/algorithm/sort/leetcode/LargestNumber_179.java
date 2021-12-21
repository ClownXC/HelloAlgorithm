package com.algorithm.sort.leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber_179 {


    public static String largestNumber(int[] nums) {

        // 如果num数组为[1,17]，那么由于117<171，那么我们希望[1，17]的顺序变为[17，1]
        // 由于int型数组没有自定义排序的功能，而结果又需要转化为字符串
        // 字符串有自定义的排序，那么我们首先将原数组转化为字符串数组
        String[] strings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strings[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(strings, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            stringBuilder.append(strings[i]);
        }


        while('0' == stringBuilder.charAt(0)){
            stringBuilder.deleteCharAt(0);
            if(0 == stringBuilder.length()){
                return "0";
            }
        }
        return stringBuilder.toString();
    }




    public static void main(String[] args) {


        int[] nums = {3, 30, 34, 5, 9};
        String latestStr = LargestNumber_179.largestNumber(nums);
        System.out.println("latestStr:  "+latestStr);

    }
}
