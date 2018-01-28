package me.leetcode.array;

/**
 * Given a binary array, find the maximum number of consecutive 1s in this array.
 * @author Administrator
 *
 */
public class MaxConsecutiveOnes485 {

    public int findMaxConsecutiveOnes(int[] nums) {
	int sum = 0;
	int maxSum = 0;
	for (int num : nums) {
	    if (num == 1)
		sum += 1;
	    if (sum > maxSum)
		maxSum = sum;
	    if (num == 0)
		sum = 0;
	}
	return maxSum;
    }
}
