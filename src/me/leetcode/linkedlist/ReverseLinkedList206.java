package me.leetcode.linkedlist;

/**
 * Reverse a singly linked list.
 * 
 * Hint:
 * A linked list can be reversed either iteratively or recursively. 
 * Could you implement both?
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 * @author Administrator
 *
 */
public class ReverseLinkedList206 {
    public ListNode reverseList(ListNode head) {
	ListNode res = null;
	ListNode next = null;
	while (head != null) {
	    next = head.next;
	    head.next = res;
	    res = head;
	    head = next;
	}
	return res;
    }
}
