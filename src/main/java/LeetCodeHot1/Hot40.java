package LeetCodeHot1;

import homework.oop.tree.TreeNode;

public class Hot40 {
    // 二叉树的直径
    int ans0 = 0;
    public int diameterOfBinaryTree0(TreeNode root) {
        if (root == null) {
            return ans0;
        }
        setMaxDepth(root);
        return ans0;
    }

    private void setMaxDepth(TreeNode root) {
        if (root == null) {
            return;
        }
        int ld = maxDepth(root.left);
        int rd = maxDepth(root.right);
        ans0 = Math.max(ans0, ld + rd);
        setMaxDepth(root.left);
        setMaxDepth(root.right);
    }
    private int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    // 优化后 上面方法的setMaxDepth没必要 因为在求最大深度的时候已经可以设置ans = Math.max(ans,L+R+1)
    int ans;
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
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
