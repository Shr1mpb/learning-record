package LeetCodeHot;

import java.util.HashMap;
import java.util.Map;

public class LeetCodeHot10 {
    /**
     * 和为K的子数组
     * @param nums 整数数组
     * @param k 整数k
     * @return 返回该数组中和为k的子数组的个数 子数组是  ！！连续！！  的非空序列
     */

    //本人只能想到穷举O(n²) 因为没有说有顺序
    //参考题解做法 前缀和+哈希表优化
    //因为子数组是连续的 所以肯定和前缀有关 如果有子数组可以和为k 那么当前前缀 - 之前前缀 = k
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> psCounts = new HashMap<>();    // 统计前缀和出现次数，
        psCounts.put(0,1);                                   // 初始ps[0] = 0出现一次    前缀和为0 出现1次 以处理一个数的情况
        int res = 0;                                         // 满足条件的子数组个数
        int ps = 0;                                          // 当前前缀和ps[i]
        for(int num: nums){
            ps += num;                                              // 更新前缀和，[0, i+1)
            res += psCounts.getOrDefault(ps - k, 0);                // 累加以元素nums[i]为结尾，满足子数组条件个数 //有就get 没有就0 前缀和ps - 前缀和为ps-k = k
            psCounts.put(ps, psCounts.getOrDefault(ps, 0) + 1);     // 将当前前缀和加入哈希表
        }
        return res;
    }






    public static void main(String[] args) {
        int[] nums ={1,1,1};
        int k = 2;
        LeetCodeHot10 leetCodeHot10 = new LeetCodeHot10();
        System.out.println(leetCodeHot10.subarraySum(nums, k));
    }
}
