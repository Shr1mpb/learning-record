package LeetCodeHot;

import LeetCodeHot.hot24.ListNode;

import java.util.Stack;

public class LeetCodeHot24 {
    /**
     * 回文链表
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
     *
     * @param head
     * @return O(n)+O(n)
     */
    public boolean isPalindrome(ListNode head) {
        //只有一个节点
        if (head != null && head.next == null) {
            return true;
        }
        //多个节点
        //思路：先全放入栈中，再遍历看看有无
        Stack<ListNode> stack = new Stack();
        ListNode temp = head;
        while (temp != null) {
            stack.add(temp);
            temp = temp.next;
        }
        int huiWenSize = stack.size() / 2;
        for (int i = 0; i < huiWenSize; i++) {
            if (head.val != stack.pop().val) {
                return false;
            }
            head = head.next;
        }
        return true;

    }
    //空间复杂度O(1)的办法
    //快慢指针： 慢指针一次走一步，快指针一次走两步 找到中间位置，然后翻转
    //翻转后看看是否回文 回文判断好后再把链表翻转回来(这里不翻转也能过，但是程序调用者一般不希望修改原始的数据)
    //局限性：由于没有使用额外的空间，这里对一个链表操作的时候需要加锁
}



