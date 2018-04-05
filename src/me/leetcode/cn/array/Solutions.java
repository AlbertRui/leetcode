package me.leetcode.cn.array;

/**
 * @author AlbertRui
 * @date 2018-04-05 20:41
 */
public class Solutions {

    /**
     * 给定一个整数数列，找出其中和为特定值的那两个数。
     * <p>
     * 你可以假设每个输入都只会有一种答案，同样的元素不能被重用。
     * <p>
     * 示例:
     * <p>
     * 给定 nums = [2, 7, 11, 15], target = 9
     * <p>
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
