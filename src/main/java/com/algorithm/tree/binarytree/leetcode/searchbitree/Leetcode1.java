package com.algorithm.tree.binarytree.leetcode.searchbitree;

import java.util.TreeSet;

public class Leetcode1 {


    /**
     *
     * @param nums
     * @param k: i和j的差的绝对值最大为 k
     * @param t: nums[i]和nums[j]的差的绝对值最大为 t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t){

        TreeSet<Integer> set = new TreeSet<>();

        for(int i = 0; i < nums.length; i++){

            Integer s = set.ceiling(nums[i]);
            if(s != null && s - nums[i] <= t){
                return true;
            }

            Integer g = set.floor(nums[i]);
            if(g != null && nums[i] - g <= t){
                return true;
            }

            set.add(nums[i]);
            if(set.size() > k){
                set.remove(nums[i-k]);
            }
        }
        return false;

    }


    public static void main(String[] args){

        Leetcode1 test = new Leetcode1();
        int []nums = new int[]{1, 5, 9, 1, 5, 9};
        int k = 2;
        int t = 3;
        System.out.println("result:   "+test.containsNearbyAlmostDuplicate(nums, k, t));





    }

}
