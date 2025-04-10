package LeetCodeHot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCodeHot61 {
    /**
     * 分割回文串
     * 给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
     */
    //这个题又没做出来，不知道怎么回溯，找不到树
            /*
                回溯+动态规划
                状态转移方程：
                    f(i,j)= true,i>=j
                            f(i+1,j-1) && s[i]==s[j]
             */
    boolean[][] f;
    List<List<String>> ret = new ArrayList<>();
    List<String> ans = new ArrayList<>();
    int n;

    public List<List<String>> partition(String s) {

        //dfs之前都是动态规划 f[][]数组用于判断子串是否为回文串
        //f的第一维代表起始索引 第二维代表终止索引
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], true);
        }

        //循环从最后一行开始 保证在状态转移计算f[i][j]时，f[i+1][j-1]已经有值
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }

        dfs(s, 0);
        return ret;
    }



    public void dfs(String s, int i) {
        if (i == n) {
            ret.add(new ArrayList<>(ans));  //！！！这里的方法比 ans.stream().toList()要速度快
            return;
        }
        for (int j = i; j < n; ++j) {//前面的子串已经被分割并加入了结果 现在要枚举后面的子串 如果是回文就添加，并且继续递归枚举
            // 以开始的i=0为例，先是第一个回文，然后从第一个开始找下一个回文，直到满足条件添加进了结果
            // 然后j会被循环，相当于第一个回文判断变成了前两位，然后继续递归查找
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }


    public static void main(String[] args) {
        LeetCodeHot61 l = new LeetCodeHot61();
        System.out.println(l.partition("aab"));
    }
}
