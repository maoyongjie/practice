package com.practice.datastructure.dynamicplan;

/**
 * @author MaoYongjie
 * @date 2022/8/23 10:49
 * @Description:
 */
//给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//
// 子数组 是数组中的一个连续部分。
//
//
//
// 示例 1：
//
//
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
//
//
// 示例 2：
//
//
//输入：nums = [1]
//输出：1
//
//
// 示例 3：
//
//
//输入：nums = [5,4,-1,7,8]
//输出：23
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 105
// -104 <= nums[i] <= 104
//
//
//
//
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
// Related Topics 数组 分治 动态规划

/**
 * S1 子问题定义为：dp(n) 以nums[n]为结尾的连续子数组的最大和
 * <p>
 * S2 分治法 ???
 * S3 贪心算法
 */
public class Solution53 {
    public static void main(String[] args) {
        int[] nums = {5, 4, -1, 7, 8};
        System.out.println((new Solution53()).maxSubArray2(nums));
    }

    public int maxSubArray(int[] nums) {
        int[] arr = new int[nums.length];
        arr[0] = nums[0];
        int res = arr[0];
        for (int i = 1; i < nums.length; i++) {
            arr[i] = Math.max(nums[i], arr[i - 1] + nums[i]);
            res = Math.max(res, arr[i]);
        }
        return res;
    }

    public int maxSubArray2(int[] nums) {
        int res = nums[0];
        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (pre < 0) {
                pre = nums[i];
            } else {
                pre += nums[i];
            }

            res = Math.max(res, pre);
        }
        return res;
    }
}
