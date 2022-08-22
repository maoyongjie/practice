package com.practice.datastructure.dynamicplan;

import java.util.Arrays;

/**
 * @author MaoYongjie
 * @date 2022/8/22 18:26
 * @Description:
 */

//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
//
// 问总共有多少条不同的路径？
//
//
//
// 示例 1：
//
//
//输入：m = 3, n = 7
//输出：28
//
// 示例 2：
//
//
//输入：m = 3, n = 2
//输出：3
//解释：
//从左上角开始，总共有 3 条路径可以到达右下角。
//1. 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右
//3. 向下 -> 向右 -> 向下
//
//
// 示例 3：
//
//
//输入：m = 7, n = 3
//输出：28
//
//
// 示例 4：
//
//
//输入：m = 3, n = 3
//输出：6
//
//
//
// 提示：
//
//
// 1 <= m, n <= 100
// 题目数据保证答案小于等于 2 * 109
//
// Related Topics 数学 动态规划 组合数学
// 👍 1523 👎 0
public class Solution62 {
    public static void main(String[] args) {
        System.out.println((new Solution62()).uniquePaths(7, 3));
    }

    public int uniquePaths(int m, int n) {
        int[] arr1 = new int[n];
        Arrays.fill(arr1, 1);
        int[] arr2 = new int[n];

        while (m>0){
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    arr2[0] = 1;
                } else {
                    arr2[j] = arr2[j - 1] + arr1[j];
                }
            }
            arr1 = arr2;
        }


        return 1;
    }

    public int uniquePaths2(int m, int n) {
        int[][] arr = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 || j == 1) {
                    arr[i][j] = 1;
                } else {
                    arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
                }
            }
        }
        return arr[m][n];
    }
}
