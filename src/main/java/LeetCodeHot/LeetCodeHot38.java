package LeetCodeHot;

import homework.oop.tree.TreeNode;

public class LeetCodeHot38 {
    /**
     *  翻转二叉树
     *  给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     */
    //想法：只要节点不为空 就交换左右节点 递归
    public TreeNode invertTree(TreeNode root) {
        if (root != null) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            invertTree(root.left);
            invertTree(root.right);
        }
        return root;
    }
}
