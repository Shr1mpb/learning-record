package LeetCodeHot1;

import LeetCodeHot.hot25.ListNode;

public class Hot31 {
    // K 个一组翻转链表
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        // 开始循环
        while (true){
            // 后面的节点不足k个 可以返回了
            ListNode last = pre;
            for (int i = 0; i < k; i++) {
                last = last.next;
                if (last == null) {
                    return dummy.next;
                }
            }
            // 后面的节点有k个 翻转
            // cur不变但每次都向后移 头插法 把cur的下一个节点接在头上
            ListNode cur = pre.next;
            ListNode next;
            for (int i = 0; i < k - 1; i++) {
                next = cur.next;
                cur.next = next.next;
                next.next = pre.next;
                pre.next = next;
            }
            pre = cur;

        }
    }
}
