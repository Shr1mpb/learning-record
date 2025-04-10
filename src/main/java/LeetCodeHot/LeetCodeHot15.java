package LeetCodeHot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCodeHot15 {
    /**
     * 轮转数组
     * 给定一个整数数组 将数组中的元素向右轮转k个位置 k是非负数
     * @param nums 给定数组
     * @param k 给定整数k 非负
     */
    //想到方法 直接放在容器里 然后再赋值回去
    public void rotate(int[] nums, int k) {
        k = k%nums.length;
        List<Integer> list = new ArrayList<>(nums.length);
        for (int i = nums.length - k; i < nums.length; i++) {
            list.add(nums[i]);
        }
        for (int i = 0; i < nums.length - k; i++) {
            list.add(nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = list.get(i);
        }
    }
    //方法一样，同上 没用容器，快一些
    public void rotate0(int[] nums, int k) {
        int[] res = new int[nums.length];
        for(int i=0; i<nums.length; i++){
            res[i] = nums[i];

        }
        for(int i=0; i<nums.length; i++){
            nums[(i+k)%nums.length] = res[i];
        }
    }

    //进阶：三步翻转法
    public void rotate1(int[] nums, int k) {

        //1、数组复制法
        //观察，i+k%n的位置，放入i的元素
        //2、三步翻转法
        k = k % nums.length;
        swap(nums, 0, nums.length-1);//先翻转整个数组
        swap(nums, 0, k-1);//再翻转0到k-1
        swap(nums, k, nums.length-1);//再反转k到数组末尾

    }
    private void swap(int[] nums, int start, int end){
        while(start < end){
            int temp =  nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        new LeetCodeHot15().rotate(nums, k);
    }
}
