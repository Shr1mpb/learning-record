package LeetCodeHot;

public class LeetCodeHot96 {
    /**
     * 只出现一次的数字
     * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
     */
    public int singleNumber(int[] nums) {
        if (nums.length == 1) return nums[0];
        int res = 0;
        for (int num : nums) {
            res ^= num;// 按位异或后 出现两次的数字将会变成0
        }
        return res;
    }
}
