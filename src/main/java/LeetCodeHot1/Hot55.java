package LeetCodeHot1;

import java.util.ArrayList;
import java.util.List;

public class Hot55 {
    // 全排列
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> cur = new ArrayList<>();
        backtracking(res, cur, nums);
        return res;
    }

    private static void backtracking(List<List<Integer>> res, List<Integer> cur, int[] nums) {
        if (cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (cur.contains(nums[i])) {
                continue;
            }
            cur.add(nums[i]);
            backtracking(res, cur, nums);
            cur.remove(cur.size() - 1);
        }
    }
}
