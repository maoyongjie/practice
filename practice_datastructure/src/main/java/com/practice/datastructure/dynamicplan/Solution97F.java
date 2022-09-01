package com.practice.datastructure.dynamicplan;

/**
 * @author MaoYongjie
 * @date 2022/9/1 10:36
 * @Description:
 */
//给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
//
// 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
//
//
// s = s1 + s2 + ... + sn
// t = t1 + t2 + ... + tm
// |n - m| <= 1
// 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
//
//
// 注意：a + b 意味着字符串 a 和 b 连接。
//
//
//
// 示例 1：
//
//
//输入：s1 = "aabc ", s2 = "dbbc", s3 = "aadbbcbc "
//输出：true
//
//
// 示例 2：
//
//
//输入：s1 = "aab cc", s2 = "dbb ca", s3 = "aadbbbaccc"
//输出：false
//
//
// 示例 3：
//
//
//输入：s1 = "", s2 = "", s3 = ""
//输出：true
//
//
//
//
// 提示：
//
//
// 0 <= s1.length, s2.length <= 100
// 0 <= s3.length <= 200
// s1、s2、和 s3 都由小写英文字母组成
//
//
//
//
// 进阶：您能否仅使用 O(s2.length) 额外的内存空间来解决它?
// Related Topics 字符串 动态规划
// 👍 757 👎 0

/**
 * 定义P(i,j) 为i结尾 和 j结尾的子串是否可以构成s3.substr(0,i+j)
 * P(i-1,j-1)->P(i,j)?
 */
public class Solution97F {
    public static void main(String[] args) {
        System.out.println(Solution97F.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    }
    public static boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        if ((m + n) != s3.length()) {
            return false;
        }
        boolean[][] arr = new boolean[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) {
                    arr[0][0] = true;
                } else if (i == 0) {
                    arr[0][j] = arr[0][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    arr[i][0] = arr[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                }else {
                    arr[i][j] = (arr[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1))||(arr[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return arr[m][n];
    }
}
