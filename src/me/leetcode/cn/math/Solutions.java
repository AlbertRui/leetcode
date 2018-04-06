package me.leetcode.cn.math;

/**
 * @author AlbertRui
 * @date 2018-04-06 22:30
 */
public class Solutions {

    /**
     * 7. 颠倒整数
     * 给定一个范围为 32 位 int 的整数，将其颠倒。
     * 例 1:
     * 输入: 123
     * 输出:  321
     * 例 2:
     * 输入: -123
     * 输出: -321
     * 例 3:
     * 输入: 120
     * 输出: 21
     * 注意:假设我们的环境只能处理 32 位 int 范围内的整数。根据这个假设，如果颠倒后的结果超过这个范围，则返回 0。
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int digit;
        long rnum = 0;
        int n = x;

        while (n != 0) {
            digit = n % 10;
            rnum = rnum * 10 + digit;
            n = n / 10;

            // Checking the overflow
            if ((rnum > Integer.MAX_VALUE) || (rnum < Integer.MIN_VALUE))
                return 0;
        }
        return (int) rnum;
    }

    /**
     * 8. 字符串转整数（atoi）
     * 实现 atoi，将字符串转为整数。
     * 提示：仔细考虑所有输入情况。如果你想挑战自己，请不要看下面并自己考虑所有可能的输入情况。
     * 说明：这题解释的比较模糊（即没有指定输入格式）。你得事先汇集所有的输入情况。
     * atoi的要求：
     * 这个函数需要丢弃之前的空白字符，直到找到第一个非空白字符。之后从这个字符开始，选取一个可选的正号或负号后面跟随尽可能多的数字，并将其解释为数字的值。
     * 字符串可以在形成整数的字符后包括多余的字符，将这些字符忽略，这些字符对于函数的行为没有影响。
     * 如果字符串中的第一个非空白的字符不是有效的整数，或者没有这样的序列存在，字符串为空或者只包含空白字符则不进行转换。
     * 如果不能执行有效的转换，则返回 0。如果正确的值超过的可表示的范围，则返回 INT_MAX（2147483647）或 INT_MIN（-2147483648）。
     */
    public int myAtoi(String str) {
        // 判断是否为空和长度是否为0
        if (str == null || str.length() == 0)
            return 0;

        // 去掉字符串首尾的空格
        str = str.trim();

        int sign = 1, start = 0, len = str.length();
        long sum = 0;

        // 判断符号
        char firstChar = str.charAt(0);
        if (firstChar == '+') {
            sign = 1;
            start++;
        } else if (firstChar == '-') {
            sign = -1;
            start++;
        }

        for (int i = start; i < len; i++) {
            if (!Character.isDigit(str.charAt(i))) // 判断是否为数字
                return (int) sum * sign;

            sum = sum * 10 + str.charAt(i) - '0';

            // 判断是否越界
            if (sign == 1 && sum > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && (-1) * sum < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }

        return (int) sum * sign;
    }

    /**
     * 判断一个整数是否是回文数，不能使用辅助空间
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
            System.out.println(revertedNumber + " " + x);
        }
        //当x的长度为奇数时，x == revertedNumber / 10去掉中间通过的数字
        System.out.println(x == revertedNumber || x == revertedNumber / 10);
        return x == revertedNumber || x == revertedNumber / 10;
    }

    public static void main(String[] args) {
        Solutions solutions = new Solutions();
        solutions.isPalindrome(123454321);
    }

}
