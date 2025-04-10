package LeetCodeHot;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeetCodeHot86 {
    /**
     * 单词拆分
     * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。
     * 如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
     * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
     */
    // 看到这个我真想不出来动态规划的递推式
    // 看了题解
    // 记忆化搜索 一步一步向后
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[len+1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {// 对于每个位置 从头开始遍历 如果满足这个位置之前有并且wordDict还包含剩余，就返回true
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }
}
