package LeetCodeHot1;

import LeetCodeHot.hot23.ListNode;

import java.util.*;

public class Hot23 {
    // 反转链表
    public ListNode reverseList0(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        // 法一： 存储后翻转
        Deque<ListNode> list = new LinkedList<>();
        while (head != null && head.next != null) {
            list.add(head);
            head = head.next;
        }
        ListNode res = head;
        head.next = list.pollLast();
        head = head.next;
        while (!list.isEmpty()) {
            head.next = list.pollLast();
            head = head.next;
        }
        head.next = null;
        return res;
    }

    // 法二 双指针翻转
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        Hot23 hot23 = new Hot23();
        ListNode res = hot23.reverseList(head);
    }

}
