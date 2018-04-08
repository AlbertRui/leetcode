package me.leetcode.cn.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author AlbertRui
 * @date 2018-04-06 10:08
 */
@SuppressWarnings("ALL")
public class Solutions {

    /**
     * 第三题
     * 给定一个字符串，找出不含有重复字符的 最长子串 的长度。
     * 示例：
     * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
     * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
     * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列 而不是子串。
     * 用一个HashMap来存储字符串。其中key为字符，value为该字符在字符串中的位置。
     * 使用两个指针i，j来指示最长子串的位置。刚开始i，j都为0，指向第一个字符。
     * 然后i开始向右遍历。若遍历到的字符已在HashMap中，则更新它的value为现在i的位置。
     * 并且将j指向该字符的下一个位置（j只能往右移，或者不移，不能左移）。
     * 若未在HashMap中，则将该字符以及它的位置放入HashMap中。最大的（i-j+1）即为最长子串的长度。
     * 作者：Jeffbond
     * 链接：https://www.jianshu.com/p/c43f2c9eaf16
     * 來源：简书
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - j + 1);
            System.out.println(i + " " + j + " " + map.toString() + " " + max);
        }
        return max;
    }

    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
            System.out.println(i + " " + j + " " + set.toString() + " " + ans);
        }
        return ans;
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
     * @param args
     */
    public String longestPalindrome(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
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
        //字符串为空则直接返回
        if (s == null || s.isEmpty()) {
            return s;
        }
        int length = s.length();
        //字符串长度不大于行数或者行数为1时，直接返回
        if (length <= numRows || numRows == 1) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        //首行和尾行相邻两个元素之间的距离
        int step = 2 * numRows - 2;
        //统计字符已添加个数
        int count = 0;
        //逐行添加，行数从零开始计算
        for (int i = 0; i < numRows; i++) {
            //其他行除了像首末两行一样有间隔距离2*(numRows - 1)的元素，
            // 在它们之间还有一个元素，该元素到该行下一个元素的距离为2*i，i为所在行数，
            // 所以可知固定间隔位置到下一个元素的距离为2*(numRows -1) - 2*i，即step - interval;
            int interval = step - 2 * i;
            for (int j = i; j < s.length(); j += step) {
                sb.append(s.charAt(j));
                count++;
                //interval > 0 排除末行，interval < step 排除首行
                if (interval > 0 && interval < step && j + interval < length && count < length) {
                    sb.append(s.charAt(j + interval));
                    count++;
                }
            }
        }
        return sb.toString();
    }

    /**
     * 10. 正则表达式匹配
     * 实现支持 '.' 和 '*' 的正则表达式匹配。
     * <p>
     * '.' 匹配任意单个字符。
     * '*' 匹配零个或多个前面的元素。
     * <p>
     * 匹配应该覆盖整个输入字符串（不是部分字符串）。
     * <p>
     * 函数:
     * bool isMatch(const char *s, const char *p)
     * <p>
     * 例子:
     * isMatch("aa","a") → false
     * isMatch("aa","aa") → true
     * isMatch("aaa","aa") → false
     * isMatch("aa", "a*") → true
     * isMatch("aa", ".*") → true
     * isMatch("ab", ".*") → true
     * isMatch("aab", "c*a*b") → true
     *
     * 参考：https://www.jianshu.com/p/85f3e5a9fcda
     */
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }

        //第二个字符不是*
        if (p.length() == 1 || p.charAt(1) != '*') {
            if (s.isEmpty() || (p.charAt(0) != '.' && p.charAt(0) != s.charAt(0))) {
                return false;
            } else {
                return isMatch(s.substring(1), p.substring(1));
            }
        }

        //第二个字符是*或者长度大于1
        while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
            if (isMatch(s, p.substring(2))) {
                return true;
            }
            s = s.substring(1);
        }

        return isMatch(s, p.substring(2));
    }
    public static void main(String[] args) {
//        lengthOfLongestSubstring("abcabb");
//        System.out.println("================set=========================");
//        lengthOfLongestSubstring2("bbbbbbbbbbbbbb");
        convert("PAYPALISHIRING", 3);
    }

}
