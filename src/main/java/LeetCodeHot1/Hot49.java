package LeetCodeHot1;

import homework.tree.TreeNode;

public class Hot49 {
    // 二叉树的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q)//根节点是之一 直接返回
            return root;
        /*
        递归左右子树：
            左子树返回值为 l，右子树返回值为 r。
            根据左右子树的返回值判断：
            如果左子树返回 null，说明 p 和 q 都在右子树中，返回右子树的结果。
            如果右子树返回 null，说明 p 和 q 都在左子树中，返回左子树的结果。
            如果左右子树都不为 null，说明当前节点就是最近公共祖先，返回当前节点。
         */
        TreeNode r = lowestCommonAncestor(root.right , p , q);
        TreeNode l = lowestCommonAncestor(root.left , p , q);
        return l == null ? r : (r == null ? l : root);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        Hot49 hot49 = new Hot49();
        TreeNode ans = hot49.lowestCommonAncestor(root, root.left, root.right);
    }
}
