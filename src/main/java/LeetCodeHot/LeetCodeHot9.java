package LeetCodeHot;

import java.util.*;

public class LeetCodeHot9 {
    /**
     * 找到字符串中所有字母异位词
     * @param s 传入字符串
     * @param p 传入异位词的判断词
     * @return 返回找到的异位词( 起始位置，长度为p.length的子串) 的起始位置索引
     */

    //滑动窗口： 不满足条件向右拓宽边界 满足条件左边收缩边界确定最优
    //滑动窗口 + 数组(这里主要是数组)
    public List<Integer> findAnagrams(String s, String p) {
        int n = s.length(), m = p.length();
        List<Integer> res = new ArrayList<>();
        if(n < m) return res;
        int[] pCnt = new int[26];
        int[] sCnt = new int[26];
        for(int i = 0; i < m; i++){
            pCnt[p.charAt(i) - 'a']++;
            sCnt[s.charAt(i) - 'a']++;
        }
        if(Arrays.equals(sCnt, pCnt)){
            res.add(0);
        }
        for(int i = m; i < n; i++){
            sCnt[s.charAt(i - m) - 'a']--;
            sCnt[s.charAt(i) - 'a']++;
            if(Arrays.equals(sCnt, pCnt)){
                res.add(i - m + 1);
            }
        }
        return res;
    }

    //滑动窗口
    //这个题中 最优解的宽度就是p的宽度，也就是3 所以用长度为3的窗口向右移动即可
    public List<Integer> findAnagramsError(String s, String p) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < s.length() - p.length() + 1; i++) {
            String subString = s.substring(i, i + p.length());
            if (isEctopicWord(subString,p)) {
                ret.add(i);
            }
        }

        return ret;
    }

    //时间复杂度 O(滑窗宽度) 这里错的原因就是没有判断个数 判断个数用到数组 时间复杂度还是O(常数)(见上解法)
    private boolean isEctopicWord(String subString, String p) {
        // 使用 HashSet 存储第一个字符串的字符
        HashSet<Character> charSet = new HashSet<>();
        for (char c : subString.toCharArray()) {
            charSet.add(c);
        }

        // 遍历第二个字符串，检查字符是否在集合中
        for (char c : p.toCharArray()) {
            if (!charSet.contains(c)) {
                return false;
            }
        }

        // 如果所有字符都在集合中，返回 true
        return true;
    }


    public static void main(String[] args) {
        String s = "baa";
        String p = "aa";
        LeetCodeHot9 leetCodeHot9 = new LeetCodeHot9();
        System.out.println(leetCodeHot9.findAnagrams(s, p));
    }


}

