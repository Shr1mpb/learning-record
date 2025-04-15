package LeetCodeHot1;

import LeetCodeHot.hot25.ListNode;

public class Hot26 {
    // 环形链表 II
    public ListNode detectCycle(ListNode head) {
        // 这次要返回链表的位置 依然采用Floyd龟兔赛跑法
        ListNode slow = head;
        ListNode fast = head.next;
        boolean isCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                isCycle = true;
                break;
            }
        }
        // 没有环 返回null
        if (!isCycle) {
            return null;
        }
        // 有环 返回进入的位置
        // 相遇位置和头位置开始 都移动一步 相遇即进入环的节点
        while (head != slow) {
            head = head.next;
        }
        return head;

    }
}
