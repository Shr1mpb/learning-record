package LeetCodeHot1;

import homework.oop.tree.TreeNode;

public class Hot43 {
    // 验证二叉搜索树
    // 第一次做又错了 这里大要大过全部的上面的节点 而不是大过自己这里的就可以
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(6);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(7);
        Hot43 hot43 = new Hot43();
        System.out.println(hot43.isValidBST(root));

    }
}
