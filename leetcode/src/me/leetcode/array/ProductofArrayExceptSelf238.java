package me.leetcode.array;

/**
 * Given an array of n integers where n > 1, nums, return an array output such
 * that output[i] is equal to the product of all the elements of nums except
 * nums[i].
 * 
 * Solve it without division and in O(n).
 * 
 * For example, given [1,2,3,4], return [24,12,8,6].
 * 
 * Follow up: Could you solve it with constant space complexity? (Note: The
 * output array does not count as extra space for the purpose of space
 * complexity analysis.)
 * 
 * @author Administrator
 *
 */
public class ProductofArrayExceptSelf238 {
    public static int[] productExceptSelf(int[] nums) {
	int product = 1;
	int flag = 0;
	for (int num : nums) {
	    if (num != 0)
		product *= num;
	    else
		flag ++;
	}
	int len = nums.length;
	/**
	 * 注意！这里不能用foreach
	 */
	for (int i = 0; i < len; i++) {
	    System.out.println(nums[i]);
	    if(flag == 0) {
		nums[i] = product / nums[i];
	    }else if(flag == 1) {
		if (nums[i] == 0) {
		    nums[i] = product;
		} else {
		    nums[i] = 0;
		}
	    } else {
		nums[i] = 0;
	    }
	    System.out.println(nums[i] + "9");
	}
	System.out.println("==================");
	return nums;
    }
    public static void main(String[] args) {
	int[] nums = new int[] {1, 0 };
	nums = productExceptSelf(nums);
	for (int i : nums) {
	    System.out.println(i);
	}
    }
}
