package me.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements
 * appear twice and others appear once.
 * <p>
 * Find all the elements that appear twice in this array.
 * <p>
 * Could you do it without extra space and in O(n) runtime?
 *
 * @author Administrator
 */
public class FindAllDuplicatesInAnArray442 {

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        for (int num : nums) {
            int currNum = Math.abs(num);
            if (nums[currNum - 1] > 0) {
                nums[currNum - 1] = -nums[currNum - 1];
            } else {
                result.add(currNum);
            }
        }
        return result;
    }
}
