package LeetCodeHot;

public class LeetCodeHot89 {
    /**
     * 分割等和子集
     * 给你一个 只包含正整数 的 非空 数组 nums 。
     * 请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     */
    public boolean canPartition(int[] nums) {
        if (nums.length < 2) {
            return false;
        }
        int sum = 0;
        int maxValue = Integer.MIN_VALUE;
        for (int num : nums) {
            sum += num;
            maxValue = Math.max(maxValue, num);
        }
        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;
        if (maxValue > target) {
            return false;
        }
        boolean[] dp = new boolean[target + 1];// 代表现在的数字是否能拼凑和来达到索引
        dp[0] = true;
        for (int num : nums) {
            for (int j = target; j >= num; --j) {// 这里必须从大到小 遍历看看有没有新的可达数字
                // 如果从小到大，会导致前面的dp[j]已被更新 会导致重复使用同一个数
                dp[j] |= dp[j - num];// 当前本身已经为true 或是加上当前数字可以达到
                if (dp[target]) {
                    return true;
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        LeetCodeHot89 leetcodehot89 = new LeetCodeHot89();
        System.out.println(leetcodehot89.canPartition(new int[]{2,2,3,5}));
    }
}
