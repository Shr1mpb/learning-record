package LeetCodeHot;

public class LeetCodeHot88 {
    /**
     * 乘积最大子数组
     * 给你一个整数数组 nums ，
     * 请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
     * 测试用例的答案是一个 32-位 整数。
     */
    // 不通过 需要根据正负讨论
    public int maxProduct0(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int ret = dp[0];
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.max(nums[i], nums[i] * dp[j - 1]);
            }
            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }

    // 官方题解
    public int maxProduct(int[] nums) {
        int length = nums.length;
        long[] maxF = new long[length];
        long[] minF = new long[length];
        for (int i = 0; i < length; i++) {// 先都置为本身
            maxF[i] = nums[i];
            minF[i] = nums[i];
        }
        for (int i = 1; i < length; ++i) {// 遍历得到最小值和最大值集合
            maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(nums[i], minF[i - 1] * nums[i]));
            minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(nums[i], maxF[i - 1] * nums[i]));
        }
        int ans = (int) maxF[0];
        for (int i = 1; i < length; ++i) {// 获得最大值
            ans = Math.max(ans, (int) maxF[i]);
        }
        return ans;
    }

    // 正反遍历一次得到答案 纯数学方法
    /*
    这个思路确实难想，有点儿像贪心。
    这个使用了一个结论，一个不包含0的整数序列的连续乘积最大值，一定以起点开始或者以终点结束。
    使用反证法可以证明，如果连续乘积最大值不以起点开始也不以终点结束，也就是说，
    结果序列两边都有非0整数。分类讨论，如果连续乘积最大值为正，那么结果序列左右两边应该异号，
    否则可以向两边扩展，而如果两边异号，那么一定存在一边为正，也可以继续扩展，与假设矛盾。如果连续乘积最大值为负，
    那么结果序列两边一定同号，否则可以向两边扩展，如果同负，那么乘以任意一个负数都会让乘积变大，
    如果同正，那么任意一个正数都大于这个负数的连续乘积最大值。综上，不以起点开始且不以终点结束的序列，一定不是最大的连续乘积
     */
    public int maxProduct1(int[] nums) {
        int max=nums[0];
        int product = 1;
        for (int num : nums) {
            product *= num;
            max = Math.max(max, product);
            if (product == 0) product = 1;
        }
        product=1;
        for(int j=nums.length-1;j>=0;j--){
            product*=nums[j];
            max = Math.max(max,product);
            if(product==0)product=1;
        }
        return max;
    }



}
