package LeetCodeHot1;

import java.util.HashSet;
import java.util.Set;

public class Hot8 {
    // 无重复字符的最长子串 -- 滑动窗口问题(本质类似于双指针) 窗口大小不固定
    public int lengthOfLongestSubstring(String s) {
        int start = 0, end = 0;
        int ret = 0;
        Set<Character> set = new HashSet<>();
        char[] charArray = s.toCharArray();
        while(end < s.length()) {
            if (!set.contains(charArray[end])) {
                set.add(charArray[end++]);
                ret = Math.max(ret, set.size());
            }else{
                set.remove(charArray[start++]);
            }
        }
        return ret;
    }
    public static void main(String[] args) {
        Hot8 hot8 = new Hot8();
        System.out.println(hot8.lengthOfLongestSubstring("abcabcbb"));
    }
}
