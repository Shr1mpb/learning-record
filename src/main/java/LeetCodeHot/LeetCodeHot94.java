package LeetCodeHot;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LeetCodeHot94 {
    /**
     * 最长公共子序列
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
     * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
     * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
     */
    // 示例测试用例通过但WA
    public int longestCommonSubsequence0(String text1, String text2) {
        if(text1.intern().equals(text2.intern())) return text1.length();

        char[] charArray1 = text1.toCharArray();
        // 开始遍历前 让text1为较短的
        if (text1.length() > text2.length()) {
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }
        // 遍历
        int ret = 0;
        int count = 0;
        String temp = text2;
        boolean con = true;
        for (int i = 0; i < text1.length(); i++) {

            if (temp.contains(String.valueOf(charArray1[i]))) {
                temp = temp.substring(temp.indexOf(charArray1[i]), temp.length() - 1);
                count++;
                con = false;
                if (count > ret) {
                    ret = count;

                }
            }else if(con){
                temp = text2;
                count = 0;
                continue;
            }
        }
        return ret;

    }


    // 官解
    // dp[i][j] 表示 从text1 0~i和text2 0~j的最长公共子序列的长度 包前不包后
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        //初始化dp[0][] 和dp[][0]为0，因为当一个字符串长度为0时，那么他们的LCS长度也为0
        Arrays.fill(dp[0], 0);
        for(int i = 0; i <= m; i++){
            dp[i][0] = 0;
        }

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(text1.charAt(i - 1) == text2.charAt(j - 1))//该字符可以加入LCS
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else{//该位置的字符不相等，至少有一个不能加入LCS，先选择当前局部最优解，即选择前面的较大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];

    }





    public static void main(String[] args) {
        LeetCodeHot94 lc = new LeetCodeHot94();
        System.out.println(lc.longestCommonSubsequence("abcde", "acce"));
    }
}
