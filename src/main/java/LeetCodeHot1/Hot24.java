package LeetCodeHot1;

import LeetCodeHot.hot24.ListNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Hot24 {
    // 回文链表
    public boolean isPalindrome0(ListNode head) {
        // 存好再遍历
        Deque<ListNode> list = new LinkedList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        while (!list.isEmpty()) {
            ListNode left = list.pollFirst();
            ListNode right = list.pollLast();
            if (left != null && right != null && left.val != right.val) return false;
        }
        return true;

    }

    // 法2：翻转后再双指针 空间复杂度下降
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode second = reverse(slow);
        ListNode first = head;

        while(second != null){
            if(first.val != second.val) return false;

            first = first.next;
            second = second.next;
        }

        return true;





    }

    ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode cur = head;

        while(cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }

        return pre;
    }
}
