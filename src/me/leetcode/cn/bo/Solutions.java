package me.leetcode.cn.bo;

/**
 * @author AlbertRui
 * @date 2018-04-28 20:34
 */
public class Solutions {

    /**
     * 371. 两整数之和
     * 不使用运算符 + 和-，计算两整数a 、b之和。
     * 示例：若 a = 1 ，b = 2，返回 3。
     */
    public int getSum(int a, int b) {
        if (b == 0) return a;
        int sum = a ^ b;
        int carry = (a & b) << 1;
        return getSum(sum, carry);
    }

    public int getSum2(int a, int b) {
        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }
        return a;
    }

}
