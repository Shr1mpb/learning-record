package LeetCodeHot1;

import LeetCodeHot.hot25.ListNode;

public class Hot29 {
    // 删除链表的倒数第 N 个结点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int count = 0; // 节点总数
        ListNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        // 处理特殊情况
        if (count == 0) {
            return null;
        }
        if (n == count) {
            return head.next;
        }
        ListNode ret = head;
        // 开始删去倒数第n个节点
        int index = count - n; // 要删去节点的索引
        int curIndex = 0;
        while (head != null) {
            curIndex++;
            if (curIndex == index) {
                if (head.next != null) {
                    ListNode t = head.next;
                    head.next = head.next.next;
                    t.next = null;
                } else {
                    head.next = null;
                }
                break;
            } else {
                head = head.next;
            }

        }
        return ret;


    }
}
