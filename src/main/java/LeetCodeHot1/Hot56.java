package LeetCodeHot1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Hot56 {
    // 子集

    // 1.回溯后去重 内存超限
    public List<List<Integer>> subsets0(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backTracking0(res, nums, new ArrayList<>());
        // 对res去重
        res.forEach(list -> list.sort(Integer::compareTo));
        HashSet<List<Integer>> set = new HashSet();
        for (List<Integer> list : res) {
            set.add(list);
        }
        return new ArrayList<>(set);
    }

    private void backTracking0(List<List<Integer>> res, int[] nums, List<Integer> cur) {
        // 这里每一步都要加上 但会重复
        res.add(new ArrayList<>(cur));

        // 开始回溯
        for (int num : nums) {
            if (cur.contains(num)) {
                continue;
            }
            cur.add(num);
            backTracking0(res, nums, cur);
            cur.remove(cur.size() - 1);
        }
    }

    // 2.回溯时直接天然去重
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backTracking(res, nums, 0, new ArrayList<>());
        return res;
    }

    private void backTracking(List<List<Integer>> res, int[] nums, int index, List<Integer> cur) {
        // 这里每一步都要加上
        res.add(new ArrayList<>(cur));

        // 开始回溯
        for (int i = index; i < nums.length; i++) {
            cur.add(nums[i]);
            backTracking(res, nums, i+1, cur);
            cur.remove(cur.size() - 1);
        }
    }

}
