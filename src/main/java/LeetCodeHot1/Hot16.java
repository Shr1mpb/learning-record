package LeetCodeHot1;

import java.util.Arrays;

public class Hot16 {
    // 除自身以外数组的乘积
    // 从两边开始计算前缀积 一个位置计算了：前、后两次前缀积 正好不包括自己
    public int[] productExceptSelf(int[] nums) {
        int n=nums.length;
        int[] ans=new int[n];
        Arrays.fill(ans,1);
        int beforeSum=1;
        int afterSum=1;
        for(int i=0,j=n-1;i<n;i++,j--){
            ans[i]*=beforeSum;
            ans[j]*=afterSum;
            beforeSum*=nums[i];
            afterSum*=nums[j];
        }
        return ans;
    }
}
