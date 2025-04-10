package LeetCodeHot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCodeHot55 {
    /**
     * 全排列
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     */

    //第一个回溯题 看一下题解 理解
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        backtracking(res, cur, nums);
        return res;
    }

    private static void backtracking(List<List<Integer>> res, List<Integer> cur, int[] nums) {
        // 终止条件
        if (cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        // 处理逻辑
        for (int num : nums) {
            if (!cur.contains(num)) {
                cur.add(num);
                backtracking(res, cur, nums);
                cur.remove(cur.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> permute = LeetCodeHot55.permute(nums);
        System.out.println();
    }
}
/*
void backtracking(参数) {
    if (终止条件) {
        存放结果;
        return;
    }
    for (选择 : 本层集合中的元素) {
        处理节点;
        backtracking(路径, 选择列表); // 递归
        撤销处理; // 回溯
    }
}
 */
