package sort;

import java.util.Arrays;

public class review {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 5, 9, 11, 56, 31, 20, 4, 22, 88, 96, 15, 18, 62, 34};
//        insertSort(nums);
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if(nums[j] > curr){
                    nums[j+1] = nums[j];
                }
                else{
                    break;
                }
            }
            nums[j+1] = curr;
        }
    }

    //快速排序
    //思路：选基准，分拣(比基准小的放左边 大的放右边)，递归(对基准左右再进行如此操作)，合并
    private static void quickSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        quickSortHelper(nums, 0, nums.length - 1);
    }
    private static void quickSortHelper(int[] nums, int low, int high) {
        if(low < high){//
            int pivotIndex = partition(nums,low,high);//分拣
            quickSortHelper(nums, low, pivotIndex - 1);//递归
            quickSortHelper(nums, pivotIndex + 1, high);
        }
    }
    private static int partition(int[] nums, int low, int high) {
        //关键在 将基准左边都放比基准小的 右边都放比基准大的
        int pivot = nums[low];
        int left = low + 1;
        int right = high;
        while (left <= right) {
            //找出左边大于的
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            //找出右边小于的
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(nums,left,right);
                left++;
                right--;
            }
        }
        //放对基准位置
        swap(nums, low, right);
        return right;//返回基准位置
    }
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
