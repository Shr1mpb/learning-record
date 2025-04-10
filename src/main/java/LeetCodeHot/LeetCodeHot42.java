package LeetCodeHot;

import homework.tree.TreeNode;

public class LeetCodeHot42 {
    /**
     * 将有序数组转换为二叉搜索树
     * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 平衡 二叉搜索树。
     */
    //这个我不会 看了看教程 二叉搜索树的中序遍历是升序队列，但是可以确定的二叉搜索树很多
    //要求二叉搜索树平衡 可以考虑用中间节点(除不尽就是左边)作为根 这样左右子就差0/1 可以保持平衡
    //中间节点选择后，分为两边，递归调用接入节点，每次接入的都是两边中间的数 出口是left>right 即接完
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 总是选择中间位置左边的数字作为根节点
        int mid = (left + right) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }


}
