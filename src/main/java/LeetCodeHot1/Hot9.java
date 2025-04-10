package LeetCodeHot1;

import java.util.*;

public class Hot9 {
    // 找到字符串中所有字母异位词 滑动窗口问题 窗口大小固定
    // 超时
    public List<Integer> findAnagrams0(String s, String p) {
        char[] charArray = s.toCharArray();
        char[] target = p.toCharArray();
        if (target.length > charArray.length) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        List<Character> targetList = new ArrayList<>();
        for (char c : target) {
            targetList.add(c);
        }
        targetList.sort(Comparator.comparingInt(o -> o));
        for (int i = 0; i < charArray.length - targetList.size() + 1; i++) {
            List<Character> tempList = new ArrayList<>();
            for (int j = i; j < i + targetList.size(); j++) {
                tempList.add(charArray[j]);
            }
            tempList.sort(Comparator.comparingInt(o -> o));
            if (tempList.equals(targetList)) {
                result.add(i);
            }
        }
        return result;

    }

    public List<Integer> findAnagrams(String s, String p) {
        char[] charArray = s.toCharArray();
        char[] target = p.toCharArray();
        if (target.length > charArray.length) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (char c : target) {
            pCount[c - 'a']++;
        }
        // 先固定一个滑窗
        for (int i = 0;i<target.length;i++) {
            sCount[charArray[i] - 'a']++;
        }
        if (Arrays.equals(sCount, pCount)) {
            result.add(0);
        }
        // 开始滑动
        for (int i = target.length; i < charArray.length; i++) {
            sCount[charArray[i] - 'a']++;
            sCount[charArray[i - target.length] - 'a']--;
            if (Arrays.equals(sCount, pCount)) {
                result.add(i - target.length + 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Hot9 hot9 = new Hot9();
        System.out.println(hot9.findAnagrams("cbaebabacd", "abc"));
    }
}
