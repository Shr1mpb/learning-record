package LeetCodeHot;

import homework.tree.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

public class LeetCodeHot46 {
    /**
     * 二叉树展开为链表
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     */

    //刚开始做的：
    private Queue<TreeNode> sort = new LinkedList<>();
    public void flatten(TreeNode root) {
        getSort(root);//先序遍历结果，放在sort中
        TreeNode cur = sort.poll();
        if (cur == null) {
            return;
        }
        //按先序遍历结果连接节点
        while (!sort.isEmpty()) {
            if (cur.left != null) {
                cur.left = null;
            }
            cur.right = sort.poll();
            cur = cur.right;
        }
        //最后处理一下最后一个节点
        if (cur != null) {
            if (cur.left != null) {
                cur.left = null;
            }
        }

    }

    //获取先序遍历结果
    private void getSort(TreeNode root) {
        if (root == null) return;
        sort.offer(root);
        getSort(root.left);
        getSort(root.right);
    }

}
