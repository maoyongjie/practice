package com.practice.datastructure.dynamicplan;

import java.util.List;

/**
 * @author MaoYongjie
 * @date 2022/9/1 15:04
 * @Description:
 */
//给定一个三角形 triangle ，找出自顶向下的最小路径和。
//
// 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果
//正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
//
//
//
// 示例 1：
//
//
//输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
//输出：11
//解释：如下面简图所示：
//   2
//  3 4
// 6 5 7
//4 1 8 3
//自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
//
//
// 示例 2：
//
//
//输入：triangle = [[-10]]
//输出：-10
//
//
//
//
// 提示：
//
//
// 1 <= triangle.length <= 200
// triangle[0].length == 1
// triangle[i].length == triangle[i - 1].length + 1
// -104 <= triangle[i][j] <= 104
//
//
//
//
// 进阶：
//
//
// 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？
//
// Related Topics 数组 动态规划
// 👍 1096 👎 0

/**
 *
 */
public class Solution120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        int res = 105*m;
        int[][] arr = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= i; j++) {
                if (i == 0 && j == 0) {
                    arr[0][0] = triangle.get(0).get(0);
                } else {
                    if (j > 0 && j < i) {
                        arr[i][j] = triangle.get(i).get(j) + Math.min(arr[i - 1][j], arr[i - 1][j - 1]);
                    } else if (j == 0) {
                        arr[i][j] = triangle.get(i).get(j) + arr[i - 1][j];
                    } else {
                        arr[i][j] = triangle.get(i).get(j) + arr[i - 1][j - 1];
                    }
                }

                if (i == m - 1) {
                    res = Math.min(res,arr[i][j]);
                }
            }
        }
        return res;
    }


    public int minimumTotal2(List<List<Integer>> triangle) {
        int m = triangle.size();
        int res = 105*m;
        int pre = triangle.get(0).get(0);
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= i; j++) {

                if (i == 0 && j == 0) {
                    arr[0] = triangle.get(0).get(0);
                } else {

                    if (j > 0 && j < i) {
                        int temp = arr[j];
                        arr[j] = triangle.get(i).get(j) + Math.min(arr[j], pre);
                        pre = temp;
                    } else if (j == 0) {
                        pre = arr[j];
                        arr[j] = triangle.get(i).get(j) + pre;
                    } else {
                        arr[j] = triangle.get(i).get(j) + pre;
                    }
                }

                if (i == m - 1) {
                    res = Math.min(res,arr[j]);
                }
            }
        }
        return res;
    }
}
