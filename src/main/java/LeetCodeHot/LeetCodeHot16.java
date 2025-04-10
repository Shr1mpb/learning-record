package LeetCodeHot;

import java.util.Arrays;

public class LeetCodeHot16 {
    /**
     * 除自身以外数组的乘积
     * @param nums 给定整数数组nums
     * @return 返回数组ans ans[i]的值为nums中除了nums[i]的各元素的乘积
     * 要求在时间复杂度O(n)完成 难点：不能使用除法
     */
    public int[] productExceptSelf(int[] nums) {
        int n=nums.length;
        int[] ans=new int[n];
        Arrays.fill(ans,1);
        int beforeSum=1;
        int afterSum=1;
        //算法精髓： i和j会有交叉的时候 ans[i]计算时(i>length/2) ans[i]已经被设置过是它后面所有数的积 再乘before 得到答案
        for(int i=0,j=n-1;i<n;i++,j--){
            ans[i]*=beforeSum;
            ans[j]*=afterSum;
            beforeSum*=nums[i];
            afterSum*=nums[j];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5};
        System.out.println(Arrays.toString(new LeetCodeHot16().productExceptSelf(nums)));
    }
}
