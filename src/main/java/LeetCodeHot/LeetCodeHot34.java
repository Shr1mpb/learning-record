package LeetCodeHot;

import LeetCodeHot.hot24.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LeetCodeHot34 {
    /**
     *  合并 K 个升序链表
     *  给你一个链表数组，每个链表都已经按升序排列。
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     */
    //这位更是重量级 困难，直接PriorityQueue看看能不能解决
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
        //所有都添加到优先队列中
        //相当于已经升序排序
        for (ListNode listNode : lists) {
            while (listNode != null) {
                priorityQueue.add(listNode);
                listNode = listNode.next;
            }
        }
        //连起来
        ListNode newHead = priorityQueue.poll();
        ListNode ret = newHead;
        if (newHead != null) {
            while (!priorityQueue.isEmpty()) {
                newHead.next = priorityQueue.poll();
                newHead = newHead.next;
            }
        }
        //最后一个元素的下一个元素置空
        if (newHead != null) {
            newHead.next = null;
        }

        return ret;
    }
}
