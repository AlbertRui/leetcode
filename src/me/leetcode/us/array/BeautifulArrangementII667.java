package me.leetcode.us.array;

/**
 * 这题简单点说就是求1-n构成的数组的一个序，这个序要满足两两之间元素差的绝对值（相同的值算一种情况）构成的数组大小为k。返回满足条件的数组序列。
 * 这题我拿到的第一反应是回溯法，尝试解了一下发现复杂度太高，然后就尝试着去找规律，发现是很简单的一个规律题。首先我们确定的一点：元素差最多的个数是n-1个，
 * 这个n-1的构成也很容易发现，较大的数和较小的数交替形成的序列就满足要求。例如，我们假设n= 6，k = 5，那么这个序列就是 6 1 5 2 4 3
 * 形成的k个元素差为： 5 4 3 2 1 （反向也是可以的 即 1 6 2 5 3
 * 4）若k不等于n-1，我们只需要按上述规律形成满足k-1的序列，剩余序列按递减序即可（剩余的差值都为1）。 假设n = 6，k =4，我们得到的序列即是：
 * 1 6 2 5 4 3。
 *
 * @author Administrator
 */
public class BeautifulArrangementII667 {
    public int[] constructArray(int n, int k) {
        int index = 0;
        int[] arr = new int[n];
        // 最大n-1
        for (int i = 1, j = n; i <= j; ) {
            if (k > 1) {
                arr[index++] = (k-- % 2 == 1 ? i++ : j--);
            } else {
                arr[index++] = i++;
            }
        }
        return arr;
    }
}
