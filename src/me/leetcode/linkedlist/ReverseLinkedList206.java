package me.leetcode.linkedlist;

import me.leetcode.util.ListNode;

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
    /**
     * 我的解答，使用迭代
     * @param head
     * @return
     */
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
    
    /**
     * 参考解答：使用递归
     * The recursive version is slightly trickier and the key is to work backwards. 
     * Assume that the rest of the list had already been reversed,now how do I reverse the front part? 
     * Let's assume the list is: n1 → … → nk-1 → nk → nk+1 → … → nm → Ø
     * 
     * Assume from node nk+1 to nm had been reversed and you are at node nk.
     * 
     * n1 → … → nk-1 → nk → nk+1 ← … ← nm
     * 
     * We want nk+1’s next node to point to nk.
     * 
     * So,nk.next.next = nk;
     * 
     * Be very careful that n1's next must point to Ø. If you forget about this,
     * your linked list has a cycle in it. This bug could be caught if you test
     * your code with a linked list of size 2.
     * 
     * @param head
     * @return
     */
    public ListNode reverseListByRecursively(ListNode head) {
	if (head == null || head.next == null) {
	    return head;
	}
	ListNode point = reverseListByRecursively(head.next);
	head.next.next = head;
	head.next = null;
	return point;
    }
}
