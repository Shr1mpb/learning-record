package LeetCodeHot1;

import LeetCodeHot.hot25.ListNode;

public class Hot30 {
    // 两两交换链表中的节点
    public ListNode swapPairs(ListNode head) {
        // 递归思想
        if (head == null || head.next == null) return head;// 新的一对没有或者只有一个 直接返回 连接即可
        // 有两个 交换，然后拼接下面的
        ListNode next = head.next.next;
        ListNode pre = head;
        ListNode nex = head.next;
        pre.next = swapPairs(next);
        nex.next = pre;
        return nex;
    }
}
