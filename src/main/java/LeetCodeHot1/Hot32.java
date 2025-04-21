package LeetCodeHot1;

import LeetCodeHot.hot32.Node;

import java.util.HashMap;
import java.util.Map;

public class Hot32 {
    // 随机链表的复制
    Map<Node, Node> node_pair = new HashMap<>();
    public Node copyRandomList(Node head) {
        if (head == null){
            return null;
        }
        // 通过node_pair去重，递归实现复制
        if (!node_pair.containsKey(head)) {
            Node new_head = new Node(head.val);
            node_pair.put(head, new_head);
            new_head.next = copyRandomList(head.next);
            new_head.random = copyRandomList(head.random);
        }
        return node_pair.get(head);
    }
}
