package LeetCodeHot;

import homework.oop.tree.TreeNode;

import java.util.*;

public class LeetCodeHot44 {
    /**
     * 二叉搜索树中第 K 小的元素
     * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（从 1 开始计数）。
     */
    //思路：遍历二叉树，并使用优先队列，然后取出优先队列中序号为k-1的元素
    public int kthSmallest0(TreeNode root, int k) {
        //root天生不为null
        //这里使用BFS
        Queue<Integer> list = new PriorityQueue<>();
        Queue<TreeNode> tool = new LinkedList<>();
        tool.offer(root);
        while (!tool.isEmpty()) {
            TreeNode node = tool.poll();
            list.add(node.val);
            if (node.left != null) {
                tool.offer(node.left);
            }
            if (node.right != null) {
                tool.offer(node.right);
            }
        }
        int ret = list.poll();
        while (--k != 0) {
            ret = list.poll();
        }
        return ret;

    }

    //我服了 这是个搜索树 直接中序遍历以后返回就可以了
    //优化：中序遍历遍历到第k个就停下 然后返回
    private List<Integer> list = new ArrayList<>();
    public int kthSmallest(TreeNode root, int k) {
        inorderTraversal(root, k);
        return list.get(k - 1);
    }

    public void inorderTraversal(TreeNode root,int k) {
        if (root == null) return;
        if (list.size() == k) return;
        inorderTraversal(root.left, k);
        list.add(root.val);
        inorderTraversal(root.right, k);
    }
}
