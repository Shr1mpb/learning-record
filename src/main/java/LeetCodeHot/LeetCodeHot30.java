package LeetCodeHot;

import LeetCodeHot.hot24.ListNode;

public class LeetCodeHot30 {


    /**
     * 两两交换链表中的节点
     *给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {//0或1个节点的情况
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        //先处理完第一组
        fast = fast.next;
        slow.next = fast.next;
        fast.next = slow;
        head = fast;//头已变为fast

        if (slow.next == null || slow.next.next == null) {
            return head;
        }
        fast = slow.next.next;
        int count = 0;
        while (count < 2) {
            if (count == 0) {
                swapListNode(slow, fast);
                fast = fast.next;
            }
            slow = slow.next;
            fast = fast.next;
            if (fast == null) {
                break;
            }
            count++;
            if (count == 2) {
                count = 0;
            }
        }



        return head;

    }


    //优化第一个的写法 不用直接先处理头
    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {//0或1个节点的情况
            return head;
        }
        ListNode slow = new ListNode(-1, head);
        ListNode fast = head.next;
        head = fast;
        int count = 0;
        while (count < 2) {
            if (count == 0) {
                swapListNode(slow, fast);
                fast = fast.next;
            }
            slow = slow.next;
            fast = fast.next;
            if (fast == null) {
                break;
            }
            count++;
            if (count == 2) {
                count = 0;
            }
        }

        return head;

    }

    private void swapListNode(ListNode firstPre, ListNode next) {
        ListNode first = firstPre.next;
        first.next = next.next;
        next.next = first;
        firstPre.next = next;
    }


    //递归实现
    public ListNode swapPairs3(ListNode head) {
        //没有或只有一个 无法交换 直接返回头结点作为新的头
        if (head == null || head.next == null) {
            return head;
        }
        //有两个 开始交换
        ListNode newHead = head.next;
        head.next = swapPairs3(newHead.next);
        newHead.next = head;

        return newHead;

    }


}
