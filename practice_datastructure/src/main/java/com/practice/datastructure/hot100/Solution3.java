package com.practice.datastructure.hot100;

import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.Map;

public class Solution3 {
    public static void main(String[] args) {
        System.out.println((new Solution3()).lengthOfLongestSubstring("a"));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.equals("")) {
            return 0;
        }
        int l = 0;
        int r = 0;
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (r < s.length()) {

            map.put(s.charAt(r), 1);
            r++;
            if (r < s.length() && map.containsKey(s.charAt(r))) {
                res = Math.max(res, r - l);
                while (s.charAt(l) != s.charAt(r)) {
                    map.remove(s.charAt(l));
                    l++;
                }
                map.remove(s.charAt(r));
                l++;
            } else if (r == s.length()) {
                res = Math.max(res, r - l);
            }
        }
        return res;
    }
}
