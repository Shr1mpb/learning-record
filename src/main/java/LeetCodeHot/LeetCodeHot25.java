package LeetCodeHot;

import LeetCodeHot.hot25.ListNode;

import java.util.HashSet;
import java.util.Set;

public class LeetCodeHot25 {

    /**
     * 环形链表
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
     * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null){
            return false;
        }
        if (head.next == head) {
            return true;
        }
        ListNode temp = head;
        Set<ListNode> hashSet = new HashSet<>();
        while (temp != null) {
            if (hashSet.contains(temp)) {
                return true;
            }
            hashSet.add(temp);
            temp = temp.next;
        }
        return false;

    }

    //快一些的 快慢指针
    public boolean hasCycle2(ListNode head) {
        ListNode fast=head;
        ListNode slow=head;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                return true;
            }
        }
        return false;
    }
}
