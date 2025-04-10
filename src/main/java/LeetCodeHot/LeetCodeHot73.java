package LeetCodeHot;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class LeetCodeHot73 {
    /**
     *  柱状图中最大的矩形
     *  给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     */
    //还是遍历 从头开始 遇到下一个不行的就先算前面的面积 然后更新最大值
    //做不对，我这边只做到了顾后，没有瞻前
    public int largestRectangleArea0(int[] heights) {
        Deque<Integer> stack = new LinkedList<>();
        int maxArea = heights[0];
        stack.push(0);
        for (int i = 1; i < heights.length; i++) {
            //遇到较大的
            //之前的元素都出栈
            if (!stack.isEmpty() && heights[stack.peek()] < heights[i]) {
                Integer pop = stack.pop();
                maxArea = Math.max(maxArea, heights[pop] * (stack.size() + 2));
                maxArea = Math.max(maxArea, heights[i]);//处理单条最大的情况
                while (!stack.isEmpty()) {
                    stack.pop();
                }
            }else if (!stack.isEmpty() && i == heights.length - 1) {//这种情况只有到最后没有大的 然后还没计算最大值
                maxArea = Math.max(maxArea, heights[i] * (stack.size() + 1));
            }
            stack.push(i);
        }
        stack.clear();
        stack.push(heights.length - 1);
        for (int i = heights.length - 2; i >= 0; i--) {
            //遇到较大的
            //之前的元素都出栈
            if (!stack.isEmpty() && heights[stack.peek()] < heights[i]) {
                Integer pop = stack.pop();
                maxArea = Math.max(maxArea, heights[pop] * (stack.size() + 2));
                maxArea = Math.max(maxArea, heights[i]);//处理单条最大的情况
                while (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!stack.isEmpty() && i == heights.length - 1) {//这种情况只有到最后没有大的 然后还没计算最大值
                maxArea = Math.max(maxArea, heights[i] * (stack.size() + 1));
            }
            stack.push(i);
        }

        return maxArea;
    }

    //思路：先思考暴力解法：固定高，然后向两边扩散，获得最大值；如果每一个柱子都向两边扩散获得最大值，
    //那么就可以获得最终的最大值
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        //处理left：这里是存放 该索引下左边第一个比它高度小的位置，即扩散到的位置的边缘 -1代表到左边尽头
        Deque<Integer> mono_stack = new ArrayDeque<Integer>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        //处理right 同理，记录该索引下右边第一个比它高度小的位置 n代表能够遍历到末尾
        mono_stack.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            right[i] = (mono_stack.isEmpty() ? n : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            //计算每个位置扩散出去的最大值 更新最大值答案
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }


    public static void main(String[] args) {
        LeetCodeHot73 lc = new LeetCodeHot73();
        int i = lc.largestRectangleArea(new int[]{5, 4, 1, 2});
        System.out.println(i);

    }

}
