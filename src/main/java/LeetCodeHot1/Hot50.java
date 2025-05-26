package LeetCodeHot1;

import homework.oop.tree.TreeNode;

public class Hot50 {// 二叉树中的最大路径和
    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        // 包含当前节点的最大路径和（递归遍历每个节点，我们取其中的最大值）
        containCurNodeMaxSum(root);
        return max;
    }

    private int containCurNodeMaxSum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 在左子树中，包含左孩子节点的最大路径和
        int leftMax = containCurNodeMaxSum(node.left);
        // 在右子树中，包含右孩子节点的最大路径和
        int rightMax = containCurNodeMaxSum(node.right);
        // 再往上返回的时候，路径是：要么选左 / 要么选右 / ！！要么只有根节点
        int curMax = Math.max(Math.max(node.val + leftMax, node.val + rightMax), node.val);
        // 但是我们和最大路径和比较的时候，会多一种情况：既有左又有右（但是这种路径是没法往上层递归返回的，因为此路径已经结束了）
        max = Math.max(node.val + leftMax + rightMax, max);
        return Math.max(curMax,0);
    }

}
