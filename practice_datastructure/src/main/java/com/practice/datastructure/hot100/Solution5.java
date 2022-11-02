package com.practice.datastructure.hot100;

public class Solution5 {
    public static void main(String[] args) {
        System.out.println((new Solution5()).longestPalindrome("baaaac"));
    }

    public String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int start = 0;
        int end = 0;
        int len = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (i == j) {
                    dp[i][j] = true;
                } else {
                    if (s.charAt(i) == s.charAt(j)) {
                        if (j - i <= 2) {
                            dp[i][j] = true;
                        } else {
                            dp[i][j] = dp[i + 1][j - 1];
                        }
                    }
                }
                if (dp[i][j] && j - i > len) {

                    len = j - i;
                    start = i;
                    end = j;
                }

            }
        }
        return s.substring(start, end + 1);
    }
}
