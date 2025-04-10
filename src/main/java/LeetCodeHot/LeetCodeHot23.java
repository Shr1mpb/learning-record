package LeetCodeHot;

import LeetCodeHot.hot23.ListNode;


import java.util.Stack;

public class LeetCodeHot23 {
    /**
     * 反转链表
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     * @param head 头结点
     * @return
     */
    public ListNode reverseList(ListNode head) {
        Stack<ListNode> stack = new Stack();
        while (head != null) {
            stack.add(head);
            head = head.next;
        }
        if (stack.isEmpty()) {
            return null;
        }
        head = stack.pop();
        ListNode temp = head;
        while (!stack.isEmpty()) {
            ListNode pop = stack.pop();
            temp.next = pop;
            temp = temp.next;
        }
        temp.next = null;
        return head;
    }

    //更加直接的翻转
    public ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr!=null){
            //互换位置写法
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;//迭代
        }
        return prev;
    }

}

