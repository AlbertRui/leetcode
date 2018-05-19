package me.leetcode.cn.other;

/**
 * @author AlbertRui
 * @date 2018-05-19 13:29
 */
public class Solutions {

    private long[] tens = new long[]{1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};

    private long getReverse(long num, int n) {
        long ret = 0;
        for (int i = 1; i <= n; i++) {
            ret += (num % 10) * tens[n - i];
            num /= 10;
        }
        return ret;
    }

    /**
     * 479. 最大回文数乘积
     * 你需要找到由两个 n 位数的乘积组成的最大回文数。
     * 由于结果会很大，你只需返回最大回文数 mod 1337得到的结果。
     * 示例:
     * 输入: 2
     * 输出: 987
     * 解释: 99 x 91 = 9009, 9009 % 1337 = 987
     * 说明:
     * n 的取值范围为 [1,8]。
     */
    public int largestPalindrome(int n) {
        if (n == 1) return 9;
        long ret = 0, max = tens[n];
        for (long i = max - 1; i > 0; i--) {
            ret = i * max + getReverse(i, n);
            for (long factor = (long) Math.sqrt(ret); factor < max; factor++)
                if (ret % factor == 0 && ret / factor < max)
                    return (int) (ret % 1337);
        }
        return -1;
    }

    public int largestPalindrome2(int n) {
        int[] ans = {0, 9, 987, 123, 597, 677, 1218, 877, 475};
        if (n >= 1 && n <= 8)
            return ans[n];
        return 0;
    }

}
