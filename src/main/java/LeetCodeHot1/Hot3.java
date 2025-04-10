package LeetCodeHot1;

import java.util.Arrays;

public class Hot3 {
    // 最长连续序列
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int temp = nums[0];
        int retTemp = 1;
        int ret = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == temp + 1) {
                retTemp++;
                temp = nums[i];
            }else if (nums[i] == temp) {
              continue;
            } else{
                ret = Math.max(retTemp, ret);
                retTemp = 1;
                temp = nums[i];
            }
        }
        ret = Math.max(retTemp, ret);
        return ret;
    }

    public static void main(String[] args) {
        // [100,4,200,1,3,2] [0,3,7,2,5,8,4,6,0,1]
        System.out.println(new Hot3().longestConsecutive(new int[]{1,0,1,2}));
    }
}
