package LeetCodeHot1;

import homework.oop.tree.TreeNode;

public class Hot37 {
    // 二叉树的最大深度
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
