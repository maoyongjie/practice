package com.practice.datastructure.dynamicplan;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @author MaoYongjie
 * @date 2022/8/18 16:59
 * @Description:
 */
//给你一个字符串 s，找到 s 中最长的回文子串。
//
//
//
// 示例 1：
//
//
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
//
//
// 示例 2：
//
//
//输入：s = "cbbd"
//输出："bb"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 1000
// s 仅由数字和英文字母组成
//
// Related Topics 字符串 动态规划
// 👍 5590 👎 0
public class Solution5 {
    public static void main(String[] args) {
        System.out.println((new Solution5()).longestPalindrome("baabv"));
    }

    public String longestPalindrome(String s) {
        int max = 0;
        String res = "";
        char[] chars = s.toCharArray();
        int size = s.length();

        ArrayDeque<Boolean> deque = new ArrayDeque<>();

        for (int i = size - 1; i >= 0; i--) {
            for (int j = i; j < size; j++) {
                boolean b;
                if (i == j) {
                    b = true;
                } else if (j - 1 == i) {
                    b = chars[i] == chars[j];
                } else {
                    b = deque.poll() && chars[i] == chars[j];
                }

                if (b) {
                    res = j - i >= max ? s.substring(i, j + 1) : res;
                    max = j - i > max ? j - i : max;
                }
                if (j < size-1 ) {
                    deque.offer(b);
                }

            }

        }
        return res;

    }
}
