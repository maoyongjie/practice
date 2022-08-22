package com.practice.datastructure.dynamicplan;

/**
 * @author MaoYongjie
 * @date 2022/8/22 16:03
 * @Description:
 */
//给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。
//
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
//
// 假设你总是可以到达数组的最后一个位置。
//
//
//
// 示例 1:
//
//
//输入: nums = [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
//
//
// 示例 2:
//
//
//输入: nums = [2,3,0,1,4]
//输出: 2
//
//
//
//
// 提示:
//
//
// 1 <= nums.length <= 104
// 0 <= nums[i] <= 1000
//
// Related Topics 贪心 数组 动态规划
// 👍 1762 👎 0
public class Solution45 {
    public static void main(String[] args) {
        int[] nums = {2,3,1};
        System.out.println((new Solution45()).jump2(nums));
    }

    public int jump(int[] nums) {
        int[] arr = new int[nums.length];
        arr[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            int min = nums.length;
            for (int j = 0; j < i; j++) {
                min = nums[j] >= i - j ? Math.min(min, arr[j]) : min;
            }
            arr[i] = min + 1;
        }
        return arr[nums.length - 1];
    }

    public int jump2(int[] nums) {
        int res = 0;
        int pos = 0;
        while (pos < nums.length - 1) {
            res++;
            int max = 0;
            int start = pos + 1;
            int end = pos + nums[pos];
            int diff = 0;
            for (int j = start; j <= end; j++) {
                diff++;
                if(end >=nums.length-1){
                    pos=nums.length - 1;
                    break;
                }
                if (nums[j]+ diff>= max) {
                    max = nums[j]+ diff;
                    pos = j;
                }
            }
        }
        return res;
    }
}
