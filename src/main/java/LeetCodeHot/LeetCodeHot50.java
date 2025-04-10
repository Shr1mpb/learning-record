package LeetCodeHot;


import homework.tree.TreeNode;

public class LeetCodeHot50 {
    /**
     * 二叉树中的最大路径和
     * 二叉树中的 路径 被定义为一条节点序列，
     * 序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。
     * 该路径 至少包含一个 节点，且不一定经过根节点。
     * 路径和 是路径中各节点值的总和。
     * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
     */
    //我感觉这个题类似那个Dijkstra算法，dijkstra是找最短，它是找最长
    //但是dijkstra是单源的，这个不清楚是什么情况
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
