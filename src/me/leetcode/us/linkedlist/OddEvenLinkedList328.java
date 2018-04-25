package me.leetcode.us.linkedlist;

import me.leetcode.util.ListNode;

/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes.
 * Please note here we are talking about the node number and not the value in the nodes.
 * <p>
 * You should try to do it in place. The program should run in O(1) space
 * complexity and O(nodes) time complexity.
 * <p>
 * Example: Given 1->2->3->4->5->NULL, return 1->3->5->2->4->NULL.
 * <p>
 * Note: The relative order inside both the even and odd groups should remain as
 * it was in the input. The first node is considered odd, the second node even
 * and so on ...
 * <p>
 * Credits: Special thanks to @DjangoUnchained for adding this problem and
 * creating all test cases.
 *
 * @author Administrator
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
            if (isOdd)
                tmpOdd = tmpOdd.next = current;
            else
                tmpEven = tmpEven.next = current;
            current = current.next;
            isOdd = !isOdd;
        }
        tmpEven.next = null;
        tmpOdd.next = even;
        return head;
    }
}

/**
 * 更优的解法
 *
 * @author Administrator
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null)
            return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
