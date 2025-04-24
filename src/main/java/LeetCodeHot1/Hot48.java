package LeetCodeHot1;

import homework.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Hot48 {
    // 路径总和 III
    public int pathSum(TreeNode root, long targetSum) {
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 1); // 为了处理边界情况，比如当前路径和恰好等于 targetSum
        int sum = 0;
        return dfs(map, root, sum, targetSum); // 进行深度优先搜索
    }

    // 前缀和方法
    private int dfs(Map<Long, Integer> map, TreeNode node, long sum, long targetSum) {
        if (node == null) {
            return 0; // 如果节点为空，返回0
        }

        sum += node.val; // 更新当前路径的和
        int ret = map.getOrDefault(sum - targetSum, 0); // 找到满足条件的路径数
        map.put(sum, map.getOrDefault(sum, 0) + 1); // 更新当前路径和的计数

        // 递归到左右子节点
        ret += dfs(map, node.left, sum, targetSum);
        ret += dfs(map, node.right, sum, targetSum);

        map.put(sum, map.getOrDefault(sum, 0) - 1); // 回溯，撤销当前节点的路径和计数 避免影响其他路径
        return ret; // 返回结果
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.right.right = new TreeNode(11);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(1);

        Hot48 hot48 = new Hot48();
        Hot36 hot36 = new Hot36();
        System.out.println(hot36.inorderTraversal(root));
        System.out.println(hot48.pathSum(root, 8));
    }
}
