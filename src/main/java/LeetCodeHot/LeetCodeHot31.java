package LeetCodeHot;

import LeetCodeHot.hot24.ListNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCodeHot31 {
    /**
     * k个一组翻转链表
     * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || (head.next == null && k >= 1)) {
                return head;
        }
        ListNode slow = new ListNode(-1, head);
        ListNode fast = head;
        int temp0 = k;
        while (fast != null && temp0 != 1) {
            fast = fast.next;
            temp0--;
        }
        if (temp0 != 1) {
            return head;
        }
        int temp = k;
        int count = 0;
        while (temp != 0 && fast != null) {
            if (temp == k) {
                List<ListNode> list = new ArrayList<>(k);
                ListNode ptr1 = slow.next;
                while (ptr1 != fast.next) {//存储这一段的链表节点
                    list.add(ptr1);
                    ptr1 = ptr1.next;
                }
                //翻转
                list.getFirst().next = fast.next;
                ListNode tempSlow = slow;
                for (int i = 0; i < k; i++) {
                    tempSlow.next = list.get(k - i - 1);
                    tempSlow = tempSlow.next;
                }

                if (count == 0) {//第一次翻转后获得翻转以后的头
                    head = list.get(k - 1);
                    count++;
                }
                //翻转后明确slow和fast指向
                //slow没动过 所以明确fast
                fast = list.getFirst();

            }
            temp--;
            if (temp == 0) {
                temp = k;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return head;

    }
    public static ListNode reverseKGroup2(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head), prev = dummy;
        while (true) {
            // 检查剩余节点是否有k个，不足则返回
            ListNode last = prev;
            for (int i = 0; i < k; i++) {
                last = last.next;
                if (last == null) {
                    return dummy.next;
                }
            }

            // 翻转k个节点 这里要学习一下翻转的思路 C在一直后移 让c后面的元素接到p后即可 相当于每次接一个后面的 接k-1次即翻转
            // 看成插入排序 每次把curr后面的插入到prev后
            ListNode curr = prev.next, next;
            for (int i = 0; i < k - 1; i++) {
                next = curr.next;
                curr.next = next.next;
                next.next = prev.next;
                prev.next = next;
            }
            prev = curr;
        }
    }

    public static void main(String[] args) {
        ListNode ptr0 = new ListNode(1, null);
        ListNode ptr1 = new ListNode(2, null);
        ListNode ptr2 = new ListNode(3, null);
        ListNode ptr3 = new ListNode(4, null);
        ListNode ptr4 = new ListNode(5, null);
        ptr0.next = ptr1;
        ptr1.next = ptr2;
        ptr2.next = ptr3;
        ptr3.next = ptr4;
        LeetCodeHot31 leetCodeHot31 = new LeetCodeHot31();
        ListNode listNode = leetCodeHot31.reverseKGroup(ptr0, 3);
        while (listNode != null) {
            System.out.print(listNode.val);
            listNode = listNode.next;
            if (listNode != null) {
                System.out.print(" , ");
            }
        }

    }
}
