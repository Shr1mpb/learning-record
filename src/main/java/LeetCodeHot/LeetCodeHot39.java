package LeetCodeHot;

import LeetCodeHot.hot24.ListNode;
import homework.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCodeHot39 {
    /**
     * 对称二叉树
     * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
     */
    //递归 检查左右子树是否对称
    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }




}
