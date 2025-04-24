package LeetCodeHot1;

import homework.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Hot46 {
    // 二叉树展开为链表
    Queue<TreeNode> q = new LinkedList<>();
    public void flatten0(TreeNode root) {
        inOrder(root);
        q.poll();
        while (!q.isEmpty()) {
            TreeNode poll = q.poll();
            root.left = null;
            root.right = poll;
            root = root.right;
        }
    }
    private void inOrder(TreeNode root) {
        if (root == null) return;
        q.offer(root);
        inOrder(root.left);
        inOrder(root.right);
    }

    // 优化： 逆向构建
    // 先展开右子 处理后pre变成右子已展开的根节点
    // 再展开左子 左子连接pre 即连上右子 pre变成左子
    // 最后，根节点连上左子 完成构建
    TreeNode pre = null;
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.right);
        flatten(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }
}
