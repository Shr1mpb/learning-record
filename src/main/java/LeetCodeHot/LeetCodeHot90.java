package LeetCodeHot;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class LeetCodeHot90 {
    /**
     * 最长有效括号
     * 给你一个只包含 '(' 和 ')' 的字符串，
     * 找出最长有效（格式正确且连续）括号子串的长度。 注意：这里的子串不一定连续
     */
    // 找出最长连续字串的最长有效长度
    public int longestValidParentheses0(String s) {
        return helper(s, 0, s.length());
    }

    public int helper(String s, int left, int right) {
        int ret = 0;
        if (left < right-1) {
            int[] find = find(s.substring(left, right));
            int helper = helper(s, find[1]+1, right);
            int helper1 = helper(s, left, find[0]-1);
            ret = find[2] + helper1 + helper;
        }
        return ret;
    }

    /**
     * 用于找到字符串中最长合法括号连续字串的起始索引，结束索引，长度
     * @param s
     * @return
     */
    public int[] find(String s) {
        if (s.length() < 2) return new int[]{0, 0, 0};
        char[] charArray = s.toCharArray();
        int left = 0;
        int count = 0;
        int ret = 0;
        int startIndex = 0;
        int endIndex = 0;
        int temp = 0;
        for (int i = 0; i < charArray.length; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left++;
            } else {
                if (left <= 0) {// 非法 重置并加上ret
                    left = 0;
                    count = 0;
                    temp = i + 1;
                } else {// 合法 添加个数并减left
                    left--;
                    count++;
                    if (count > ret) {
                        ret = count;
                        endIndex = i;
                        startIndex = temp;
                    }
                }
            }
        }
        return new int[]{startIndex, endIndex, ret * 2};
    }

    // 栈
    public int longestValidParentheses(String s) {
        if (s.length() < 2) return 0;
        char[] charArray = s.toCharArray();
        Deque<Integer> left = new LinkedList<>();
        left.offer(-1);
        int ret = 0;
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '(') {
                left.push(i);
            }else{
                left.pop();
                if (left.isEmpty()) {
                    left.push(i);
                }else{
                    ret = Math.max(ret, i - left.peek());
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        LeetCodeHot90 leetCodeHot90 = new LeetCodeHot90();
        System.out.println(leetCodeHot90.longestValidParentheses0("(()"));
    }
}
