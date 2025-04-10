package LeetCodeHot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LeetCodeHot13 {
    /**
     * 最大子数组和
     * @param nums 给定一个数组 找出具有最大和的连续子数组(至少包含一个元素)
     * @return 返回最大和
     */
    //动态规划(下面放了两个动态规划的理解的做法)
    //即求解很多组 得到最优的解

    //理解1：需要深刻理解情况 不普适
    //这里从开头开始，每组最大的连续子数组 一定是以正数开头 当sum<0时，这一组也就结束了，继续进行下一组的求解
    //如果全是负数 直接找出最大值即可
    public int maxSubArray(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        for(int num: nums) {
            if(sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    //贪心
    public int maxSubArray0(int[] nums) {
        int sum=0,maxAns=nums[0];
        for(int x:nums){
            sum+=x;
            if(sum>maxAns) maxAns=sum;
            if(sum<0) sum=0;
        }
        return maxAns;
    }


    //官方动态规划
    // 理解2:(2024.11.17 暂时理解成要获得一个递推公式)
    //我们用 f(i) 代表以第 i 个数结尾的「连续子数组的最大和」，
    //那么 fi = max （f[i-1] + x，f[i-1]）
    public int maxSubArray1(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }




    public static void main(String[] args) {
        LeetCodeHot13 leetCodeHot13 = new LeetCodeHot13();
        int[] nums = new int[]{-1,-2};
        System.out.println(leetCodeHot13.maxSubArray(nums));
    }
}
