package LeetCodeHot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LeetCodeHot57 {
    /**
     * 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     */
    static final Map<Character, String> map = Map.of(
            '2', "abc", '3', "def", '4', "ghi", '5', "jkl",
            '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz"
    );
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()){
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        char[] digitArray = digits.toCharArray();
        trackback(digitArray,new ArrayList<>(),res,0);
        return res;
    }
    public void trackback(char[] digitArray,List<Character> cur,List<String> res,int index){
        if(cur.size() == digitArray.length){//到最后一层了 开始返回结果
            StringBuilder sb = new StringBuilder();
            for (Character c:cur){
                sb.append(c);
            }
            res.add(sb.toString());
            return;
        }
        if (index < digitArray.length){
            char[] thisRow = map.get(digitArray[index]).toCharArray();
            int subIndex = 0;//存储现在在该层的索引
            while (subIndex != thisRow.length) {//本层要走这层元素个数个子路
                cur.add(thisRow[subIndex++]);

                trackback(digitArray, cur, res, index + 1);

                cur.removeLast();
            }

        }

    }

    public static void main(String[] args) {
        LeetCodeHot57 leetCodeHot57 = new LeetCodeHot57();
        leetCodeHot57.letterCombinations("3");
    }
}
