package LeetCodeHot;

import homework.oop.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class LeetCodeHot48 {
    /**
     * 路径总和 III
     * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
     * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     */
    /*
        思路：由于从父节点开始，那就深度优先遍历
        先把根节点算上，先加左节点，如果已超，那就加右节点，如果还超不等于，就回去父节点
        如果没超就继续向下加左节点，重复直到找到一条后，计数+1，返回，然后再向右试
     */
    public int pathSum(TreeNode root, long targetSum) {
        //大递归：对每个节点都求一次rootSum
            //先处理特殊情况
            if (root == null) {
                return 0;
            }
            //定义rootSum，表示从root开始和为target的路径的数目，然后对左右子递归，到求出
            int ret = rootSum(root, targetSum);
            ret += pathSum(root.left, targetSum);
            ret += pathSum(root.right, targetSum);
            return ret;
    }

    public int rootSum(TreeNode root, long targetSum) {
        //小递归 对当前节点求个数
        int ret = 0;
        if (root == null) {//出口
            return ret;
        }

        int val = root.val;
        if (val == targetSum) {
            ret++;
        }

        ret += rootSum(root.left, targetSum - val);//关键所在，递归时逐渐减少targetSum，就只要让递归到的节点的val等于即可
        ret += rootSum(root.right, targetSum - val);
        return ret;
    }



    //优化后的方法：用hashMap缩短用时
    public int pathSum2(TreeNode root, long targetSum) {
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 1); // 为了处理边界情况，比如当前路径和恰好等于 targetSum
        int sum = 0; // 当前路径的和
        return dfs(map, root, sum, targetSum); // 进行深度优先搜索
    }

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

        map.put(sum, map.getOrDefault(sum, 0) - 1); // 回溯，撤销当前节点的路径和计数
        return ret; // 返回结果
    }

}
