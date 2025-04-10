package sort;

import java.util.Arrays;

public class review2 {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 5, 9, 11, 56, 31, 20, 4, 22, 88, 96, 15, 18, 62, 34, 34, 88, 15};
//        insertionSort(nums);
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int j = i-1;
            int curr = nums[i];
            //升降序只需要改变           ↓ 这里
            for (; j >= 0 && nums[j] > curr; j--) {
                nums[j+1] = nums[j];
            }
            nums[j+1] = curr;
        }
    }

    private static void quickSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        quickSortHelper(nums, 0, nums.length - 1);
    }

    private static void quickSortHelper(int[] nums, int low, int high) {
        if(low < high){
            int pivotIndex = partation(nums, low, high);
            quickSortHelper(nums, low, pivotIndex - 1);
            quickSortHelper(nums, pivotIndex + 1, high);
        }
    }

    private static int partation(int[] nums, int low, int high) {
        int pivot = nums[low];
        int left = low +1;
        int right = high;
        while(left <= right){
            while(left <= right && nums[left] < pivot){
                left++;
            }
            while(left <= right && nums[right] > pivot){
                right--;
            }
            if (left <= right){
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        swap(nums, low, right);
        return right;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
