package LeetCodeHot1;

public class Hot17 {
    // 缺失的第一个正数 O(n) + O(1)
    /*
    数字可能重复
    对于一个长度为 N 的数组，其中没有出现的最小正整数只能在 [1,N+1] 中
    我们将数组中所有小于等于 0 的数修改为 N+1；
    我们遍历数组中的每一个数 x，它可能已经被打了标记，因此原本对应的数为 ∣x∣，其中 ∣∣ 为绝对值符号。如果 ∣x∣∈[1,N]，
    那么我们给数组中的第 ∣x∣−1 个位置的数添加一个负号。注意如果它已经有负号，不需要重复添加；
    在遍历完成之后，如果数组中的每一个数都是负数，那么答案是 N+1，否则答案是第一个正数的位置加 1。

     */
    // 原地类哈希表 映射排除答案范围
    public int firstMissingPositive(int[] nums) {
        // 最终目的：要让正数成为答案
        int n = nums.length;
        // 排除非正数影响
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        // 如果数字是1~n范围内 就把对应下标位置置为负数 标记已有
        for (int i = 0; i < n; ++i) {
            int abs = Math.abs(nums[i]);
            if (abs <= n) {// 注意 这里设置过就不要重复设置了 保证它一直是负数
                nums[abs-1] = -Math.abs(nums[abs-1]);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {// 找到第一个没有被标记的地方，返回它的下标对应的正数
                return i + 1;
            }
        }

        // 全是负数 说明1~n所有数字都有了 返回n+1
        return n + 1;
    }

    public static void main(String[] args) {
        Hot17 hot17 = new Hot17();
        System.out.println(hot17.firstMissingPositive(new int[]{1,1}));
    }

}
