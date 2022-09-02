package com.practice.datastructure.dynamicplan;

import java.util.Arrays;

/**
 * @author MaoYongjie
 * @date 2022/9/2 13:09
 * @Description:
 */
//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
//
//
//
// 示例 1:
//
//
//输入：prices = [3,3,5,0,0,3,1,4]
//输出：6
//解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
//     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
//
// 示例 2：
//
//
//输入：prices = [1,2,3,4,5]
//输出：4
//解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
//
//
// 示例 3：
//
//
//输入：prices = [7,6,4,3,1]
//输出：0
//解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
//
// 示例 4：
//
//
//输入：prices = [1]
//输出：0
//
//
//
//
// 提示：
//
//
// 1 <= prices.length <= 105
// 0 <= prices[i] <= 105
//
// Related Topics 数组 动态规划
// 👍 1213 👎 0
public class Solution123 {
    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(Solution123.maxProfit(prices));
    }

    /**
     * 1、定义子问题fi 表示 0->i区间 i+1->n区间的最大值之和
     *
     * 2、定义子问题fi 表示第i天第一次卖出的最大收益
     * fi =
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            if (i == prices.length - 1) {
                res = Math.max(res, findMaxProfit(prices));
            } else {
                res = Math.max(res, findMaxProfit(Arrays.copyOf(prices, i)) + findMaxProfit(Arrays.copyOfRange(prices, i, prices.length)));
            }
        }
        return res;
    }


    public static int findMaxProfit(int[] prices) {
        int res = 0;
        int fi_1 = 0;
        int fi;
        for (int i = 1; i < prices.length; i++) {
            if (fi_1 <= 0) {
                fi = prices[i] - prices[i - 1];

            } else {
                fi = fi_1 + (prices[i] - prices[i - 1]);
            }
            res = Math.max(res, fi);
            fi_1 = fi;
        }
        return res;
    }
}
