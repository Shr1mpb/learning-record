package LeetCodeHot;

import java.util.Arrays;

public class LeetCodeHot83 {
    /**
     * 打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
     * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     */
    // 方法 动态规划
    // 方程： dp[i]=max(dp[i−2]+nums[i],dp[i−1])
    // 边界条件为：只有一间房屋，则偷窃该房屋 只有两间房屋，选择其中金额较高的房屋进行偷窃
    public int rob(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int length = nums.length;
            if (length == 1) {
                return nums[0];
            }
            int[] dp = new int[length];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < length; i++) {
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            }
            return dp[length - 1];
    }

    public static void main(String[] args) {
        LeetCodeHot83 leetcodeHot83 = new LeetCodeHot83();
        System.out.println(leetcodeHot83.rob(new int[] { 1, 2, 3, 1 }));
    }
}
