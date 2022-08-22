package com.practice.datastructure.dynamicplan;

import java.util.List;

/**
 * @author MaoYongjie
 * @date 2022/8/22 15:33
 * @Description:
 */
//给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。
//
// 判断你是否能够到达最后一个下标。
//
//
//
// 示例 1：
//
//
//输入：nums = [2,3,1,1,4]
//输出：true
//解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
//
//
// 示例 2：
//
//
//输入：nums = [3,2,1,0,4]
//输出：false
//解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 3 * 104
// 0 <= nums[i] <= 105
//
// Related Topics 贪心 数组 动态规划
// 👍 1967 👎 0

/**
 * 使用贪心算法何解？
 */
public class Solution55 {
    public static void main(String[] args) {
        int[] nums = {3,2,2,0,4};
        System.out.println((new Solution55()).canJump(nums));
    }
    public boolean canJump(int[] nums) {
        boolean[] arr = new boolean[nums.length];
        arr[0] = true;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if(arr[j]&&(nums[j]>=i-j)){
                    arr[i] = true;
                    break;
                }
            }
        }
        return arr[nums.length-1];
    }
}
