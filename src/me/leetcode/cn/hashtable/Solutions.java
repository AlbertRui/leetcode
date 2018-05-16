package me.leetcode.cn.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author AlbertRui
 * @date 2018-04-24 23:30
 */
public class Solutions {

    /**
     * 166. 分数到小数
     * 给定两个整数，分别表示分数的分子和分母，返回字符串格式的小数。
     * 如果小数部分为循环小数，则将重复部分括在括号内。
     * 例如，
     * 给出 分子 = 1， 分母 = 2，返回 "0.5".
     * 给出 分子 = 2， 分母 = 1，返回 "2".
     * 给出 分子 = 2， 分母 = 3，返回 "0.(6)".
     */
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        StringBuilder res = new StringBuilder();
        res.append((numerator > 0) ^ (denominator > 0) ? "-" : "");
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        res.append(num / den);
        num %= den;
        if (num == 0) return res.toString();
        res.append(".");
        Map<Long, Integer> map = new HashMap<>();
        map.put(num, res.length());
        while (num != 0) {
            num *= 10;
            res.append(num / den);
            num %= den;
            if (map.containsKey(num)) {
                res.insert(map.get(num), "(");
                res.append(")");
                break;
            } else {
                map.put(num, res.length());
            }
        }
        return res.toString();
    }

    /**
     * 49. 字母异位词分组
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     * 示例:
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * 输出:
     * [["ate","eat","tea"],["nat","tan"],["bat"]]
     * 说明：所有输入均为小写字母。不考虑答案输出的顺序。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> ans = new HashMap<>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if (!ans.containsKey(key)) ans.put(key, new ArrayList<>());
            ans.get(key).add(s);
        }
        return new ArrayList<>(ans.values());
    }

    /**
     * 187. 重复的DNA序列
     * 所有 DNA 由一系列缩写为 A，C，G 和 T 的核苷酸组成，例如：“ACGAATTCCG”。
     * 在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
     * 编写一个函数来查找 DNA 分子中所有出现超多一次的10个字母长的序列（子串）。
     * 示例:
     * 输入: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
     * 输出: ["AAAAACCCCC", "CCCCCAAAAA"]
     */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        Map<Integer, Integer> sumMap = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum = ((sum << 3) | (s.charAt(i) & 7)) & 0x3FFFFFFF;
            if (i < 9) continue;
            Integer cnt = sumMap.get(sum);
            if (cnt != null && cnt == 1) {
                result.add(s.substring(i - 9, i + 1));
            }
            sumMap.put(sum, cnt != null ? cnt + 1 : 1);
        }
        return result;
    }

    public List<String> findRepeatedDnaSequences2(String s) {
        List<String> res = new ArrayList<String>();
        if (s == null || s.length() < 11) return res;
        int hash = 0;

        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);

        Set<Integer> set = new HashSet<>();
        Set<Integer> unique = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i < 9) {
                hash = (hash << 2) + map.get(c);
            } else {
                hash = (hash << 2) + map.get(c);
                hash &= (1 << 20) - 1;
                if (set.contains(hash) && !unique.contains(hash)) {
                    res.add(s.substring(i - 9, i + 1));
                    unique.add(hash);
                } else {
                    set.add(hash);
                }
            }
        }
        return res;
    }

    public List<String> findRepeatedDnaSequences3(String s) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> hash = new HashMap<>();
        for (int i = 0; i < s.length() - 9; i++) {
            String str = s.substring(i, i + 10);
            Integer count = hash.get(str);
            if (count != null && count == 1) res.add(str);
            hash.put(str, count == null ? 1 : count + 1);
        }
        return res;
    }

}
