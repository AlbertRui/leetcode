package me.leetcode.us.linkedlist;

import me.leetcode.util.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 * Write a function to delete a node (except the tail) in a singly linked list,
 * given only access to that node.
 * <p>
 * Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node
 * with value 3, the linked list should become 1 -> 2 -> 4 after calling your
 * function.
 * <p>
 * 解题思路：将这个node之改为下一个node的值，并删除下一个node即可
 *
 * @author Administrator
 */
public class DeleteNodeInLinkedList237 {

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
