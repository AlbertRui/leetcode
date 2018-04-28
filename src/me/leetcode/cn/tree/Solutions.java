package me.leetcode.cn.tree;

import me.leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AlbertRui
 * @date 2018-04-22 17:29
 */
public class Solutions {
    /**
     * 104. 二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明: 叶子节点是指没有子节点的节点。
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回它的最大深度 3 。
     */
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    /**
     * 257. 二叉树的所有路径
     * 给定一个二叉树，返回从根节点到叶节点的所有路径。
     * 例如，给定以下二叉树:
     * 1
     * / \
     * 2   3
     * \
     * 5
     * 所有根到叶路径是:["1->2->5", "1->3"]
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        findPaths(root, new StringBuilder(), res);
        return res;
    }

    private void findPaths(TreeNode node, StringBuilder stringBuilder, List<String> res) {
        if (node == null) return;
        int len = stringBuilder.length();
        stringBuilder.append(node.val);
        if (node.left == null && node.right == null) {
            res.add(stringBuilder.toString());
        } else {
            stringBuilder.append("->");
            findPaths(node.left, stringBuilder, res);
            findPaths(node.right, stringBuilder, res);
        }
        stringBuilder.setLength(len);
    }

    /**
     * 538. 把二叉搜索树转换为累加树
     * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，
     * 使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
     * 例如：
     * 输入: 二叉搜索树:
     * 5
     * /   \
     * 2     13
     * 输出: 转换为累加树:
     * 18
     * /   \
     * 20     13
     */
    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }

    private int sum = 0;

    /**
     * 129. 求根到叶子节点数字之和
     * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
     * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
     * 计算从根到叶子节点生成的所有数字之和。
     * 说明: 叶子节点是指没有子节点的节点。
     * 示例 1:
     * 输入: [1,2,3]
     * 1
     * / \
     * 2   3
     * 输出: 25
     * 解释:
     * 从根到叶子节点路径 1->2 代表数字 12.
     * 从根到叶子节点路径 1->3 代表数字 13.
     * 因此，数字总和 = 12 + 13 = 25.
     * 示例 2:
     * 输入: [4,9,0,5,1]
     * 4
     * / \
     * 9   0
     * / \
     * 5   1
     * 输出: 1026
     * 解释:
     * 从根到叶子节点路径 4->9->5 代表数字 495.
     * 从根到叶子节点路径 4->9->1 代表数字 491.
     * 从根到叶子节点路径 4->0 代表数字 40.
     * 因此，数字总和 = 495 + 491 + 40 = 1026.
     */
    public int sumNumbers(TreeNode root) {
        if (root != null)
            dfs(root, root.val);
        return res;
    }

    private int res = 0;

    private void dfs(TreeNode root, int value) {
        if (root.left == null && root.right == null) //leaf
            res += value;
        if (root.left != null)
            dfs(root.left, value * 10 + root.left.val);
        if (root.right != null)
            dfs(root.right, value * 10 + root.right.val);
    }

}
