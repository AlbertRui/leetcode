package me.leetcode.array;

import java.util.Arrays;

/**
 * Given an array of size n, find the majority element. The majority element is
 * the element that appears more than ⌊ n/2 ⌋ times.
 * 
 * You may assume that the array is non-empty and the majority element always
 * exist in the array.
 * 
 * @author Administrator
 *
 */
public class MajorityElement169 {
    public int majorityElement(int[] nums) {
	Arrays.sort(nums);
	return nums[nums.length / 2];
    }
}

/**
 * 这种解法就是在《剑指offer》上看到的那种解法。它本质上也是一种分治法，只不过在编程时使用了一些技巧，结果没那么容易看出来了。
 * 算法的思想如下：每次从数组中找出一对不同的元素，将它们从数组中删除，直到遍历完整个数组。由于这道题已经说明一定存在一个出现次数超过一半的元素，
 * 所以遍历完数组后数组中一定会存在至少一个元素。
 * 上面就是这种算法的思想，删除操作可以在常数时间内完成，但是查找不同的元素无法在常数时间内完成，这里有一个技巧。
 * 在算法执行过程中，使用常量空间来记录一个候选元素c以及它的出现次数f(c)，c即为当前阶段出现次数超过一半的元素。在遍历开始之前，该元素c为空，f(c)=
 * 0。然后开始遍历数组A时：
 * 
 * 如果f(c)为0，表示当前并没有候选元素，也就是说之前的遍历过程中没有找到超过一半的元素。那么，如果超过一半的元素c存在，那么c在剩下的子数组中，
 * 出现的次数也一定超过一半。因次我们可以将原始问题转化成它的子问题。此时c赋值为当前元素，同时f(c)=1。
 * 如果当前A[i]==c，那么f(c)+=1。（没有找到相同的元素，只需要把相同的元素累加起来）
 * 如果当前元素A[i]!=c，那么f(c)-=1（相当于删除一个c），不对A[i]做任何处理（相当于删除A[i]）
 * 如果遍历结束之后，f(c)不为0，那么再次遍历一遍数组，如果c真正出现的频率，上面算法的时间复杂度是O(n)，空间复杂度为O(1)。
 * 这种方法的分析来自http://blog.csdn.net/pi9nc/article/details/9355293。
 * 
 * runtime:20ms
 * 
 * @author Administrator
 *
 */
class Solution1 {
    public int majorityElement(int[] nums) {
	int count = 0;
	int result = nums[0];
	for (int i = 0; i < nums.length; i++) {
	    if (count == 0 || nums[i] == result) {
		count++;
		result = nums[i];
	    } else
		count--;
	}
	return result;
    }
}
