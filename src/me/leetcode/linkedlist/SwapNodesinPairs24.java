package me.leetcode.linkedlist;

import me.leetcode.util.ListNode;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * For example,
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * Your algorithm should use only constant space.
 * You may not modify the values in the list, only nodes itself can be changed.
 *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 * @author AlbertRui
 * @create 2018-02-14 13:48
 */
public class SwapNodesinPairs24 {
    /**
     * ListNode tmp = head.next;
     * // become : 2 -> 3 -> 4
     * head.next = tmp.next;
     * // become : 1 -> 3 -> 4
     * tmp.next = head;
     * // become : 2 -> 1 -> 3 -> 4
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode temp = head.next;
        head.next = swapPairs(temp.next);
        temp.next = head;
        return temp;
    }
}
