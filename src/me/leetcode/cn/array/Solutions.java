package me.leetcode.cn.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /**
     * 4. 两个排序数组的中位数
     * 有两个大小为 m 和 n 的排序数组 nums1 和 nums2 。
     * <p>
     * 请找出两个排序数组的中位数并且总的运行时间复杂度为 O(log (m+n)) 。
     * <p>
     * 示例 1:
     * <p>
     * nums1 = [1, 3]
     * nums2 = [2]
     * <p>
     * 中位数是 2.0
     * <p>
     * <p>
     * 示例 2:
     * <p>
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     * <p>
     * 中位数是 (2 + 3)/2 = 2.5
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) { // to ensure m<=n
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && nums2[j - 1] > nums1[i]) {
                iMin = iMin + 1; // i is too small
            } else if (i > iMin && nums1[i - 1] > nums2[j]) {
                iMax = iMax - 1; // i is too big
            } else { // i is perfect
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums2[j], nums1[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    /**
     * 11. 盛最多水的容器
     * 给定 n 个正整数 a1，a2，...，an，其中每个点的坐标用（i, ai）表示。
     * 画 n 条直线，使得线 i 的两个端点处于（i，ai）和（i，0）处。
     * 请找出其中的两条直线，使得他们与 X 轴形成的容器能够装最多的水。
     * 注意：你不能倾斜容器，n 至少是2。
     */
    public int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }

    /**
     * 15. 三数之和
     * 给定一个包含 n 个整数的数组 S，是否存在属于 S 的三个元素 a，b，c 使得 a + b + c = 0 ？
     * 找出所有不重复的三个元素组合使三个数的和为零。
     * 注意：结果不能包括重复的三个数的组合。
     * 例如, 给定数组 S = [-1, 0, 1, 2, -1, -4]，
     * 一个结果集合为：
     * [
     * [-1, 0, 1],
     * [-1, -1, 2]
     * ]
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<Integer> list;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int sum, begin = i + 1, end = nums.length - 1;
            while (begin < end) {
                sum = nums[i] + nums[begin] + nums[end];
                if (sum == 0) {
                    list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[begin]);
                    list.add(nums[end]);
                    result.add(list);
                    begin++;
                    end--;
                    while (begin < end && nums[begin] == nums[begin - 1]) begin++;
                    while (begin < end && nums[end] == nums[end + 1]) end--;
                } else if (sum > 0) {
                    end--;
                } else {
                    begin++;
                }
            }
        }
        return result;
    }

    /**
     * 16. 最接近的三数之和
     * 给定一个包括 n 个整数的数组 S，找出 S 中的三个整数使得他们的和与给定的数 target 最接近。
     * 返回这三个数的和。假定每组输入只存在一个答案。
     * 例如，给定数组 S = {-1 2 1 -4}, 并且 target = 1.
     * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int pFront, pBack, sum, result = 0, distance = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            pFront = i + 1;
            pBack = nums.length - 1;
            while (pFront < pBack) {
                sum = nums[i] + nums[pFront] + nums[pBack];
                if (sum == target) {
                    return sum;
                } else if (Math.abs(sum - target) < distance) {
                    distance = Math.abs(sum - target);
                    result = sum;
                }
                if (sum < target) {
                    pFront++;
                } else {
                    pBack--;
                }
            }
        }
        return result;
    }

    /**
     * 18. 四数相加
     * 给定一个含有 n 个整数的数组 S，数列 S 中是否存在元素 a，b，c 和 d 使 a + b + c + d = target ？
     * <p>
     * 请在数组中找出所有满足各元素相加等于特定值 的 不重复 组合。
     * <p>
     * 注意：解决方案集不能包含重复的四元组合。
     * <p>
     * 例如，给定数组 S = [1, 0, -1, 0, -2, 2]，并且给定 target = 0。
     * <p>
     * 示例答案为：
     * [
     * [-1,  0, 0, 1],
     * [-2, -1, 1, 2],
     * [-2,  0, 0, 2]
     * ]
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        return null;
    }

}
