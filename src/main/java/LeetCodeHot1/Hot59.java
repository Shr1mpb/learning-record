package LeetCodeHot1;

import java.util.ArrayList;
import java.util.List;

public class Hot59 {
    // 括号生成  双回溯穿透
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtracking(res, new StringBuilder(), 0, 0, n);
        return res;
    }
    private void backtracking(List<String> res, StringBuilder sb, int open, int close, int max) {
        if (sb.length() == max * 2) {// 够了 加入结果
            res.add(sb.toString());
            return;
        }
        if (open < max) {
            sb.append('(');
            backtracking(res, sb, open + 1, close, max);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close < open) {
            sb.append(')');
            backtracking(res, sb, open, close + 1, max);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
