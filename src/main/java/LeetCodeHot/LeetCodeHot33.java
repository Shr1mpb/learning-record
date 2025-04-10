package LeetCodeHot;

import LeetCodeHot.hot24.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LeetCodeHot33 {
    /**
     * 排序链表
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     * @param head
     * @return
     */
    //这里只写了一个简单一些的算法 与普通的数组排序类似，还有快速排序、归并排序等各种快速一些的算法
    public ListNode sortList(ListNode head) {
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


}
