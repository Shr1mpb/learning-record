package LeetCodeHot;

import LeetCodeHot.hot22.ListNode;

import java.util.*;

public class LeetCodeHot22 {
    /**
     * 给你两个单链表的头节点 headA 和 headB ，
     * 请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
     */
    //我的方法：相当于遍历，用hashset看看有没有一样，如果都能添加进去，则返回null
    //如果有添加不进去的，就返回这个节点
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == headB) {
            return headA;
        }
        if (headA == null || headB == null) {
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        set.add(headA);
        ListNode temp = headA;
        while (temp.next != null) {
            temp = temp.next;
            set.add(temp);
        }
        if (!set.add(headB)) {
            return headB;
        }
        temp = headB;
        while (temp.next != null) {
            temp = temp.next;
            if (!set.add(temp)) {
                return temp;
            }
        }
        return null;
    }

    //和第一个一样
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet();
        set.add(headA);
        ListNode temp = headA;
        while (temp.next != null) {
            temp = temp.next;
            set.add(temp);
        }
        if (set.contains(headB)) {
            return headB;
        }
        temp = headB;
        while (temp.next != null) {
            temp = temp.next;
            if (set.contains(temp)) {
                return temp;
            }
        }
        return null;
    }

    //双指针
    //规律： A链m个 B链n个
    //假设A链相交前节点个数为a，B为b，相交后c;则 a+c = m;b+c = n
    /*
    相交：
        如果 a=b，则两个指针会同时到达两个链表相交的节点，此时返回相交的节点；
        如果 a不等于b，
            指针 pA 会遍历完链表 headA，
            指针 pB 会遍历完链表 headB，两个指针不会同时到达链表的尾节点，
            然后指针 pA 移到链表 headB 的头节点，指针 pB 移到链表 headA 的头节点，
            然后两个指针继续移动，在指针 pA 移动了 a+c+ b 次、指针 pB 移动了 b+c+ a 次之后，
            两个指针会同时到达两个链表相交的节点，该节点也是两个指针第一次同时指向的节点，此时返回相交的节点。
    不相交：
        m = n，同时空
        m 不等于 n，则a遍历完指向b，b遍历完指向a，都遍历m+n次时，都变成null

     */
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;
        while(pA !=  pB){
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }
        return pA;
}

}


