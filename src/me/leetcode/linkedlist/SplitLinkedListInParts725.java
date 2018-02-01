package me.leetcode.linkedlist;

/**
 * Given a (singly) linked list with head node root, write a function to split
 * the linked list into k consecutive linked list "parts".
 * 
 * The length of each part should be as equal as possible: no two parts should
 * have a size differing by more than 1. This may lead to some parts being null.
 * 
 * The parts should be in order of occurrence in the input list, and parts
 * occurring earlier should always have a size greater than or equal parts
 * occurring later.
 * 
 * Return a List of ListNode's representing the linked list parts that are
 * formed. 
 * Examples 1->2->3->4, k = 5 // 5 equal parts [ [1], [2], [3], [4], null ]
 * 
 * Example 1:
 * 
 * Input: root = [1, 2, 3], k = 5 Output: [[1],[2],[3],[],[]] 
 * Explanation: The input and each element of the output are ListNodes, not arrays. 
 * For example,the input root has root.val = 1, root.next.val = 2, root.next.next.val = 3, 
 * and root.next.next.next = null. 
 * The first element output[0] has output[0].val = 1, output[0].next = null. 
 * The last element output[4] is null, but it's string representation as a ListNode is [].
 * 
 * Example 2:
 * 
 * Input: root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3 Output: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]] 
 * Explanation: The input has been split into consecutive parts with size difference at most 1, 
 * and earlier parts are a larger size than the later parts.
 * 
 * Note: The length of root will be in the range [0, 1000]. 
 * Each value of a node in the input will be an integer in the range [0, 999]. 
 * k will be an integer in the range [1, 50].
 * 
 * 题目给了我们一个链表，还给了我们k，让我们把链表分成 k 个部分，使得每一个部分尽可能相等。 
 * 首先，我们要知道链表的长度 len，利用 len 和 k 来分组。 
 * 举例： 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10
 * 
 * len = 10; k = 7 的话； 首先计算 base = len / k = 10 / 7 = 1； 
 * 然后计算 leftover = len % k = 10 % 7 = 3； 
 * 先把每一个小组的base 值填入 [1, 1, 1, 1, 1, 1, 1] 一共有7个小组，每个小组目前有1个node
 * 然后把 leftover 分别加 1到每一个小组，从前到后，直到加完。 [2, 2, 2, 1, 1, 1, 1]
 * 这样的话，一共有7个小组，前三个小组，每一组有2个 nodes，后面4个小组，每一组有1个node。 
 * 这里写成 array 方便理解，答案中只需要知道 base， 然后把 leftover 加上就可以。
 * 
 * 所以根据上面的 array， 就可以把原链表分组为： [1 -> 2] [3 -> 4] [5 -> 6] [7] [8] [9] [10]
 * 
 */
public class SplitLinkedListInParts725 {
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

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
	this.val = val;
    }
}
