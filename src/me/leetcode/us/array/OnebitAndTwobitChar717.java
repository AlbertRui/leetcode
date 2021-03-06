package me.leetcode.us.array;

/**
 * We have two special characters. The first character can be represented by one
 * bit 0. The second character can be represented by two bits (10 or 11).
 * <p>
 * Now given a string represented by several bits. Return whether the last
 * character must be a one-bit character or not. The given string will always
 * end with a zero.
 * <p>
 * Example 1: Input: bits = [1, 0, 0] Output: True Explanation: The only way to
 * decode it is two-bit character and one-bit character. So the last character
 * is one-bit character.
 * <p>
 * Example 2: Input: bits = [1, 1, 1, 0] Output: False Explanation: The only way
 * to decode it is two-bit character and two-bit character. So the last
 * character is NOT one-bit character.
 * <p>
 * Note: 1 <= len(bits) <= 1000. bits[i] is always 0 or 1
 *
 * @author Administrator
 */
public class OnebitAndTwobitChar717 {
    public boolean isOneBitCharacter(int[] bits) {
        int lastIndex = bits.length - 1;
        int i = 0;
        while (i < lastIndex) {
            // 1开头移动两位，0开头移动一位
            i += bits[i] + 1;
        }
        return i == lastIndex;
    }
}
