package com.algorithm.recall.l78;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {

        Solution solution = new Solution();
        int []data = {1, 2, 3};
        List<List<Integer>> subsets = solution.subsets(data);
        for (List<Integer> s : subsets){
            for (Integer i : s){
                System.out.print(i + " ");
            }
            System.out.println();
        }

    }


    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> item = new LinkedList<>();


        result.add(item);
        if (nums == null || nums.length == 0) {
            return result;
        }
        generate(0, nums, item, result);
        return result;

    }

    /**
     *
     * @param i
     * @param nums
     * @param item
     * @param result
     */
    private void generate(int i,
                          int nums[],
                          List<Integer> item,
                          List<List<Integer>> result) {

        if (i == nums.length) {
            return;
        }

        item.add(nums[i]);
        result.add(item);
        generate(i + 1, nums, item, result);

        item.remove(item.size() - 1);
        generate(i + 1, nums, item, result);


    }


}
