package LeetCodeHot;

import java.util.*;

public class LeetCodeHot84 {
    /**
     * 完全平方数
     * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
     * 完全平方数 是一个整数，其值等于另一个整数的平方；
     * 换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
     */
    // 动态规划：递推式比较难想 f[i]表示至少需要多少个数字表示
    // f[i] = 1 + min((j从1~根号i)f[i-j^2]) f[0] = 0
    public int numSquares0(int n) {
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int minn = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                minn = Math.min(minn, f[i - j * j]);
            }
            f[i] = minn + 1;
        }
        return f[n];
    }

    // 动态规划2：存储一个完全平方数的集合，完全平方数是绝对最少的只用一个
    // 从1开始遍历 遇到一个数后，需要遍历之前的完全平方数，看看能不能找到最少数字
    public int numSquares(int n) {
        int[] nums = new int[n + 1];
        List<Integer> squares = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            double sqrt = Math.sqrt(i);
            if ((int) sqrt == sqrt) {// 当前数是完全平方数
                squares.add(i);// 完全平方数集合中加入该数
                squares.sort(Comparator.comparingInt(Integer::intValue).reversed());// 倒序 后面遍历时要快一点
                nums[i] = 1;// 该数的答案是1
            }else{// 当前数不是完全平方数 找出该数的答案
                int ans = Integer.MAX_VALUE;
                for (int square : squares) {// 遍历完全平方数和i-当前完全平方数的和 找出最小答案
                    ans = Math.min(ans, nums[square] + nums[i - square]);
                    if (ans == 2) {// 如果已经是2 已经比1大的最小 结束循环
                        break;
                    }
                }
                nums[i] = ans;
            }
        }
        return nums[n];
    }


}
