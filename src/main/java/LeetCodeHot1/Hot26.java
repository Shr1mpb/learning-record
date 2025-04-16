package LeetCodeHot1;

import LeetCodeHot.hot25.ListNode;

public class Hot26 {
    // 环形链表 II
    // 弗洛伊德龟兔赛跑 相遇和头同时走 走到重合就是进入环的点
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;

        ListNode slow = head;
        ListNode fast = head;

        boolean isCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                isCycle = true;
                break;
            }
        }

        if (!isCycle) {
            return null;
        }

        while (head != slow) {
            head = head.next;
            slow = slow.next;
        }

        return head;
    }
}
