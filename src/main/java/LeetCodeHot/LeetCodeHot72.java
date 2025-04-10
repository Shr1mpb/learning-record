package LeetCodeHot;


import java.util.Deque;
import java.util.LinkedList;

public class LeetCodeHot72 {
    /**
     * 每日温度
     * 给定一个整数数组 temperatures ，表示每天的温度，
     * 返回一个数组 answer ，
     * 其中 answer[i] 是指对于第 i 天，
     * 下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     */
    //超出时间限制
    public int[] dailyTemperatures0(int[] temperatures) {
        int[] res =  new int[temperatures.length];
        for (int i = 0; i < temperatures.length - 1; i++) {
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[j] > temperatures[i]) {
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }

    //单调栈 及时去除无用元素 保持栈中有序
    //从左到右
    //一旦发现比栈顶元素大的数 就更新栈顶元素的答案 并且把栈顶元素出栈 新的数入栈
    public int[] dailyTemperatures1(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        Deque<Integer> st = new LinkedList<>();//栈中存放的是索引 索引能够拿到温度 相当于存放了索引+温度
        for (int i = 0; i < n; i++) {
            int t = temperatures[i];
            //栈不空 并且温度要大于栈顶元素
            while (!st.isEmpty() && t > temperatures[st.peek()]) {
                //栈顶元素出栈 更新栈顶元素的答案
                int j = st.pop();
                ans[j] = i - j;
            }
            //新元素入栈
            st.push(i);
        }
        return ans;
    }

    //单调栈
    //从右到左遍历
    //思路：右边找到较大的数后 日后找左边的结果就不用比较小的一方了 高 低 低 高 中间的低低就可以去掉
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        Deque<Integer> st = new LinkedList<>();//栈中存放的是索引 索引能够拿到温度 相当于存放了索引+温度
        for (int i = n - 1; i >= 0; i--) {
            int t = temperatures[i];
            while (!st.isEmpty() && t >= temperatures[st.peek()]) {
                st.pop();
            }
            if (!st.isEmpty()) {
                ans[i] = st.peek() - i;
            }
            st.push(i);
        }
        return ans;
    }


    public static void main(String[] args) {
        LeetCodeHot72 leetcode = new LeetCodeHot72();
        leetcode.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
    }
}
