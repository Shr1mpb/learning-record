package LeetCodeHot;

import java.util.Arrays;
import java.util.LinkedList;

public class LeetCodeHot11 {
    /**
     * 滑动窗口最大值
     * @param nums 给定的数组
     * @param k 滑窗的大小 只能看到滑窗大小k个数字 滑窗每次只向右移动一位
     * @return 滑动窗口中的最大值
     *
     * 解法：双端队列 这里使用了java中的LinkedList
     * 目的是维护一个既在队列中 又是最大值的数字 维护最大值时 实际上队列中的每个元素只被处理了一次 时间复杂度为O(n)
     */

    //该方法每次都需要找打最大值，即要遍历k长度，时间复杂度是O(n x k)
    public int[] maxSlidingWindow0(int[] nums, int k) {
        int len = nums.length;
        int retLen = len - k + 1;
        int[] ret = new int[retLen];
        //滑窗开始移动
        for (int i = 0; i < retLen; i++) {
            int max = nums[i];
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            ret[i] = max;
        }
        return ret;
    }
    //下面用一个preMax来记录之前滑窗口中的最大值 降低时间复杂度 此时时间复杂度最坏情况仍然是O(n x k) 超时
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int len = nums.length;
        int retLen = len - k + 1;
        int[] ret = new int[retLen];
        int preMax = 0;
        //先计算第一个滑窗的最大值
        for (int i = 0; i < k; i++) {
            preMax = Math.max(nums[i],preMax);
        }
        //此时preMax是第一个滑窗的最大值 直接加入到ret中
        ret[0] = preMax;
        //从第二个滑窗开始 进行后续的计算
        for (int i = 1; i < retLen; i++) {//1 2 3 4 5
            if (preMax != nums[i - 1] && nums[i + k - 1] >= preMax) {
                preMax = nums[i + k - 1];
                ret[i] = preMax;
            } else if (preMax != nums[i - 1]) {
                ret[i] = preMax;
            }else{
                int temp = nums[i];
                for (int j = i; j < i + k; j++) {
                    temp = Math.max(nums[j], temp);
                }
                ret[i] = temp;
                preMax = temp;
            }
        }
        return ret;
    }

    //ai指出，当滑窗问题时间复杂度是O(n x k)时 可以用双端队列优化 使之时间复杂度变为O(n)
    //队列中存储的是索引

   //解释：每次进入队列的数，都让它是最大值。队列的排序是从大到小， 之后判断队头的数是不是在窗口中 不在则弹出 保证队列中都是滑窗内的元素
    //从大到小，且都在窗口中
    //然后返回最大的数
    //在队列里相当于只是维持了一个在滑窗内的最大数 并不需要k的复杂度 因为每个数字只被处理了一次
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int retLen = len - k + 1;
        int[] ret = new int[retLen];
        LinkedList<Integer> dequeue = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            //队列后端 小数弹出
            while (!dequeue.isEmpty() && nums[dequeue.peekLast()] <= nums[i]) {
                dequeue.pollLast();
            }
            dequeue.addLast(i);
            //判断当前队列的队首是否有效
            if (!dequeue.isEmpty() && dequeue.peek() <= (i - k)) {
                dequeue.poll();
            }
            //当前窗口为长度为k时，保留最大值
            if (i + 1 >= k && !dequeue.isEmpty()) { //i+1>=k时 后面都是窗口 都要保留
                ret[i + 1 - k] = nums[dequeue.peek()];
            }
        }


        return ret;
    }

    //依据上面的思路 维护一个在队列中 并且是最大值的数即可
    //有问题 维护在不在队列时 如果不在 只能把它置为现在当前位置的元素 可以回溯 但是会影响时间复杂度 故不太好实现
    //这个思路其实和preMax类似求解时间不稳定
    //在滑窗问题中，如果我们想要找到每个窗口的最大值，并且不使用队列（或类似的数据结构）来维护这些最大值，
    // 那么我们通常需要一些额外的变量来跟踪当前窗口内的最大值以及相关的状态信息。然而，完全避免使用任何类似队列的数据结构可能会使算法变得复杂且难以高效实现。
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length == 1) {
            return nums;
        }
        int index = 0;
        int[] ret = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            if (index <= i - k) {//维护队列中
                index = i - k + 1;//不在队列中 置为下一个队列的队列头
                i = i-k+1;
                continue;
            }
            if (nums[index] <= nums[i]) {//维护最大
                index = i;
            }
            if (i + 1 >= k) {
                ret[i+1-k] = nums[index];
            }

        }
        return ret;
    }


    public static void main(String[] args) {
        LeetCodeHot11 leetCodeHot11 = new LeetCodeHot11();
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        int k = 3;
        System.out.println(Arrays.toString(leetCodeHot11.maxSlidingWindow2(nums, k)));
    }
}
