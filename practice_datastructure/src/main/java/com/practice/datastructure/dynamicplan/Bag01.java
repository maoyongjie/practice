package com.practice.datastructure.dynamicplan;

public class Bag01 {

    public static void main(String[] args) {
        int[] wt = {2,1,3};
        int[] pri = {4,2,3};
        System.out.println(Bag01.knapsack(3, 4, wt, pri));
    }

    public static int knapsack(int n, int w, int[] wt, int[] pri) {
        int[][] dp = new int[n + 1][w+ 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                if(j - wt[i - 1]<0){
                    dp[i][j] = dp[i - 1][j];
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - wt[i - 1]] + pri[i - 1]);
                }

            }
        }
        return dp[n][w];
    }

}
