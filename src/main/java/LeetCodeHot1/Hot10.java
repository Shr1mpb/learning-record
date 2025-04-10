package LeetCodeHot1;

import java.util.HashMap;
import java.util.Map;

public class Hot10 {
    // 和为 K 的子数组
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int res = 0;
        int ps = 0;
        for(int num: nums){
            ps += num;
            res += map.getOrDefault(ps - k, 0);// i~j和为k 即当前 前缀和1(当前) - 前缀和2 = k
            map.put(ps, map.getOrDefault(ps, 0) + 1);
        }
        return res;
    }
}

