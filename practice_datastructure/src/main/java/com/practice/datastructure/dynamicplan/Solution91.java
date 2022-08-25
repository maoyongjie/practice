package com.practice.datastructure.dynamicplan;

/**
 * @author MaoYongjie
 * @date 2022/8/25 17:16
 * @Description:
 */
//一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
//
//
//'A' -> "1"
//'B' -> "2"
//...
//'Z' -> "26"
//
// 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
//
//
// "AAJF" ，将消息分组为 (1 1 10 6)
// "KJF" ，将消息分组为 (11 10 6)
//
//
// 注意，消息不能分组为 (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
//
// 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
//
// 题目数据保证答案肯定是一个 32 位 的整数。
//
//
//
// 示例 1：
//
//
//输入：s = "12"
//输出：2
//解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
//
//
// 示例 2：
//
//
//输入：s = "226"
//输出：3
//解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
//
//
// 示例 3：
//
//
//输入：s = "0"
//输出：0
//解释：没有字符映射到以 0 开头的数字。
//含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
//由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 100
// s 只包含数字，并且可能包含前导零。
//
// Related Topics 字符串 动态规划
// 👍 1233 👎 0
public class Solution91 {
    public static void main(String[] args) {
//        System.out.println(getInt('3', '5'));
        System.out.println(numDecodings("10"));
    }


    public static int numDecodings(String s) {
        int size = s.length();
        char[] chars = s.toCharArray();
        int[] arr = new int[size];
        if (chars[0] == '0') {
            return 0;
        } else {
            arr[0] = 1;
        }
        if (size >= 2) {
            if (chars[1] == '0') {
                arr[1] = chars[0] - 48 > 2 ? 1 : 0;
            } else {
                arr[1] = getInt(chars[0], chars[1]) > 26 || chars[1] - 48 > '2' ? 1 : 2;
            }
        }
        for (int i = 2; i < size; i++) {
            if (chars[i] == '0') {
                if (chars[i - 1] == '0') {
                    return 0;
                }
                arr[i] = Integer.valueOf(chars[i - 1] + "") > 2 ? 0 : arr[i - 2];
            } else {
                if (chars[i - 1] == '0') {
                    arr[i] = arr[i - 1];
                } else {
                    arr[i] = getInt(chars[i - 1], chars[i]) > 26 ? arr[i - 1] : arr[i - 1] + arr[i - 2];
                }
            }
            if (arr[i] == 0) {
                return 0;
            }
        }
        return arr[size - 1];
    }

    private static int getInt(char a, char b) {
        return Integer.valueOf(a + "" + b);
    }
}
