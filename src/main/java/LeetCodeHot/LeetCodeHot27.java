package LeetCodeHot;

import LeetCodeHot.hot24.ListNode;

import java.util.List;

public class LeetCodeHot27 {
    /**
     * 合并两个有序链表
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode ret;
        if (list1 == null && list2 == null) {
            return null;
        }
        if (!(list1 == null || list2 == null)) {
            if (list1.val < list2.val) {
                ret = list1;
                list1 = list1.next;
            }else {
                ret = list2;
                list2 = list2.next;
            }
        }else {
            if (list1 == null) {
                ret = list2;
                list2 = list2.next;
            }else{
                ret = list1;
                list1 = list1.next;
            }
        }

        ListNode temp = ret;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }
        if (list1 == null) {
            while (list2 != null) {
                temp.next = list2;
                temp = temp.next;
                list2 = list2.next;
            }
        }else{
            while (list1 != null) {
                temp.next = list1;
                temp = temp.next;
                list1 = list1.next;
            }
        }
        return ret;
    }
    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        ListNode cur1 = list1,cur2 = list2;
        ListNode dummy = new ListNode(0,null);
        ListNode cur = dummy;

        while(cur1 != null && cur2 != null){
            if(cur1.val <= cur2.val){
                cur.next = cur1;
                cur1 = cur1.next;
            }else{
                cur.next = cur2;
                cur2 = cur2.next;
            }

            cur = cur.next;
        }

        //直接拼接剩余链表
        cur.next = cur1 == null?cur2:cur1;

        return dummy.next;
    }
}
