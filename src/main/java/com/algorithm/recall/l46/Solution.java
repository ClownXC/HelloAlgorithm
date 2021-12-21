package com.algorithm.recall.l46;

import java.util.ArrayList;
import java.util.List;

public class Solution {


    List<List<Integer>> res;
    Boolean[] used;


    /**
     *
     * @param nums
     * @param index
     * @param p
     */
    public void generatePermutation(final List<Integer> nums,
                                    int index,
                                    List<Integer> p){

        if (index == nums.size()){
            res.add(p);
            return;
        }

        for (int i = 0; i < nums.size(); i++){
            if (! used[i]){
                p.add(nums.get(i));
                used[i] = true;
                generatePermutation(nums, index + 1, p);
                p.remove(nums.get(i));
                used[i] = false;
            }
        }
        return;
    }


    /**
     *
     * @param nums
     * @param result
     * @param index
     * @param list
     * @return
     */
    public List<List<Integer>> permute(List<Integer> nums,
                                       List<List<Integer>> result,
                                       int index,
                                       List<Integer> list){

        res.clear();
        if (nums.size() == 0){
            return res;
        }

        used = new Boolean[nums.size()];
        List<Integer> p = new ArrayList<>();
        generatePermutation(nums, 0, p);
        return res;
    }








}
