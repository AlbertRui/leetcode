package me.leetcode.linkedlist;

import me.leetcode.util.ListNode;
import me.leetcode.util.TreeNode;

import java.util.HashMap;
import java.util.Stack;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * Example:
 * Given the sorted linked list: [-10,-3,0,5,9],
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *       0
 *      / \
 *     -3 9
 *     /  /
 *   -10  5
 *
 * @author AlbertRui
 * @create 2018-02-15 23:48
 */
public class ConvertSortedListtoBinarySearchTree109 {
    public TreeNode sortedListToBST(ListNode head) {
        return null;
    }
    /**
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        TreeNode root = null;
        TreeNode p = root;
        Stack<TreeNode> stack = new Stack<>();
        for (int i = 0; i < preorder.length; i++) {
            int temp = map.get(preorder[i]);
            TreeNode node = new TreeNode(preorder[i]);
            if (stack.isEmpty()) {
                root = node;
//              stack.add(node);
                p = root;
            } else {
                if (temp < map.get(stack.peek().val)) {
                    p.left = node;
                    p = p.left;
                } else {
                    while (!stack.isEmpty() && temp > map.get(stack.peek().val)) {
                        p = stack.pop();
                    }
                    p.right = node;
                    p = p.right;
                }
            }
            stack.add(node);
        }

        return root;
    }
}
