package LeetCodeHot1;

import LeetCodeHot.hot25.ListNode;

public class Hot25 {
    // 环形链表
    public boolean hasCycle(ListNode head) {
        // 判断是否存在环形链表 用龟兔赛跑法
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;

    }
}
