package LeetCodeHot;

import java.util.HashMap;

public class LeetCodeHot81 {
    /**
     * 爬楼梯    典型动态规划 需要递推表达式
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     */
    // 递归 F(n) = F(n-1) + F(n-2)
    // 可以存储数据来避免重复计算
    HashMap<Integer,Integer> map = new HashMap<>();
    public int climbStairs0(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (map.get(n) == null) {
            int value = climbStairs0(n - 1) + climbStairs0(n - 2);
            map.put(n, value);
            return value;
        }else{
            return map.get(n);
        }
    }

    // 动态规划 就是在递归时记录状态，只记录与下一个有关的状态 空间复杂度优化到了O(1)
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {// 滚动“数组”更新 每次向前移动位置 一直算到第n次
            int temp = first + second;
            first = second;
            second = temp;
        }
        return second;
    }

    public static void main(String[] args) {
        LeetCodeHot81 leetCodeHot81 = new LeetCodeHot81();
        System.out.println(leetCodeHot81.climbStairs(4));
    }

}
