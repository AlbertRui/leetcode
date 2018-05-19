package me.leetcode.cn.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author AlbertRui
 * @date 2018-04-06 10:08
 */
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
     * <p>
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

    /**
     * 14. 最长公共前缀
     * 编写一个函数来查找字符串数组中最长的公共前缀字符串。
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs[0].length(); i++) {
            for (String str : strs) {
                if (i == str.length() || str.charAt(i) != strs[0].charAt(i)) {
                    return str.substring(0, i);
                }
            }
        }
        return strs[0];
    }

    /**
     * 17. 电话号码的字母组合
     * 给定一个数字字符串，返回数字所有可能表示的字母组合。
     * 数字到字母的映射（和电话号码一样）。
     * 输入：数字字符串 "23"
     * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     * 说明:尽管上面的答案是按字典序排列的，但是你的答案可以是任何顺序。
     * <p>
     * 使用队列实现
     */
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return new ArrayList<>();
        LinkedList<String> ans = new LinkedList<>();
        String[] mapping = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for (int i = 0; i < digits.length(); i++) {
            int x = Character.getNumericValue(digits.charAt(i));
            while (ans.peek().length() == i) {
                String t = ans.remove();
                for (char s : mapping[x].toCharArray())
                    ans.add(t + s);
            }
        }
        return ans;
    }

    /**
     * 22. 括号生成
     * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
     * 例如，给出 n = 3，生成结果为：
     * [
     * "((()))",
     * "(()())",
     * "(())()",
     * "()(())",
     * "()()()"
     * ]
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        if (n == 0)
            ans.add("");
        else
            for (int c = 0; c < n; ++c)
                for (String left : generateParenthesis(c))
                    for (String right : generateParenthesis(n - 1 - c))
                        ans.add("(" + left + ")" + right);
        return ans;
    }

    /**
     * 564. 寻找最近的回文数
     * 给定一个整数 n ，你需要找到与它最近的回文数（不包括自身）。
     * “最近的”定义为两个整数差的绝对值最小。
     * 示例 1:
     * 输入: "123"
     * 输出: "121"
     * 注意:n 是由字符串表示的正整数，其长度不超过18。
     * 如果有多个结果，返回最小的那个。
     */
    public String nearestPalindromic(String n) {
        int order = (int) Math.pow(10, n.length() / 2);
        Long ans = Long.valueOf(n);
        Long noChange = mirror(ans);
        Long larger = mirror((ans / order) * order + order + 1);
        Long smaller = mirror((ans / order) * order - 1);
        if (noChange > ans) {
            larger = Math.min(noChange, larger);
        } else if (noChange < ans) {
            smaller = Math.max(noChange, smaller);
        }
        return String.valueOf(ans - smaller <= larger - ans ? smaller : larger);
    }

    private Long mirror(Long ans) {
        char[] a = String.valueOf(ans).toCharArray();
        int i = 0;
        int j = a.length - 1;
        while (i < j) {
            a[j--] = a[i++];
        }
        return Long.valueOf(new String(a));
    }

    /**
     * 443. 压缩字符串
     * 给定一组字符，使用原地算法将其压缩。
     * 压缩后的长度必须始终小于或等于原数组长度。
     * 数组的每个元素应该是长度为1 的字符（不是 int 整数类型）。
     * 在完成原地修改输入数组后，返回数组的新长度。
     * 进阶：
     * 你能否仅使用O(1) 空间解决问题？
     * 示例 1：
     * 输入：["a","a","b","b","c","c","c"]
     * 输出：返回6，输入数组的前6个字符应该是：["a","2","b","2","c","3"]
     * 说明："aa"被"a2"替代。"bb"被"b2"替代。"ccc"被"c3"替代。
     * 示例 2：
     * 输入：["a"]
     * 输出：返回1，输入数组的前1个字符应该是：["a"]
     * 说明：没有任何字符串被替代。
     * 示例 3：
     * 输入：["a","b","b","b","b","b","b","b","b","b","b","b","b"]
     * 输出：返回4，输入数组的前4个字符应该是：["a","b","1","2"]。
     * 说明：由于字符"a"不重复，所以不会被压缩。"bbbbbbbbbbbb"被“b12”替代。
     * 注意每个数字在数组中都有它自己的位置。
     * 注意：所有字符都有一个ASCII值在[35, 126]区间内。
     * 1 <= len(chars) <= 1000。
     */
    public int compress(char[] chars) {
        int anchor = 0, write = 0;
        for (int read = 0; read < chars.length; read++) {
            if (read + 1 == chars.length || chars[read + 1] != chars[read]) {
                chars[write++] = chars[anchor];
                if (read > anchor)
                    for (char c : ("" + (read - anchor + 1)).toCharArray())
                        chars[write++] = c;
                anchor = read + 1;
            }
        }
        return write;
    }

    /**
     * 345. 反转字符串中的元音字母
     * 题目描述提示帮助提交记录社区讨论阅读解答
     * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
     * 示例 1：
     * 给定 s = "hello", 返回 "holle".
     * 示例 2：
     * 给定 s = "leetcode", 返回 "leotcede".
     * 注意:
     * 元音字母不包括 "y".
     * 超时
     */
    public String reverseVowels(String s) {
        if (s == null || s.equals("")) return s;
        char[] chars = s.toCharArray();
        Set<Character> characters = new HashSet<>();
        characters.add('a');
        characters.add('o');
        characters.add('e');
        characters.add('i');
        characters.add('u');
        int head = 0, tail = chars.length - 1;
        while (head != tail) {
            if (characters.contains(chars[head]) && characters.contains(chars[tail])) {
                char temp = chars[head];
                chars[tail] = chars[tail];
                chars[tail] = temp;
            } else if (characters.contains(chars[head])) {
                tail--;
            } else if (characters.contains(chars[tail])) {
                head++;
            } else {
                head++;
                tail--;
            }
        }
        return Arrays.toString(chars);
    }

    /**
     * 正确解法
     */
    public String reverseVowels2(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            if ("AEIOUaeiou".indexOf(chars[left]) < 0) left++;
            else if ("AEIOUaeiou".indexOf(chars[right]) < 0) right--;
            else {
                char tmp = chars[left];
                chars[left] = chars[right];
                chars[right] = tmp;
                left++;
                right--;
            }
        }
        return new String(chars);
    }

    /**
     * 686. 重复叠加字符串匹配
     * 给定两个字符串 A 和 B, 寻找重复叠加字符串A的最小次数，使得字符串B成为叠加后的字符串A的子串，如果不存在则返回 -1。
     * 举个例子，A = "abcd"，B = "cdabcdab"。
     * 答案为 3， 因为 A 重复叠加三遍后为 “abcdabcdabcd”，此时 B 是其子串；A 重复叠加两遍后为"abcdabcd"，B 并不是其子串。
     * 注意:
     * A 与 B 字符串的长度在1和10000区间范围内。
     */
    public int repeatedStringMatch(String A, String B) {
        int q = 1;
        StringBuilder S = new StringBuilder(A);
        for (; S.length() < B.length(); q++) S.append(A);
        if (S.indexOf(B) >= 0) return q;
        if (S.append(A).indexOf(B) >= 0) return q+1;
        return -1;
    }

    public static void main(String[] args) {
//        lengthOfLongestSubstring("abcabb");
//        System.out.println("================set=========================");
//        lengthOfLongestSubstring2("bbbbbbbbbbbbbb");
//        convert("PAYPALISHIRING", 3);
        Solutions solutions = new Solutions();
        String val = solutions.nearestPalindromic("1999");
        System.out.println(val);
    }

}
