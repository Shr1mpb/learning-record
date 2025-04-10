package LeetCodeHot1;

import java.util.*;

public class Hot2 {
    // 字母异位词分组
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs.length < 2) {// 处理特殊情况
            ArrayList<String> objects = new ArrayList<>();
            objects.add(strs[0]);
            res.add(objects);
            return res;
        }
        HashMap<String,List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sorted = new String(charArray);
            if (map.containsKey(sorted)) {
                map.get(sorted).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(sorted, list);
            }
        }

        return new ArrayList<>(map.values());

    }
}
