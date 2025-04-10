package LeetCodeHot;

public class LeetCodeHot78 {
    /**
     * 跳跃游戏
     * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
     */
    // 思路：每次都跳最多，如果超过：就返回true
    // 如果没有返回true，向前回溯一下，找下一个点接着跳最远
    // 回溯算法超时
    public boolean canJump0(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        return canJumpHelper(nums, 0, nums[0]);
    }

    private boolean canJumpHelper(int[] nums, int currIndex,int currStep) {
        if (currIndex >= nums.length - 1) { // 递归出口
            return true;
        }
        //递归
        while(currStep > 0) {
            int temp = currIndex;
            temp = currIndex + currStep;
            if (temp <= nums.length - 1) {
                if (canJumpHelper(nums, temp, nums[temp])) { // 递归
                    return true;
                }
            }
            currStep--;// 回溯
        }
        return false;
    }

    //贪心算法 最远能超过，就已经能跳到
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {// i在最远位置之前，说明能从i出发
                rightmost = Math.max(rightmost, i + nums[i]); // 更新最远位置
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        LeetCodeHot78 leetCodeHot78 = new LeetCodeHot78();
        System.out.println(leetCodeHot78.canJump(new int[]{2, 3, 1, 1, 4}));
    }

}
