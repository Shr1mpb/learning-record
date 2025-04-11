package LeetCodeHot1;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Hot11 {
    // 滑动窗口最大值 固定滑窗
    // 超时
    public int[] maxSlidingWindow0(int[] nums, int k) {
        // 特殊情况
        if (k == nums.length) {
            int ret = Integer.MIN_VALUE;
            for (int num : nums) {
                ret = Math.max(ret, num);
            }
            return new int[]{ret};
        }
        // 普遍情况
        Deque<Integer> deque = new LinkedList<>();
        int[] ret = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            deque.offerLast(nums[i]);
        }
        ret[0] = getMax(deque);
        for (int i = k; i < nums.length; i++) {
            deque.pollFirst();
            deque.offerLast(nums[i]);
            ret[i - k + 1] = getMax(deque);
        }
        return ret;
    }
    private int getMax(Deque<Integer> deque) {
        int max = Integer.MIN_VALUE;
        for (Integer integer : deque) {
            max = Math.max(max, integer);
        }
        return max;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int retLen = len - k + 1;
        int[] ret = new int[retLen];
        LinkedList<Integer> dequeue = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            while (!dequeue.isEmpty() && nums[dequeue.peekLast()] <= nums[i]) {
                dequeue.pollLast();
            }
            dequeue.offerLast(i);
            // 查看队首元素是否有效
            while (!dequeue.isEmpty() && dequeue.peekFirst() <= i - k) {
                dequeue.pollFirst();
            }
            if (i >= k - 1) {
                ret[i - k + 1] = nums[dequeue.peekFirst()];
            }
        }


        return ret;
    }

    public static void main(String[] args) {
        Hot11 hot11 = new Hot11();
        System.out.println(Arrays.toString(hot11.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7 }, 3)));
    }
}
