package sort;

import java.util.Arrays;

public class review4 {
    public static void main(String[] args) {
        int[] nums = new int[]{7,3,6,8,11,15,0,2,12,1,4,13,5,9,10,14};
//        insertionSort(nums);
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int j = i-1;
            int curr = nums[i];
            for(;j>=0 && nums[j]> curr;j--) {
                nums[j+1] = nums[j];
            }
            nums[j+1] = curr;
        }
    }

    public static void quickSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        quickSortHelper(nums, 0, nums.length - 1);
    }

    private static void quickSortHelper(int[] nums, int low, int high) {
        if (low < high) {
            int pivotIndex = paration(nums, low, high);
            System.out.println(pivotIndex);
            System.out.println(Arrays.toString(nums));
            quickSortHelper(nums, low, pivotIndex - 1);
            quickSortHelper(nums, pivotIndex + 1, high);
        }
    }

    private static int paration(int[] nums, int low, int high) {
        int pivot = nums[low];
        int left = low + 1;
        int right = high;
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
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
        swap(nums,low,right);
        return right;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
