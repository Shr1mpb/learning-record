package sort;

import java.util.Arrays;

public class review3 {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 5, 9, 11, 56, 31, 20, 4, 22, 88, 96, 15, 18, 62, 34, 34, 88, 15};
//        insertionSort(nums);
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }
    private static void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int j = i-1;
            int curr = nums[i];//!!!重要 因为手牌后移的时候会覆盖当前的
            for (; j >= 0 && nums[j] > curr; j--) {
                nums[j+1] = nums[j];
            }
            nums[j+1] = curr;
        }
    }

    private static void quickSort(int[] nums) {
        quickSortHelper(nums, 0, nums.length - 1);
    }

    private static void quickSortHelper(int[] nums, int low, int high) {
        if (low < high) {
            int pivotIndex = paration(nums, low, high);
            quickSortHelper(nums, low, pivotIndex-1);
            quickSortHelper(nums, pivotIndex+1, high);
        }
    }
    private static int paration(int[] nums, int low, int high) {
        int pivot = nums[low];
        int left = low + 1;
        int right = high;
        while (left <= right) {//找出左边一个大的 右边一个小的 并交换位置
            while(left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        //pivot放在正确的位置
        swap(nums, low, right);
        return right;//pivot位置返回
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
