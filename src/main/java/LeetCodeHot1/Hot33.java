package LeetCodeHot1;

import LeetCodeHot.hot25.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Hot33 {
    // 排序链表
    // 1.PQ法 直接全放进pq 然后再重新连接起来
    public ListNode sortList0(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
        while (head != null) {
            priorityQueue.add(head);
            head = head.next;
        }
        ListNode ret = priorityQueue.poll();
        ListNode temp = ret;
        while (!priorityQueue.isEmpty()) {
            temp.next = priorityQueue.poll();
            temp = temp.next;
        }
        temp.next = null;
        return ret;
    }

    // 2.手写归并
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode fast = head.next, slow = head;
        // 找到中间节点 二分整个链表
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 记录tmp 把前半部分链表和后半部分链表分隔开 (slow.next = null)
        ListNode tmp = slow.next;
        slow.next = null;
        // 递归处理
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        ListNode h = new ListNode(0);
        ListNode res = h;
        // 合并
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left != null ? left : right;
        return res.next;
    }



}
