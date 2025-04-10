package LeetCodeHot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeetCodeHot59 {
    /**
     *  括号生成
     *  数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     */
    //思路：直接生成一组括号，之后存储下一个生成括号的位置，然后遍历回溯生成
    //这个题没做出来 一个劲越界
    //这个做法有点厉害 是先放好左括号 再放右括号 写了两次回溯
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    //以括号为基本单位：递归实现
    public List<String> generateParenthesis2(int n) {
        if (n == 1) {
            List<String> single = new ArrayList<>();
            single.add("()");
            return single;
        }
        Set<String> res = new HashSet<>();
        for (String s : generateParenthesis2(n - 1)) {
            for (int j = 0; j <= s.length(); j++) {
                res.add(s.substring(0, j) + "()" + s.substring(j));
            }
        }
        return new ArrayList<>(res);
    }

    public static void main(String[] args) {
        LeetCodeHot59 leetcode = new LeetCodeHot59();
        System.out.println(leetcode.generateParenthesis(3));
    }
}
