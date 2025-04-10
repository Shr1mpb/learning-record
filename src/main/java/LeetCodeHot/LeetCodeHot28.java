package LeetCodeHot;

import LeetCodeHot.hot24.ListNode;

public class LeetCodeHot28 {
    /**
     * 两数相加
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //处理头
        ListNode head = new ListNode(0, null);
        boolean gtTen = false;
        int sum;
        ListNode cur = head;
        //处理剩余
        while (l1 != null && l2 != null) {
            sum = l1.val + l2.val;
            if (gtTen) {
                sum++;
            }
            gtTen = sum >= 10;
            cur.next = new ListNode(sum % 10, null);
            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        if (!gtTen){//未进位 直接拼接
            cur.next = (l1 == null) ? l2 : l1;
        }else{//进位
            if (l1 == null) {
                while (l2 != null) {
                    sum = l2.val;
                    if (gtTen) {
                        sum++;
                    }
                    gtTen = sum >= 10;
                    cur.next = new ListNode(sum % 10, null);
                    cur = cur.next;
                    l2 = l2.next;
                }
            }else{
                while (l1 != null) {
                    sum = l1.val;
                    if (gtTen) {
                        sum++;
                    }
                    gtTen = sum >= 10;
                    cur.next = new ListNode(sum % 10, null);
                    cur = cur.next;
                    l1 = l1.next;
                }
            }
        }
        if (gtTen) {
            cur.next = new ListNode(1, null);
        }
        return head.next;
    }


    //一样的方法，更好的写法
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        if (carry > 0)
            tail.next = new ListNode(carry);
        return head;
    }
}
