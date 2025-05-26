package LeetCodeHot1;

import homework.oop.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Hot36 {
    // 二叉树的中序遍历

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inOrder(root, res);
        return res;
    }

    private void inOrder(TreeNode root,List<Integer> res) {
        if (root == null) {
            return;
        }
        inOrder(root.left, res);
        res.add(root.val);
        inOrder(root.right, res);
    }

}
