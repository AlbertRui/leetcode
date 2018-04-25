package me.leetcode.cn.hashtable;

import java.util.HashMap;
import java.util.Map;

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

}
