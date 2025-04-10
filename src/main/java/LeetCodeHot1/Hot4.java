package LeetCodeHot1;

public class Hot4 {
    // 移动零
    public void moveZeroes(int[] nums) {
        // 把不等于0的都放在前面 剩余位置填充0(由于放置的时候是交换，所以剩余位置已经是0)
        int p = 0;
        for (int i = 0;i<nums.length;i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[p];
                nums[p++] = temp;
            }
        }
    }
}
