package LeetCodeHot1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Hot57 {
    // 电话号码的字母组合
    static final Map<Character, String> map = Map.of(
            '2', "abc", '3', "def", '4', "ghi", '5', "jkl",
            '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz"
    );
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int total = digits.length();
        char[] input = digits.toCharArray();
        backtracking(res, sb, input, 0);
        return res;
    }

    private void backtracking(List<String> res, StringBuilder sb,char[] input,int index) {
        if (input.length == sb.length()) {
            res.add(sb.toString());
            return;
        }

        for (int i = index; i < input.length; i++) {
            char c = input[i];
            String s = map.get(c);
            char[] charArray = s.toCharArray();
            for (int j = 0; j < charArray.length; j++) {
                sb.append(charArray[j]);
                backtracking(res, sb, input, i + 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Hot57 hot57 = new Hot57();
        List<String> res = hot57.letterCombinations("23");
    }
}
