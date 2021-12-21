package com.algorithm.dp;


/**
 * 如果m和数组 nums 的个数相等，那么每个数组都是一个子数组，所以返回 nums 中最大的数字即可，
 * 如果m为1，那么整个 nums 数组就是一个子数组，返回 nums 所有数字之和，
 * 所以对于其他有效的m值，返回的值必定在上面两个值之间，
 * <p>
 * <p>
 * nums = [1, 2, 3, 4, 5], m = 3
 * 将 left 设为数组中的最大值5，right 设为数字之和 15，然后算出中间数为 10，
 * 接下来要做的是找出和最大且小于等于 10 的子数组的个数，[1, 2, 3, 4], [5]
 * 可以看到无法分为3组，说明 mid 偏大，所以让 right=mid，
 * 然后再次进行二分查找，算出 mid=7，
 * 再次找出和最大且小于等于7的子数组的个数，[1,2,3], [4], [5]，
 * 成功的找出了三组，说明 mid 还可以进一步降低，
 * 让 right=mid，再次进行二分查找，算出 mid=6，
 * 再次找出和最大且小于等于6的子数组的个数，[1,2,3], [4], [5]，
 * 成功的找出了三组，尝试着继续降低 mid，让 right=mid，
 * 再次进行二分查找，算出 mid=5，
 * 再次找出和最大且小于等于5的子数组的个数，[1,2], [3], [4], [5]，
 * 发现有4组，此时的 mid 太小了，
 * 应该增大 mid，让 left=mid+1，此时 left=6，right=5，
 * 循环退出了，返回 right 即可，
 */
public class SpiltArrayLargestSum_410 {


    /**
     * @param nums
     * @param n
     * @return
     */
    public static int splitArr(int[] nums, int n) {

        int left = 0;
        int right = 0;
        int ans = 0;


        for (int i = 0; i < nums.length; i++) {
            right += nums[i];
        }
        while (left <= right) {

            int mid = (left + right) / 2;

            if (guess(nums, mid, n)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }


    /**
     *
     * @param nums
     * @param mid
     * @param count
     * @return
     */
    private static boolean guess(int[] nums, int mid, int count) {


        int sum = 0;

        for (int i = 0; i < nums.length; i++) {

            if (sum + nums[i] > mid) {

                count--;
                sum = nums[i];
                if (nums[i] > mid) {
                    return false;
                }
            } else {
                sum += nums[i];
            }
        }
        return count == 1;
    }

    public static void main(String[] args) {

        int[] nums = {7,2,5,10,8};
        System.out.println(SpiltArrayLargestSum_410.splitArr(nums, 2));
    }


}
