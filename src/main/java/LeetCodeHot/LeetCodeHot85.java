package LeetCodeHot;

import java.util.Arrays;

public class LeetCodeHot85 {
    /**
     * 零钱兑换
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * 你可以认为每种硬币的数量是无限的。
     */
    //      代表j
    // f[i] = 循环j Math.min(f[i-j] + 1 ,f[i])  f[0] = 0
    public int coinChange0(int[] coins, int amount) {
        if (amount == 0) return 0;
        Arrays.sort(coins);
        int[] f = new int[amount + 1];
        Arrays.fill(f, amount + 1); // 不可达
        f[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = coins.length - 1; j >= 0; j--) {
                if (coins[j] <= i) {
                    f[i] = Math.min(f[i], f[i - coins[j]] + 1);
                }
            }
        }

        return f[amount] > amount ? -1 : f[amount];

    }
    // f[i] = min(j=0 ~ n-1)f[i-cj] + 1
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    public static void main(String[] args) {
        LeetCodeHot85 leetcodehot85 = new LeetCodeHot85();
        System.out.println(leetcodehot85.coinChange(new int[]{2}, 3));

    }
}
