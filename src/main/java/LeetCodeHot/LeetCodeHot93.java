package LeetCodeHot;

public class LeetCodeHot93 {
    /**
     * 最长回文子串
     * 给你一个字符串 s，找到 s 中最长的 回文 子串。
     */
    // dp[i][j]表示从i到j的子串是回文串
    public String longestPalindrome(String s) {
        int n = s.length();
        int start = 0, end = 0;
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                //                                 要么范围足够小    要么里面是回文串
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    if (j - i > end - start) {
                        start = i;
                        end = j;
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }
}
