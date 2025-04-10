package LeetCodeHot;

import java.util.*;

public class LeetCodeHot12 {
    /**
     * 最小覆盖子串
     * @param s 给定字符串s
     * @param t 给定字符串t
     * @return 返回s中涵盖t所有字符的 最小子串 如果s不存在涵盖t的所有字符的子串 返回""
     */

    //这里是我能想到的解法 缺点是不能处理t中重复
    public String minWindow0(String s, String t) {
        String ret = "";
        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();
        int l = 0;//滑窗左
        boolean retEmpty = true;
        Set<Character> hit = new HashSet<>();
        HashSet<Character> tChar = new HashSet<>();
        int temp = 0;
        for (Character c : tCharArray) {
            tChar.add(c);
        }

        HashMap<Integer, Integer> retList = new HashMap<>();
        for (int i = 0; i < sCharArray.length; i++) {
            //i是右指针 遍历，如果有t中的字符，就加入hit
            if (tChar.contains(sCharArray[i])) {
                hit.add(sCharArray[i]);
            }
            //看看hit有没有满 当hit都有 即hit大小到达t的大小时 开始缩小左边的指针
            if (hit.size() == t.length()) {
                while (!hit.contains(sCharArray[l])) {
                    l++;
                }
                //得到最小的滑窗 记录最小值 并标记返回标记
                int minLen = i-l+1;
                retList.put(minLen, l);
                retEmpty = false;
                //记录结果后，将左指针右移一位，右指针置为(左指针 - 1)的位置，清空hit，继续循环
                hit.clear();
                l++;
                i=l-1;
            }

        }

        Set<Integer> integers = retList.keySet();
        int minLen = s.length();
        for (Integer integer:integers){
            if (minLen >= integer) {
                minLen  = integer;
            }
        }
        if (retList.get(minLen) != null) {
            ret = s.substring(retList.get(minLen),retList.get(minLen) + minLen);
        }

        if (retEmpty) {
            return "";
        }
        else {
            return ret;
        }
    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int formed = 0; // 记录窗口中已经匹配的t中字符的个数
        int requiredSize = need.size(); // t中不同字符的个数
        int[] result = {-1, 0, Integer.MAX_VALUE}; // 存储最小覆盖子串的起始索引、起始索引+1（为了计算子串长度方便）、最小长度

        while (right < s.length()) {
            char r = s.charAt(right);
            if (need.containsKey(r)) {
                window.put(r, window.getOrDefault(r, 0) + 1);
                if (window.get(r).equals(need.get(r))) {//数量一致后加
                    formed++;
                }
            }
            right++;

            // 尝试收缩窗口左边界
            /*
              这里while循环是判断左边是不是需要的字符 如果不是就直接left++ 如果是，就让form--，并且对应字符-- 之后 left++，然后继续进行下一轮的for循环
             */
            while (left < right && formed == requiredSize) {
                if (right - left < result[2]) {
                    result[0] = left;
                    result[2] = right - left;
                }

                char l = s.charAt(left);
                if (need.containsKey(l)) {
                    if (window.get(l).equals(need.get(l))) {
                        formed--;
                    }
                    window.put(l, window.get(l) - 1);
                }
                left++;
            }
            //
        }

        return result[0] == -1 ? "" : s.substring(result[0], result[0] + result[2]);
    }


    public static void main(String[] args) {
        LeetCodeHot12 leetCodeHot12 = new LeetCodeHot12();

        String s = "ADOBECODEBANC";
        String t = "ABC";

        System.out.println(leetCodeHot12.minWindow(s,t));
    }
}
