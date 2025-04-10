package sort;

import java.util.Arrays;

public class SwitchSort {
    /*
        交换排序
        这里学习快速排序和堆排序

     */
    //1.冒泡/起泡排序 (下沉、上浮) O(n^2)
    //2.快速排序 O(n log2 n) 存储开销 log2 n 平均时间复杂度nlog2 n 但是有序时会退化到n^2
        //最快 也需要额外空间 并且不稳定
        /*
            选定基准后：
            if(length>1)
            基准位置
            left,right
            quickSort(left,基准位置+1),quickSort(基准位置-1,right)
            合并
        */
    public static void main(String[] args) {
        int[] nums = new int[]{3, 5, 9, 11, 56, 31, 20, 4, 22, 88, 96, 15, 18, 62, 34};
        quickSort(nums);
    }


    //快速排序  O(n log2 n) 存储开销 log2 n
    //两个指针 向中间移动 左一次右一次 交换 知道low == high 即完成一趟排序
    public static void quickSort(int[] nums) {
        // 如果数组长度小于等于1，直接返回
        if (nums.length <= 1) {
            System.out.println(Arrays.toString(nums));
            return; // 关键是要返回，不要继续执行后面的代码
        }

        quickSortHelper(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    private static void quickSortHelper(int[] nums, int low, int high) {
        if (low < high) {
            // 获取划分后基准元素的索引
            int pivotIndex = partition(nums, low, high);
            // 对基准元素左侧和右侧的子数组递归调用快速排序
            quickSortHelper(nums, low, pivotIndex - 1);
            quickSortHelper(nums, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] nums, int low, int high) {
        // 选择第一个元素作为基准
        int pivot = nums[low];
        int left = low + 1; // 左指针从基准后一个位置开始
        int right = high; // 右指针从高位开始

        while (left <= right) {
            // 找到左边第一个大于等于基准的元素
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            // 找到右边第一个小于等于基准的元素
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            // 如果左指针小于或等于右指针，交换元素
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        // 最后将基准元素放到正确的位置
        swap(nums, low, right);
        return right; // 返回基准元素的索引
    }


    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
