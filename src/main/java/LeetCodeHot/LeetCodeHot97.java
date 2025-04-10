package LeetCodeHot;

public class LeetCodeHot97 {
    /**
     * 多数元素
     * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     */
    // 摩尔投票法
    public int majorityElement(int[] nums) {
        int count = 1;
        int now = nums[0];
        for(int i = 1;i<nums.length;i++){
            if(nums[i] == now){
                count++;
            }else{
                count--;
                if(count < 0){
                    now = nums[i];
                    count = 1;
                }
            }
        }
        return now;
    }

}
