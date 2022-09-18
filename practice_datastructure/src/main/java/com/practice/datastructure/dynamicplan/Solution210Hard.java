package com.practice.datastructure.dynamicplan;

/**
 * @author MaoYongjie
 * @date 2022/8/24 17:09
 * @Description:
 */
//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
//
//
// '.' 匹配任意单个字符
// '*' 匹配零个或多个前面的那一个元素
//
//
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
//
//
// 示例 1：
//
//
//输入：s = "aa", p = "a"
//输出：false
//解释："a" 无法匹配 "aa" 整个字符串。
//
//
// 示例 2:
//
//
//输入：s = "aa", p = "a*"
//输出：true
//解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
//
//
// 示例 3：
//
//
//输入：s = "ab", p = ".*"
//输出：true
//解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 20
// 1 <= p.length <= 30
// s 只包含从 a-z 的小写字母。
// p 只包含从 a-z 的小写字母，以及字符 . 和 *。
// 保证每次出现字符 * 时，前面都匹配到有效的字符
//
// Related Topics 递归 字符串 动态规划
// 👍 3169 👎 0
public class Solution210Hard {
    public boolean isMatch(String s, String p) {
//        char[] charS = s.toCharArray();
//        char[] charP = p.toCharArray();

        //定义P(i,j)为i结尾，j结尾的字符串是否可以匹配
        boolean[][] arr = new boolean[s.length() + 1][p.length() + 1];

//        while (i < s.length() || j < p.length()) {
//            //匹配
//            if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
//                //后一个字符为*
//                if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
//                    //匹配0次  aa a*aa || 匹配多次 aaaaa a*
//                    arr[i][j] = arr[i][j + 2] || arr[i + 1][j];
//                } else {
//                    //直接匹配
//                    arr[i++][j++] = true;
//                }
//
//            } //不匹配
//            else {
//                //abc c*abc 直接过
//                if(j < p.length() - 1 && p.charAt(j + 1) == '*'){
//                    arr[i][j] = arr[i][j+2];
//                }else {
//                    arr[i][j] = false;
//                }
//            }
//        }

        arr[0][0] = true;

        for (int j = 1; j <= p.length(); j++) {
            if (p.charAt(j - 1) == '*') {
                arr[0][j] = arr[0][j - 2];
            }
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                //匹配
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    arr[i][j] = arr[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    if(s.charAt(i-1) == p.charAt(j-2)||p.charAt(j-2)=='.'){
                        arr[i][j] = arr[i][j-2]||arr[i-1][j];
                    }else {
                        arr[i][j] =arr[i][j-2];
                    }
                }
            }
        }
        return arr[s.length()][p.length()];
    }
}
