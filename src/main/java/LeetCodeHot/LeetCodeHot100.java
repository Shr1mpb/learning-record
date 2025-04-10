package LeetCodeHot;

public class LeetCodeHot100 {
    /**
     * 寻找重复数
     * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
     * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
     * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
     */
    // 快慢指针 数字都在1~n范围内 建立 索引->数字 的映射，让下一次的索引=上一次索引映射的结果
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        slow = nums[slow];// 慢指针 一次走一步
        fast = nums[nums[fast]]; // 快指针 一次走两步(映射两次)
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        // 快慢相遇的时候，即到了环中
         // 到环中后，让一个指针=0 同时走一步 相等的时候即遇见的时候
        int pre1 = 0;
        int pre2 = slow;
        while(pre1 != pre2){
            pre1 = nums[pre1];
            pre2 = nums[pre2];
        }
        return pre1;
    }

}
