package me.leetcode.us.linkedlist;

import me.leetcode.util.ListNode;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 * <p>
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 *
 * @author AlbertRui
 * @date 2018-02-14 12:11
 */
public class RemoveDuplicatesfromSortedList83 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode p_current = head;
        ListNode p_next = head.next;

        while (p_next != null) {
            if (p_current.val == p_next.val) {
                p_current.next = p_next.next;
            } else {
                p_current = p_current.next;
            }
            p_next = p_next.next;
        }
        return head;
    }
}

/**
 * 更优解
 */
class _RemoveDuplicatesfromSortedList83 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.val == current.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }
}
