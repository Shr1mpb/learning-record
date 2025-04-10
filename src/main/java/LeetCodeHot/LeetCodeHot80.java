package LeetCodeHot;

import java.util.*;

public class LeetCodeHot80 {
    /**
     * 划分字母区间
     * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，
     * 同一字母最多出现在一个片段中。例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。
     * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
     * 返回一个表示每个字符串片段的长度的列表。
     */
    // 从左到右遍历字符串，遍历的同时维护当前片段的开始下标 start 和结束下标 end，初始时 start=end=0。
    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            last[s.charAt(i) - 'a'] = i;
        }// 记录所有字母最后出现的下标
        List<Integer> partition = new ArrayList<Integer>();
        int start = 0, end = 0;

        // 每次记录最后索引的位置 然后不断更新 到最后索引的位置时说明这个片段已经完毕 加入长度
        for (int i = 0; i < length; i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                partition.add(end - start + 1);
                start = end + 1;
            }
        }
        return partition;
    }

    public static void main(String[] args) {
        LeetCodeHot80 leetcode = new LeetCodeHot80();
        System.out.println(leetcode.partitionLabels("ababcbacadefegdehijhklij"));
    }
}
