package me.leetcode.cn.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int begin, end, sum;
        for (int i = 0; i < nums.length - 3; i++) {
            if (nums[i] > target / 4) break;
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (nums[j] > (target - nums[i]) / 3) break;
                if (j != i + 1 && nums[j] == nums[j - 1]) continue;
                begin = j + 1;
                end = nums.length - 1;
                while (begin < end) {
                    sum = nums[i] + nums[j] + nums[begin] + nums[end];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[begin], nums[end]));
                        begin++;
                        end--;
                        while (begin < end && nums[begin] == nums[begin - 1]) begin++;
                        while (begin < end && nums[end] == nums[end + 1]) end--;
                    } else if (sum > target) {
                        end--;
                    } else {
                        begin++;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 90. 子集 II
     * 给定一个可能包含重复整数的列表，返回所有可能的子集（幂集）。
     * 注意事项：解决方案集不能包含重复的子集。
     * 例如，如果 nums = [1,2,2]，答案为：
     * [[2],[1],[1,2,2],[2,2],[1,2],[]]
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backTrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backTrack(List<List<Integer>> list, List<Integer> subset, int[] nums, int start) {
        list.add(new ArrayList<>(subset));
        for (int curr = start; curr < nums.length; curr++) {
            if (curr != start && nums[curr] == nums[curr - 1]) continue;
            subset.add(nums[curr]);
            backTrack(list, subset, nums, curr + 1);
            subset.remove(subset.size() - 1);
        }
    }

    /**
     * 26. 删除排序数组中的重复项
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     * 示例 1:
     * 给定数组 nums = [1,1,2],
     * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     * 你不需要考虑数组中超出新长度后面的元素。
     * 示例 2:
     * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
     * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
     * 你不需要考虑数组中超出新长度后面的元素。
     * 说明:
     * 为什么返回数值是整数，但输出的答案是数组呢?
     * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
     * <p>
     * 你可以想象内部操作如下:
     * // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
     * int len = removeDuplicates(nums);
     * // 在函数里修改输入数组对于调用者是可见的。
     * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
     * for (int i = 0; i < len; i++) {
     * print(nums[i]);
     * }
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++)
            if (nums[j] != nums[i])
                nums[++i] = nums[j];
        return i + 1;
    }

    /**
     * 647. 回文子串
     * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
     * 示例 1:
     * 输入: "abc"
     * 输出: 3
     * 解释: 三个回文子串: "a", "b", "c".
     * 示例 2:
     * 输入: "aaa"
     * 输出: 6
     * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
     * 注意:
     * 输入的字符串长度不会超过1000。
     */
    public int countSubstrings(String s) {
        int N = s.length(), ans = 0;
        for (int center = 0; center <= 2 * N - 1; center++) {
            int left = center / 2;
            int right = left + center % 2;
            while (left >= 0 && right < N && s.charAt(left) == s.charAt(right)) {
                ans++;
                left--;
                right++;
            }
        }
        return ans;
    }

    /**
     * 217. 存在重复
     * 给定一个整数数组，判断是否存在重复元素。
     * 如果任何值在数组中出现至少两次，函数应该返回 true。如果每个元素都不相同，则返回 false。
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) return true;
            set.add(num);
        }
        return false;
    }

    /**
     * 495. 提莫攻击
     * 在《英雄联盟》的世界中，有一个叫“提莫”的英雄，他的攻击可以让敌方英雄艾希（编者注：寒冰射手）进入中毒状态。
     * 现在，给出提莫对艾希的攻击时间序列和提莫攻击的中毒持续时间，你需要输出艾希的中毒状态总时长。
     * 你可以认为提莫在给定的时间点进行攻击，并立即使艾希处于中毒状态。
     * 示例1:
     * 输入: [1,4], 2
     * 输出: 4
     * 原因: 在第1秒开始时，提莫开始对艾希进行攻击并使其立即中毒。中毒状态会维持2秒钟，直到第2秒钟结束。
     * 在第4秒开始时，提莫再次攻击艾希，使得艾希获得另外2秒的中毒时间。
     * 所以最终输出4秒。
     * 示例2:
     * 输入: [1,2], 2
     * 输出: 3
     * 原因: 在第1秒开始时，提莫开始对艾希进行攻击并使其立即中毒。中毒状态会维持2秒钟，直到第2秒钟结束。
     * 但是在第2秒开始时，提莫再次攻击了已经处于中毒状态的艾希。
     * 由于中毒状态不可叠加，提莫在第2秒开始时的这次攻击会在第3秒钟结束。
     * 所以最终输出3。
     * 注意：
     * 你可以假定时间序列数组的总长度不超过10000。
     * 你可以假定提莫攻击时间序列中的数字和提莫攻击的中毒持续时间都是非负整数，并且不超过10,000,000。
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries.length == 0) return 0;
        int timeDif, res = duration;
        for (int i = 1; i < timeSeries.length; i++) {
            timeDif = timeSeries[i] - timeSeries[i - 1];
            if (timeDif < duration)
                res -= (duration - timeDif);
            res += duration;
        }
        return res;
    }

    /**
     * 41. 缺失的第一个正数
     * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
     * 示例 1:
     * 输入: [1,2,0]
     * 输出: 3
     * 示例 2:
     * 输入: [3,4,-1,1]
     * 输出: 2
     * 示例 3:
     * 输入: [7,8,9,11,12]
     * 输出: 1
     * 说明:你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
     */
    public int firstMissingPositive(int[] nums) {
        int i = 0, n = nums.length;
        while (i < n) {
            if (nums[i] != (i + 1) && nums[i] >= 1 && nums[i] <= n && nums[nums[i] - 1] != nums[i])
                swap(nums, i, nums[i] - 1);
            else
                i++;
        }
        for (i = 0; i < n; ++i)
            if (nums[i] != (i + 1))
                return i + 1;
        return n + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 88. 合并两个有序数组
     * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
     * 说明:初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     * 示例:
     * 输入:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     * 输出: [1,2,2,3,5,6]
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int index = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j])
                nums1[index--] = nums1[i--];
            else
                nums1[index--] = nums2[j--];

        }
        while (i >= 0) nums1[index--] = nums1[i--];
        while (j >= 0) nums1[index--] = nums2[j--];
    }

}
