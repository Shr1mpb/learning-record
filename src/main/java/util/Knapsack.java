package util;

import java.util.Arrays;

public class Knapsack {
    public static int knapsack(int[] weights, int[] values, int capacity) {
        // dp[i][j] 代表前i个物品在容量j下的最大价值
        int[][] dp = new int[weights.length + 1][capacity + 1];
        // 初始化dp[0][x] = 0
        Arrays.fill(dp[0], 0);
        // 开始填充表格
        for (int i = 1; i <= weights.length; i++) {
            for (int w = 0; w <= capacity; w++) {
                int currentWeight = weights[i - 1];
                int currentValue = values[i - 1];

                if (currentWeight > w) {
                    // 当前物品无法放入背包，继承前i-1个物品的状态
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // 选择放入或不放入当前物品，取最大值
                    dp[i][w] = Math.max(
                            dp[i - 1][w],// 不放
                            dp[i - 1][w - currentWeight] + currentValue// 放 前i-1个物品在容量
                    );
                }
            }
        }
        return dp[weights.length][capacity];
    }

    public static void main(String[] args) {
        int[] weights = {2, 3, 4, 5};    // 物品重量
        int[] values = {3, 4, 5, 6};     // 物品价值
        int capacity = 8;                // 背包容量
        int maxValue = knapsack(weights, values, capacity);
        System.out.println("最大价值为: " + maxValue);  // 输出: 10
    }
}
