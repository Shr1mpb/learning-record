package LeetCodeHot1;

import LeetCodeHot.hot22.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Hot22 {
    // 相交链表
    public ListNode getIntersectionNode0(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    // 优化后的快速方法
    // 都到最后，看看长链和短链的长度
    // 长链长度 + 长 - 短 ，然后一起移动 就到了相交的地方
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int l1 = 0;
        int l2 =0;
        ListNode p = headA;
        ListNode q = headB;
        while(p!=null){
            l1++;
            p = p.next;
        }
        while(q!=null){
            l2++;
            q = q.next;
        }
        ListNode big = l1>=l2?headA:headB;
        ListNode small = big==headA? headB:headA;
        for(int i = 0; i<Math.abs(l1-l2);i++){
            big = big.next;
        }
        while(big!=null && small!=null && big!=small){
            big = big.next;
            small = small.next;
        }
        return big;
    }
}
