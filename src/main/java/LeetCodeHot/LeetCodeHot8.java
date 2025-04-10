package LeetCodeHot;

import java.util.*;

public class LeetCodeHot8 {

    /**
     * 无重复字符的最长子串
     * @param s 给定一个字符串s
     * @return 找出不含有重复字符的最长子串的长度
     */
    public int lengthOfLongestSubstring0(String s) {
        int ret = 0;
        if (Objects.equals(s, "") || s.isEmpty()){
            return 0;
        }
        char[] charArray = s.toCharArray();
        List<Character> list = new ArrayList<>();
        int temp = 0;
        for (Character c : charArray) {
            if (list.contains(c)) {
                int last = list.lastIndexOf(c);
                while (last-- != -1) {
                    list.removeFirst();
                    temp--;
                }
                list.add(c);
                temp++;
                ret = Math.max(ret, temp);

            } else {
                temp++;
                list.add(c);
                ret = Math.max(ret, temp);
            }
        }
        return ret;

    }


    //改用set remove的时候使用元素进行remove 时间复杂度低
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        char[] sc = s.toCharArray();
        int res = 0;
        Set<Character> set = new HashSet<>();
        for (int l = 0, r = 0; r < n; r++) {
            while(set.contains(sc[r])){
                set.remove(sc[l++]);
            }
            set.add(sc[r]);
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "pwwkew";
        LeetCodeHot8 leetCodeHot8 = new LeetCodeHot8();
        int i = leetCodeHot8.lengthOfLongestSubstring(s);
        System.out.println(i);
    }
}
