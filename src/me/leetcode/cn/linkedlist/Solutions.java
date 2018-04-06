package me.leetcode.cn.linkedlist;

import me.leetcode.util.ListNode;

/**
 * @author AlbertRui
 * @date 2018-04-05 22:58
 */
@SuppressWarnings("ALL")
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
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp = new ListNode(0);
        ListNode res = temp;
        int flag = 0;
        int val = 0;
        while (l1 != null || l2 != null) {
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            val = (val1 + val2 + flag) % 10;
            flag = (val1 + val2 + flag) / 10;//只有0和1两种结果
            temp = temp.next = new ListNode(val);
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        if (flag == 1)
            temp.next = new ListNode(1);
        return res.next;
    }
}
