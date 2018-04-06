package me.leetcode.cn.string;

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
        int count = 1;
        int max = 0;
        int flag = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!s.substring(flag, i).contains(String.valueOf(s.charAt(i))))
                count++;
            else if (count > max) {
                max = count;
                count = 0;
                flag = i;
            }
        }
        return max;
    }
}
