package LeetCodeHot;

import java.util.*;

/**
 * 字母异位词分组
 * HOT2依然哈希相关
 * 根据一致的东西创建哈希并视为key，然后这个哈希返回的结果集为key 封装成ret并返回
 *
 */
public class LeetCodeHot2 {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ret = new ArrayList<>();
        if (strs == null ){
            ret = null;
        }else if (strs.length == 1){
            List<String> elem = new ArrayList<>();
            elem.add(strs[0]);
            ret.add(elem);
        }else{
            HashMap<String , List<String>> elems = new HashMap<>();
            for (String str : strs) {
                char[] charArray = str.toCharArray();
                Arrays.sort(charArray);
                String string = new String(charArray);
                if (elems.containsKey(string)) {
                    elems.get(string).add(str);
                } else {
                    ArrayList<String> newArrayList = new ArrayList<>();
                    newArrayList.add(str);
                    elems.put(string, newArrayList);

                }
            }
//            Set<Map.Entry<String, List<String>>> entries = elems.entrySet();
//            for (Map.Entry<String, List<String>> entry : entries) {
//                ret.add(entry.getValue());
//            }
            ret = new ArrayList<>(elems.values());
        }

        return ret;
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        LeetCodeHot2 leetCodeHot2 = new LeetCodeHot2();
        System.out.println(leetCodeHot2.groupAnagrams(strs));
    }
}
