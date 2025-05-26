package LeetCodeHot1;

import homework.oop.tree.TreeNode;

public class Hot44 {
    // 二叉搜索树中第 K 小的元素
    private int count = 0;
    private int res = -1;

    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return res;
    }

    private void inorder(TreeNode node, int k) {
        if (node == null)
            return;

        inorder(node.left, k);

        // 访问当前结点
        count++; // 增加已访问的节点计数
        if (count == k) {
            res = node.val;
            return;
        }
        inorder(node.right, k);
    }

    public static void main(String[] args) {
        Hot44 hot44 = new Hot44();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);

        System.out.println(hot44.kthSmallest(root, 3));
    }
}
