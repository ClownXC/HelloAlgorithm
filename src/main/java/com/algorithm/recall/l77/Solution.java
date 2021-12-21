package com.algorithm.recall.l77;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    List<List<Integer>> res = new ArrayList<>();


    /**
     *
     * @param n
     * @param k
     * @param start
     * @param c
     */
    private void generateCombinations(int n, int k, int start, List<Integer> c) {
        if (c.size() == k) {
            res.add(c);
            return;
        }

        for (int i = start; i <= n - (k - c.size()) + 1; i++) {
            c.add(i);
            generateCombinations(n, k, i + 1, c);
            c.remove(i);
        }

        return;

    }


    /**
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        res.clear();
        if (n <= 0 || k <= 0 || k > n) {
            return res;
        }
        List<Integer> c = new ArrayList<>();
        generateCombinations(n, k, 1, c);

        return res;
    }


    public static void main(String[] args) {
        new Solution().combine(4, 2);
    }


}
