package LeetCodeHot;

import LeetCodeHot.hot24.ListNode;
import LeetCodeHot.hot32.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCodeHot32 {
    /**
     *  随机链表的复制
     *  给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
     * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
     * 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。
     * 复制链表中的指针都不应指向原链表中的节点 。
     * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
     * 返回复制链表的头节点。
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        //先深拷贝这个链表 不带random 深拷贝后的链表的头结点叫head0
        Node head0 = new Node(head.val);
        Node ptr = head0;
        Node curr = head.next;
        while (curr != null) {
            ptr.next = new Node(curr.val);
            curr = curr.next;
            ptr = ptr.next;
        }
        //使用集合记录之前链表的每一个random指向的序号
        // prevMap中存的是节点-编号，相当于给每个节点都编了序号(从0开始)
        Map<Node,Integer> prevMap = new HashMap<>();
        Node temp = head;
        int currIndex = 0;
        while (temp != null) {
            prevMap.put(temp, currIndex++);
            temp = temp.next;
        }
        //会有null的情况 再添加一个null
        prevMap.put(null, -1);
        //indexList中存的是链表的random都指向的是第几个节点
        List<Integer> indexList = new ArrayList<>();
        while (head != null) {
            indexList.add(prevMap.get(head.random));
            head = head.next;
        }

        //记录完成后 开始链接新表
        //先把新链表放在list里面 相当于知道了编号对应的Node
        List<Node> newList = new ArrayList<>();
        Node head01 = head0;
        while (head01 != null) {
            newList.add(head01);
            head01 = head01.next;
        }
        //再开始根据indexList来设置新链表的每个random
        Node head02 = head0;
        int nowIndex = 0;
        while (head02 != null) {
            Integer num = indexList.get(nowIndex++);
            if (num == -1) {
                head02.random = null;
            }else{
                head02.random = newList.get(num);
            }
            head02 = head02.next;
        }


        return head0;
    }


    Map<Node, Node> node_pair = new HashMap<>();
    //法2 ： 递归调用 逐个放置
    public Node copyRandomList2(Node head) {
        if (head == null){
            return null;
        }

        if (!node_pair.containsKey(head)) {
            Node new_head = new Node(head.val);
            node_pair.put(head, new_head);
            new_head.next = copyRandomList2(head.next);
            new_head.random = copyRandomList2(head.random);
        }
        return node_pair.get(head);
    }

}
