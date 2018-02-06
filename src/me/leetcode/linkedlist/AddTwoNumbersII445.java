package me.leetcode.linkedlist;

import java.util.Stack;

/**
 * Definition for singly-linked list. 
 * public class ListNode { 
 * 	int val;
 * 	ListNode next; 
 * 	ListNode(int x) { val = x; } 
 * }
 * 
 * You are given two non-empty linked lists representing two non-negative integers. 
 * The most significant digit comes first and each of their nodes contain a single digit. 
 * Add the two numbers and return it as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * 
 * Follow up: What if you cannot modify the input lists? In other words,
 * reversing the lists is not allowed.
 * 
 * Example:
 * 
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4) 
 * Output: 7 -> 8 -> 0 -> 7
 */
public class AddTwoNumbersII445 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	ListNode result = null;
	ListNode firstList = reverse(l1);
	ListNode secondList = reverse(l2);
	return result;
    }
    
    public ListNode reverse(ListNode head) {
	ListNode next = null;
	ListNode result = null;
	while(head != null) {
	    next = head.next;
	    head.next = result;
	    result = head;
	    head = next;
	}
	return result;
    }
}
