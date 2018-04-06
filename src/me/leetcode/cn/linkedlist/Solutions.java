package me.leetcode.cn.fail.linkedlist;

import me.leetcode.util.ListNode;

/**
 * @author AlbertRui
 * @date 2018-04-05 22:58
 */
public class Solutions {

    /**
     * 给定两个非空链表来代表两个非负整数，位数按照逆序方式存储，
     * 它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     * <p>
     * 示例：
     * <p>
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     * <p>
     * 我的这种解法处理不了大数据
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        long num1 = 0;
        long num2 = 0;

        int i = 0;
        while (l1 != null) {
            num1 += l1.val * Math.pow(10, i++);
            l1 = l1.next;
        }
        i = 0;
        while (l2 != null) {
            num2 += l2.val * Math.pow(10, i++);
            l2 = l2.next;
        }
        Long num = num1 + num2;
        String numStr = num.toString();
        ListNode result = new ListNode(0);
        ListNode temp = result;
        int j = numStr.length();
        while (j > 0) {
            temp.val = Integer.parseInt(String.valueOf(numStr.charAt(--j)));
            if (j != 0) {
                temp.next = new ListNode(0);
                temp = temp.next;
            }
        }
        return result;
    }
}
