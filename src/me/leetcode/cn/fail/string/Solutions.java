package me.leetcode.cn.fail.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    /**
     * 6. Z字形转换
     * 将字符串 "PAYPALISHIRING" 以Z字形排列成给定的行数：（下面这样的形状）
     * <p>
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * 之后按逐行顺序依次排列："PAHNAPLSIIGYIR"
     * 实现一个将字符串进行指定行数的转换的函数:
     * string convert(string text, int nRows);
     * convert("PAYPALISHIRING", 3) 应当返回 "PAHNAPLSIIGYIR" 。
     */
    public static String convert(String s, int numRows) {
        int count = 0;
        int j = 0;
        char[] result = new char[s.length()];
        Set<Integer> set = new HashSet<>();
        while (true) {
            int k = (count++ * 2 * (numRows - 1)) % (s.length() + 1);
            System.out.println(k + " " + count * 2 * (numRows - 1));
            if (k < s.length()) {
                if (set.contains(k)) {
                    break;
                } else {
                    set.add(k);
                    result[j++] = s.charAt(k);
                }
            }
            System.out.println(String.valueOf(result));
        }
        return String.valueOf(result);
    }

}
