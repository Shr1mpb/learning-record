package LeetCodeHot;

import LeetCodeHot.hot25.ListNode;

import java.util.HashSet;
import java.util.Set;

public class LeetCodeHot26 {
    /**
     * 环形链表II
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     * 不允许修改 链表。
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null){
            return null;
        }
        if (head.next == head) {
            return head;
        }
        ListNode temp = head;
        Set<ListNode> hashSet = new HashSet<>();
        while (temp != null) {
            if (hashSet.contains(temp)) {
                return temp;
            }
            hashSet.add(temp);
            temp = temp.next;
        }
        return null;
    }
    //快一些的，与上一个类似，fast == slow相当于已进了环
    public ListNode detectCycle2(ListNode head) {
        if(head==null||head.next==null){
            return null;
        }
        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(slow==fast){
                //进环后 由画图得 从头开始的指针与slow同时走
                //走到相遇，就是进入节点
                while(head!=slow){
                    slow=slow.next;
                    head=head.next;
                }
                return slow;
            }
        }
        return null;
    }
}
