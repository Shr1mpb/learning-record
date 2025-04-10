package LeetCodeHot;

import LeetCodeHot.hot24.ListNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCodeHot29 {
    /**
     * 删除链表的倒数第 N 个结点
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode cur = head;
        List<ListNode> listNodes = new ArrayList<>();
        while (cur != null) {
            listNodes.add(cur);
            cur = cur.next;
        }
        //删除倒数第n个节点
        //如果是头节点
        if (n == listNodes.size()) {
            head = head.next;
        }else{
            ListNode pre = listNodes.get(listNodes.size() - 1 - n);
            pre.next = pre.next.next;
        }

        return head;
    }

    //快慢指针 通过快指针让慢指针正好位于倒数第n个元素的前一个位置
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode res = slow;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        if (fast == null) return res.next;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return res;
    }
}
