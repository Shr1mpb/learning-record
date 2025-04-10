package LeetCodeHot;

import java.util.*;

public class LeetCodeHot75 {
    /**
     * 前 K 个高频元素
     * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     */
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {// 存储频率
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 给频率排序 倒序
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        pq.addAll(map.entrySet());
        // 取出前k个并返回
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < k && !pq.isEmpty(); i++) {
            ret.add(pq.poll().getKey());
        }
        return ret.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        LeetCodeHot75 leetcode = new LeetCodeHot75();
        System.out.println(leetcode.topKFrequent(new int[]{1, 1, 1, 2, 3}, 2));
    }

}
