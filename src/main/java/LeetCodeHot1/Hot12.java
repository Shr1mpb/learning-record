package LeetCodeHot1;

import java.util.Arrays;
import java.util.HashSet;

public class Hot12 {
    // 最小覆盖子串
    // 超时
    public String minWindow0(String s, String t) {
        // 处理不存在情况
        if (t.length() > s.length()) {
            return "";
        }
        // 开始处理
        HashSet<Character> set = new HashSet<>();
        char[] charArray1 = t.toCharArray();
        for (char c : charArray1) {
            set.add(c);
        }
        int[] find = new int[60];
        for (int k = 0; k < t.length(); k++) {// 记录需要的字符
            find[charArray1[k] - 'A']++;
        }
        int[] sint = new int[s.length()];// 找出包含子串中字母的地方
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (set.contains(charArray[i])) {
                sint[i] = 1;
            }
        }
        int minIndex = 0;
        for (int i = 0;i<sint.length;i++) {
            if (sint[i] == 1) {
                minIndex = i;
                break;
            }
        }
        int maxIndex = minIndex;
        for (int i = sint.length-1;i>=0;i--) {
            if (sint[i] == 1) {
                maxIndex = i;
                break;
            }
        }
        int maxLen = maxIndex - minIndex + 1;
        // 开始滑动窗口 逐渐扩大窗口到包含所有子串字符
        // 滑窗长度逐渐边长
        for (int i = t.length(); i <= maxLen; i++) {// 从等长 到最长
            if (i == 9) {
                System.out.println();
            }
            for (int k = minIndex; k <= maxIndex - i + 1; k++) {
                int count = i;
                int[] tempFind = new int[60];
                for (int j = k; j <= maxIndex && count > 0; j++) {
                    count--;
                    if (sint[j] == 1) {
                        tempFind[charArray[j] - 'A']++;
                    }
                    if (count == 0) {
                        if (euq(tempFind, find)) {
                            return s.substring(j - i + 1, j + 1);
                        }
                    }
                }
            }
        }
        return "";
    }
    private boolean euq(int[] tempFind, int[] find) {
        for (int i = 0; i < find.length; i++) {
            if (tempFind[i] < find[i]) {
                return false;
            }
        }
        return true;
    }

    // 先做覆盖 再做移动
    public String minWindow(String s, String t) {
        int[] ss = new int[128];
        int[] tt = new int[128];
        // 统计字符串t的字母出现次数
        for(char c: t.toCharArray()){
            tt[c]++;
        }
        int ansLeft = -1, ansRight = s.length();
        String ans = "";
        // 滑动窗口
        int l = 0;
        for(int r = 0; r < s.length(); r++){
            // 先递增当前字母次数
            ss[s.charAt(r)]++;
            // 已经覆盖了，左端点右移
            while(isOK(ss, tt)){
                // 每次收缩窗口都更新ans
                if(r - l < ansRight - ansLeft){
                    ansRight = r;
                    ansLeft = l;
                }
                ss[s.charAt(l++)]--;
            }
        }
        // 最后更新ans可以省去大量时间
        return ansLeft < 0? "": s.substring(ansLeft, ansRight + 1);
    }

    // 判断是否覆盖
    private boolean isOK(int[] a, int[] b){
        for(int i = 'A'; i <= 'Z'; i++){
            if(a[i] < b[i]) return false;
        }
        for(int i = 'a'; i <= 'z'; i++){
            if(a[i] < b[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Hot12 hot12 = new Hot12();
        System.out.println(hot12.minWindow("aaaaaaaaaaaabbbbbcdd", "abcdd"));
    }
}
