package LeetCodeHot1;

import LeetCodeHot.hot25.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Hot34 {
    // 合并 K 个升序链表 两两合并
    public ListNode mergeKLists0(ListNode[] lists) {
        ListNode ans = null;
        for (ListNode list : lists) {
            ans = mergeTwoLists(ans, list);
        }
        return ans;
    }
    // PQ
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode h : lists) {
            if (h != null) {
                queue.offer(h);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (!queue.isEmpty()) {
            ListNode minNode = queue.poll();
            cur.next = minNode;
            cur = cur.next;
            if (minNode.next != null) {
                queue.offer(minNode.next);
            }
        }

        return dummy.next;

    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }
}
