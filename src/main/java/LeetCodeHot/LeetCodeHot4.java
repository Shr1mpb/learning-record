package LeetCodeHot;

import java.util.ArrayList;
import java.util.Arrays;

public class LeetCodeHot4 {
    /**
     * 移动零
     * @param nums 给定一个数组 将所有0移动到数组末尾 并保持非零元素的相对
     */
    public void moveZeroes(int[] nums) {
        ArrayList<Integer> arrayList = new ArrayList();
        int zeroNum = 0;
        for (int num : nums) {
            if (num != 0) {
                arrayList.add(num);
            }else {
                zeroNum++;
            }
        }
        while (zeroNum-- > 0) {
            arrayList.add(0);
        }
        System.out.println(Arrays.toString(arrayList.toArray()));
    }

    /**
     * 常规冒泡解法 时间复杂度O(n²)
     */
    public void moveZeroes2(int[] nums) {
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] == 0) {
                    int temp = nums[j+1];
                    nums[j+1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    /**
     *插入排序解法 O(n) 一次遍历  快速排序是nlog(n)
     */
    public void moveZeroes3(int[] nums) {
        int p = 0;//第一个指针
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {//i相当于第二个指针
            if (nums[i] != 0){
                temp = nums[i];
                nums[i] = nums[p];
                nums[p++] = temp;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        LeetCodeHot4 leetCodeHot4 = new LeetCodeHot4();
        leetCodeHot4.moveZeroes3(nums);
    }
}
