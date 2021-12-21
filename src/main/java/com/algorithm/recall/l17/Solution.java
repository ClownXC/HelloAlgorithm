package com.algorithm.recall.l17;

import java.util.ArrayList;
import java.util.List;

public class Solution {


    public static void main(String[] args) {

    }


    public List<String> letters = new ArrayList<String>(8);
    public List<String> list = new ArrayList<String>();

    public void prepareForList() {
        letters.add(" ");
        letters.add("");
        letters.add("abc");
        letters.add("def");
        letters.add("ghi");
        letters.add("jkl");
        letters.add("mno");
        letters.add("pqrs");
        letters.add("tuv");
        letters.add("wxyz");
    }


    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return list;
        }
        prepareForList();
        letterCombinations(list, digits, 0, "");
        return list;
    }


    /**
     * @param list   存放最后结果的list
     * @param digits 输入的数字字符串
     * @param index  当前遍历digits的下标
     * @param cur    当前的字符串
     * @return
     */
    public void letterCombinations(List<String> list, String digits, int index, String cur) {
        if (index == digits.length()) {
            if (index == digits.length()) {
                list.add(cur);
            }
            return;
        }

        char c = digits.charAt(index);
        assert (c >= '0' && c <= '9' && c != '1');
        String temp = letters.get(c - '0');

        for (int i = 0, len = temp.length(); i < len; i++) {
            String next = cur + temp.charAt(i);
            letterCombinations(list, digits, index + 1, next);
        }

    }
}


