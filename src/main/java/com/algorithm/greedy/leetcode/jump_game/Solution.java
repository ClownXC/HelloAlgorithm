package com.algorithm.greedy.leetcode.jump_game;

public class Solution {


    public static void main(String[] args) {


    }

    public boolean canJump(int[] nums){

        int []index = new int[nums.length];
        for (int i = 0; i < nums.length; i++){
            index[i] = i + nums[i];
        }

        int jump = 0;
        int maxIndex = index[0];

        while (jump < index.length && jump <= maxIndex){
            if (maxIndex < index[jump]){
                maxIndex = index[jump];
            }
            jump++;

            if (jump == index.length){
                return true;
            }


        }

        return false;
    }



    public int canJump2(int[] nums){

        if (nums.length < 2){
            return 0;
        }

        int currentMaxIndex = nums[0];
        int preMaxIndex = nums[0];

        int jumpMin = 1;

        for (int i = 1; i < nums.length; i++){
            if (i > currentMaxIndex){
                jumpMin++;
                currentMaxIndex = preMaxIndex;
            }

            if (preMaxIndex < nums[i] + i){
                preMaxIndex = nums[i] + i;
            }
        }

        return jumpMin;



    }

















}
