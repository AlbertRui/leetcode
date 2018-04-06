package me.leetcode.cn.fail.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AlbertRui
 * @date 2018-04-06 10:08
 */
public class Solutions {

    /**
     * 第二题
     * 给定一个字符串，找出不含有重复字符的 最长子串 的长度。
     * <p>
     * 示例：
     * <p>
     * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
     * <p>
     * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
     * <p>
     * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列 而不是子串。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int count = 0;
        int max = 0;
        int flag = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!s.substring(flag, i).contains(String.valueOf(s.charAt(i)))) {
                count++;
                if (count == s.length())
                    return count;
            } else if (count > max) {
                max = count;
                count = 0;
                flag = i;
            }
        }
        return max;
    }

    /**
     * 5. 最长回文子串
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 长度最长为1000。
     * 示例:
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba"也是有效答案
     * 示例:
     * 输入: "cbbd"
     * 输出: "bb"
     *
     * @param s
     */
    public String longestPalindrome(String s) {
        String maxlength = "";
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    map.put(i, j);
                    break;
                }
            }
        }
        for (Map.Entry<Integer ,Integer> entry : map.entrySet()) {
            int distance = entry.getValue() - entry.getKey();
            if (distance > maxlength.length())
                maxlength = s.substring(entry.getKey(), entry.getValue() + 1);
        }
        return maxlength;
    }
}
