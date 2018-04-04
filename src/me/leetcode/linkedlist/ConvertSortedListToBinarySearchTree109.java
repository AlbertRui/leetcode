package me.leetcode.linkedlist;

import me.leetcode.util.ListNode;
import me.leetcode.util.TreeNode;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * Example:
 * Given the sorted linked list: [-10,-3,0,5,9],
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 * 0
 * / \
 * -3 9
 * /  /
 * -10  5
 *
 * @author AlbertRui
 * @create 2018-02-15 23:48
 */
public class ConvertSortedListToBinarySearchTree109 {
    public TreeNode sortedListToBST(ListNode head) {
        return sortedListToBST(head, null);
    }

    public TreeNode sortedListToBST(ListNode head, ListNode end) {
        if (head == end)
            return null;
        if (head.next == end) {
            TreeNode root = new TreeNode(head.val);
            return root;
        }
        ListNode slow = head, fast = head;
        while (fast.next != end && fast.next.next != end) {
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.right = sortedListToBST(slow.next, end);
        root.left = sortedListToBST(head, slow);
        return root;
    }

}
