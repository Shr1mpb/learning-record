package LeetCodeHot;

import homework.oop.tree.TreeNode;


public class LeetCodeHot40 {
    /**
     * 二叉树的直径
     * 给你一棵二叉树的根节点，返回该树的 直径 。
     * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
     * 两节点之间路径的 长度 由它们之间边数表示。
     */
    //思路：使用求深度的方法，相当于求出了每个节点的最长路径，再用ans随时更新
    int ans;
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;//ans是直径上的最大节点数
        depth(root);
        return ans - 1;
    }
    public int depth(TreeNode node) {
        if (node == null) {
            return 0; // 访问到空节点了，返回0
        }
        int L = depth(node.left); // 左儿子为根的子树的深度
        int R = depth(node.right); // 右儿子为根的子树的深度
        ans = Math.max(ans, L+R+1); // 计算d_node即L+R+1 并更新ans
        return Math.max(L, R) + 1; // 返回该节点为根的子树的深度
    }



}
