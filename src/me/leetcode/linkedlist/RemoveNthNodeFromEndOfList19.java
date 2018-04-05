package me.leetcode.linkedlist;

import me.leetcode.util.ListNode;

/**
 * Given a linked list, remove the nth node from the end of list and return its head.
 * <p>
 * For example,
 * <p>
 * Given linked list: 1->2->3->4->5, and n = 2.
 * <p>
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 *
 * @author AlbertRui
 * @date 2018-04-05 10:57
 */
public class RemoveNthNodeFromEndOfList19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //这里防止空指针异常
        ListNode p = new ListNode(0);
        p.next = head;

        ListNode slow = p;     // 快慢指针
        ListNode fast = p;
        while (n-- != 0)              // fast先行n步
            fast = fast.next;

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;

        return p.next;
    }
}
