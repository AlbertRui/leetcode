package me.leetcode.linkedlist;

/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes. 
 * Please note here we are talking about the node number and not the value in the nodes.
 * 
 * You should try to do it in place. The program should run in O(1) space
 * complexity and O(nodes) time complexity.
 * 
 * Example: Given 1->2->3->4->5->NULL, return 1->3->5->2->4->NULL.
 * 
 * Note: The relative order inside both the even and odd groups should remain as
 * it was in the input. The first node is considered odd, the second node even
 * and so on ...
 * 
 * Credits: Special thanks to @DjangoUnchained for adding this problem and
 * creating all test cases.
 * 
 * @author Administrator
 *
 */
public class OddEvenLinkedList328 {
    public ListNode oddEvenList(ListNode head) {
	if (head == null || head.next == null || head.next.next == null)
	    return head;
	ListNode tmpOdd = head;
	ListNode tmpEven = head.next;
	ListNode even = tmpEven;
	ListNode current = tmpEven.next;
	boolean isOdd = true;
	while (current != null) {
	    if (isOdd) {
		tmpOdd.next = current;
		tmpOdd = current;
	    } else {
		tmpEven.next = current;
		tmpEven = current;
	    }
	    current = current.next;
	    isOdd = !isOdd;
	}
	tmpEven.next = null;
	tmpOdd.next = even;
	return head;
    }
}
