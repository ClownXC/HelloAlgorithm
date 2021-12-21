package com.algorithm.stack.leetcode;

/**
 * @author chen
 */
public class TrappingRainWater {

    public int trap(int[] height) {

        int n = height.length;
        int result = 0;
        if(n == 0 || n == 1) {
            return result;
        }

        int left = 0;
        int right = n - 1;
        int leftHeight = 0;
        int rightHeight = 0;
        while(left < right) {
            if(height[left] <= height[right]) {
                leftHeight = Math.max(leftHeight, height[left]);
                result += leftHeight - height[left];
                left++;
            }else {
                rightHeight = Math.max(rightHeight, height[right]);
                result += rightHeight - height[right];
                right--;
            }
        }
        return result;
    }






}
