package LeetCodeHot;

public class LeetCodeHot79 {
    /**
     * 跳跃游戏 II
     * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
     * 每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
     * 0 <= j <= nums[i]
     * i + j < n
     * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
     */
    // 维护当前能够到达的最大下标位置，记为边界。我们从左到右遍历数组，到达边界时，更新边界并将跳跃次数增加 1
    public int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {

            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
                if (end >= nums.length) {
                    return steps;
                }
            }
        }
        return steps;
    }


    public static void main(String[] args) {
        LeetCodeHot79 leetcodehot79 = new LeetCodeHot79();
        System.out.println(leetcodehot79.jump(new int[]{1, 2, 1, 1, 1}));
    }
}
