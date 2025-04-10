package LeetCodeHot;

import java.util.Arrays;
import java.util.HashSet;

public class LeetCodeHot3 {
    /**
     *  最长连续序列
     * @param nums 传入的数组
     * @return 返回最长的序列
     */
    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        //这里使用HashSet 存放单个数字 每次查出的时间复杂度是O(1)
        HashSet<Integer> set = new HashSet<>();
        int ret = 0;
        int max = 0;
        for (int num : nums) {
            if (set.contains(num)) {
            }else if(set.contains(num-1)){
                set.add(num);
                ret++;
                if (ret >= max) max = ret;
            }else {
                set.add(num);
                ret = 1;
                if (ret >= max) max = ret;
            }
        }
        return max;
    }
    public int longestConsecutive2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int res = 0;
        int num = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] - nums[i - 1] == 1) {
                num++;
            } else {
                if (res < num) {
                    res = num;
                }
                num = 1;
            }
        }
        return Math.max(res, num);
    }

    public static void main(String[] args) {
        LeetCodeHot3 leetCodeHot3 = new LeetCodeHot3();
        int[] nums = {9,1,4,7,3,-1,0,5,8,-1,6};
        System.out.println(leetCodeHot3.longestConsecutive(nums));
    }
}
