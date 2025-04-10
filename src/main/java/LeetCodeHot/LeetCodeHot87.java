package LeetCodeHot;

import java.util.*;

public class LeetCodeHot87 {
    /**
     * 最长递增子序列
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
     * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     */
    // 自己实现的 时间复杂度不太好
    public int lengthOfLIS0(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        // 从最上面的解释看 每个位置需要维护一个 这个序列的最后一个数 和 这个序列的长度
        List<int[]> list = new ArrayList<>(nums.length);
        list.add(new int[]{nums[0], 1});// 初始化第一个位置
        for (int i = 1; i < nums.length; i++) {// 从第二个位置开始动态更新
            int lastNum = Integer.MIN_VALUE;
            int length = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {// 遍历之前的int[]
                int[] ints = list.get(j);
                if (nums[i] > ints[0]) {// 严格大于时
                    if (ints[1] + 1 > length) {
                        length = ints[1] + 1;
                        lastNum = nums[i];
                    }
                }
            }
            // 循环结束后设置下一个数 如果是通过前面的设置出来的 那肯定长度>1
            if (lastNum != Integer.MIN_VALUE) {
                list.add(new int[]{lastNum, length});
            }else{// 如果没有通过前面设置 那就设置自己本身和长度1
                list.add(new int[]{nums[i], 1});
            }


        }
        // 返回结果 返回最长的
        int ret = 0;
        for (int[] ints : list) {
            ret = Math.max(ret, ints[1]);
        }
        return ret;
    }

    // 动态规划 优化了上面的解法
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;// 最少长度也是1
            for (int j = 0; j < i; j++) {// 遍历前面的长度
                if (nums[i] > nums[j]) {// 当前位置的数大于前面序列最后一个位置的数
                    dp[i] = Math.max(dp[i], dp[j] + 1);// 更新当前位置的长度
                }
            }
            maxans = Math.max(maxans, dp[i]);// 更新最大长度
        }
        return maxans;
    }



    public static void main(String[] args) {
        LeetCodeHot87 leetCodeHot87 = new LeetCodeHot87();
        System.out.println(leetCodeHot87.lengthOfLIS(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6}));
    }
}
