package com.algorithm.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zxc
 *
 *
 *
 *
 *
 *
 *
 */
public class LongestSubstringSolution {

    /**
     *
     * @param s
     * @return
     */
    public int getLengthOfLongestSubstring(String s){

        int n = s.length();
        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int j = 0, i = 0; j < n; j++){
            if(map.containsKey(s.charAt(j))){
                i = Math.max(map.get(s.charAt(j)), i);
            }

            ans = Math.max(ans, j-i+1);
            map.put(s.charAt(j), i+1);
        }

        return ans;
    }

//    /**
//     *
//     * @param s
//     * @param start
//     * @param end
//     * @return
//     */
//    public boolean allUnique(String s, int start, int end){
//
//        TreeSet<Integer> set = new TreeSet<>();
//
//
//
//    }


    public static void main(String[] args){


        LongestSubstringSolution test = new LongestSubstringSolution();
        int len = test.getLengthOfLongestSubstring("abcabcbb");
        System.out.println("result: "+len);




    }

}
