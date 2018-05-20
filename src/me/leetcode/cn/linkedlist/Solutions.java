package me.leetcode.cn.linkedlist;

import me.leetcode.util.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author AlbertRui
 * @date 2018-04-05 22:58
 */
@SuppressWarnings("ALL")
public class Solutions {

    /**
     * 给定两个非空链表来代表两个非负整数，位数按照逆序方式存储，
     * 它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     * <p>
     * 示例：
     * <p>
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     * <p>
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp = new ListNode(0);
        ListNode res = temp;
        int flag = 0;
        int val = 0;
        while (l1 != null || l2 != null) {
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            val = (val1 + val2 + flag) % 10;
            flag = (val1 + val2 + flag) / 10;//只有0和1两种结果
            temp = temp.next = new ListNode(val);
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        if (flag == 1)
            temp.next = new ListNode(1);
        return res.next;
    }

    /**
     * 19. 删除链表的倒数第N个节点
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * 说明：给定的 n 保证是有效的。
     * 进阶：你能尝试使用一趟扫描实现吗？
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int len = 0;
        while (first != null) {
            len++;
            first = first.next;
        }
        len -= n;
        first = dummy;
        while (len-- > 0) {
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }

    /**
     * 21. 合并两个有序链表
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * 示例：
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode temp = new ListNode(0);
        ListNode result = temp;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                temp.next = l2;
                return result.next;
            } else if (l2 == null) {
                temp.next = l1;
                return result.next;
            } else {
                if (l1.val < l2.val) {
                    temp.next = l1;
                    l1 = l1.next;
                } else {
                    temp.next = l2;
                    l2 = l2.next;
                }
            }
            temp = temp.next;
        }
        return result.next;
    }

    /**
     * 23. 合并K个排序链表
     * 参考：https://blog.csdn.net/katrina95/article/details/79112038
     * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
     * 示例:
     * 输入:
     * [
     * 1->4->5,
     * 1->3->4,
     * 2->6
     * ]
     * 输出: 1->1->2->3->4->4->5->6
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (ListNode listNode : lists)
            if (listNode != null) pq.add(listNode);
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while (!pq.isEmpty()) {
            current = current.next = pq.poll();
            if (current.next != null)
                pq.add(current.next);
        }
        return dummy.next;
    }

    /**
     * 24. 两两交换链表中的节点
     * https://blog.csdn.net/runningtortoises/article/details/45645433
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * 示例:
     * 给定 1->2->3->4, 你应该返回 2->1->4->3.
     * 说明:你的算法只能使用常数的额外空间。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode result = head.next;
        head.next = swapPairs(result.next);
        result.next = head;
        return result;
    }

    /**
     * 237. 删除链表中的节点
     * 请编写一个函数，使其可以删除某个链表中给定的（非末尾的）节点，您将只被给予要求被删除的节点。
     * 比如：假设该链表为 1 -> 2 -> 3 -> 4  ，给定您的为该链表中值为 3 的第三个节点，
     * 那么在调用了您的函数之后，该链表则应变成 1 -> 2 -> 4 。
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 725. Split Linked List in Parts
     * Given a (singly) linked list with head node root,
     * write a function to split the linked list into k consecutive linked list "parts".
     * The length of each part should be as equal as possible:
     * no two parts should have a size differing by more than 1.
     * This may lead to some parts being null.
     * The parts should be in order of occurrence in the input list,
     * and parts occurring earlier should always have a size greater
     * than or equal parts occurring later.
     * Return a List of ListNode's representing the linked list parts that are formed.
     * <p>
     * Examples 1->2->3->4, k = 5 // 5 equal parts [ [1], [2], [3], [4], null ]
     * Example 1:
     * Input:
     * root = [1, 2, 3], k = 5
     * Output: [[1],[2],[3],[],[]]
     * Explanation:
     * The input and each element of the output are ListNodes, not arrays.
     * For example, the input root has root.val = 1, root.next.val = 2, \root.next.next.val = 3, and root.next.next.next = null.
     * The first element output[0] has output[0].val = 1, output[0].next = null.
     * The last element output[4] is null, but it's string representation as a ListNode is [].
     * Example 2:
     * Input:
     * root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
     * Output: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
     * Explanation:
     * The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.
     * Note:
     * The length of root will be in the range [0, 1000].
     * Each value of a node in the input will be an integer in the range [0, 999].
     * k will be an integer in the range [1, 50].
     *
     * @param root
     * @param k
     * @return
     */
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] list = new ListNode[k];
        int len = 0;
        ListNode cursor = root;
        int base = 0;
        int leftover = 0;
        // count the length
        while (cursor != null) {
            cursor = cursor.next;
            len++;
        }
        // calculate the base and leftover
        base = len / k;
        leftover = len % k;
        cursor = root;
        // iterate each group
        for (int i = 0; i < k; i++) {
            list[i] = cursor; // save this group head
            ListNode tail = null;
            int groupSize = base;
            // set up correct group size
            if (leftover > 0) {
                groupSize++;
                leftover--;
            }
            // iterate this group nodes
            for (int j = 0; j < groupSize; j++) {
                if (j == groupSize - 1) // approach to the end of this group
                    tail = cursor;
                cursor = cursor.next;
            }
            if (groupSize > 0) // link this group tail to null
                tail.next = null;
        }
        return list;
    }

}
