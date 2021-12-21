package com.algorithm.greedy.leetcode.boats_to_save_people881;


import java.util.Stack;

/**
 * 解题的思路比较简单，即先将数组按升序排序，然后用双指针法
 * 一个指针为起始元素，另一个为结尾元素，因为每条船最多装两个人
 * 所以我们判断前指针所指元素加上后指针所指的元素的和是否超过最大限制
 * 如果不超过则两个人都可以被装载，如果超过最大限制，则只能装载后指针所指的值
 * 数组的最大值默认是小于等于限制值的
 * 整个思路简单来说就是贪心算法，每次装尽可能多的人，以装值最大的数为主
 * 如果可以带上前指针的值（两个之和小于限制值）则该船带上前指针的值，否则只搭载后指针的值。
 */
public class Solution {

    public static void main(String[] args) {

        String res = removeKdigits("10200", 1);
        System.out.println(res);


    }


    public static String removeKdigits(String num, int k){

        Stack<Integer> stack = new Stack<>();
        StringBuilder res =  new StringBuilder();

        for (int i = 0; i < num.length(); i++){

            int number = num.charAt(i) - '0';

            while (stack.size() != 0 && stack.peek() > number && k > 0){
                stack.pop();
                k--;
            }


            if (number != 0 || stack.size() != 0){
                stack.push(number);
            }

        }

        while (stack.size() != 0 && k > 0){
            stack.pop();
            k--;
        }


        while (! stack.isEmpty()){
            res.append(stack.pop());
        }
        if (res.length() == 0){
            res.append('0');
        }

        return res.reverse().toString();
    }


}
