package com.practice.datastructure.dynamicplan;

/**
 * @author MaoYongjie
 * @date 2022/8/25 9:59
 * @Description:
 */
//求两个字符串的最长公共子串长度
public class Solution0 {
    public static void main(String[] args) {
        System.out.println(findMaxSubStr("abacggndnncff", "kabackggnnncdd"));
    }
    public static int findMaxSubStr(String a, String b) {
        char[] charA = a.toCharArray();
        char[] charB = b.toCharArray();
        int m = a.length();
        int n = b.length();
        int res = 0;
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    arr[i][j] = charA[i] == charB[j] ? 1 : 0;
                } else {
                    arr[i][j] = charA[i] == charB[j] ? 1 + arr[i - 1][j - 1] : 0;
                }
                res = Math.max(res,arr[i][j]);
            }
        }
        return res;
    }
}
