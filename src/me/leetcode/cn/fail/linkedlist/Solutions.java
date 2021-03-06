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

    /**
     * 时间超限
     * 21. 合并两个有序链表
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * 示例：
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode temp = result;
        while (l1 != null || l2 != null) {
            if (l1 == null)
                temp = l2;
            if (l2 == null)
                temp = l1;
            temp = l1.val < l2.val ? l1 : l2;
            temp = temp.next;
        }
        return result;
    }

}
