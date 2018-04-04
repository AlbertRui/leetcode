package me.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some
 * elements appear twice and others appear once.
 * <p>
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 * <p>
 * Could you do it without extra space and in O(n) runtime? You may assume the
 * returned list does not count as extra space.
 * <p>
 * Example:
 * <p>
 * Input: [4,3,2,7,8,2,3,1]
 * <p>
 * Output: [5,6]
 *
 * @author Administrator
 */
public class FindAllNumbersDisappearedinanArray446 {
    public static void main(String[] args) {
        int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        findDisappearedNumbers(nums);
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        int n = nums.length;
        // 关键在于将每个元素的值减一所对应的数组下标的元素变为负值
        for (int i = 0; i < n; i++) {
            int currNum = Math.abs(nums[i]);
            if (nums[currNum - 1] > 0)
                nums[currNum - 1] = -nums[currNum - 1];
            System.out.println(nums[i] + "," + currNum + "," + nums[currNum - 1]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                res.add(i + 1);
        }
        return res;
    }
}