package sort;

import java.util.Arrays;

/*
    这个包 sort 下的排序都是内排序 即数据都在内存中的排序
    这里是作为引入 练手
    排序分类：
        插入
        交换(冒泡在这里)
        选择
        归并
        基数
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 5, 9, 11, 56, 31, 20, 4, 22, 88, 96, 15, 18, 62, 34};

        int temp = 0;

        //升序 关键词： (n-1)轮排序 (n-轮)次比较
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j+1] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(nums));

    }
}
