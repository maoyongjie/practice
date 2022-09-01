package com.practice.datastructure.dynamicplan;

/**
 * @author MaoYongjie
 * @date 2022/9/1 13:12
 * @Description:
 */
//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
//
//
//
// 示例 1：
//
//
//输入：n = 3
//输出：5
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= n <= 19
//
// Related Topics 树 二叉搜索树 数学 动态规划 二叉树
// 👍 1838 👎 0

/**
 * 定义f(i)为 以i结束的数量  f(i+1) = f(i) 全部挂在左边  +  f(i) 直挂在n节点 右子树
 */
public class Solution96 {

    public int numTrees(int n) {
        int[] arr = new int[n + 1];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < n; i++) {
            for (int j = 1; j <= i; j++) {
                arr[i] += arr[j - 1] * arr[i - j];
            }
        }
        return arr[n];
    }
}
