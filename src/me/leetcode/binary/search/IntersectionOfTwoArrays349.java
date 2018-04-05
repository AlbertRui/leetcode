package me.leetcode.binary.search;

import java.util.Set;
import java.util.TreeSet;

/**
 * Given two arrays, write a function to compute their intersection.
 * <p>
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 * <p>
 * Note:
 * Each element in the result must be unique.
 * The result can be in any order.
 *
 * @author AlbertRui
 * @date 2018-04-05 17:44
 */
public class IntersectionOfTwoArrays349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> nums = new TreeSet<>();
        Set<Integer> result = new TreeSet<>();
        for (int num : nums1) {
            nums.add(num);
        }
        for (int num : nums2) {
            if (nums.contains(num)) {
                result.add(num);
            }
        }
        int[] finalResult = new int[result.size()];
        int i = 0;
        for (int num : result) {
            finalResult[i++] = num;
        }
        return finalResult;
    }
}
