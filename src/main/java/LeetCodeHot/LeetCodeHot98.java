package LeetCodeHot;

public class LeetCodeHot98 {
    /**
     * 颜色分类
     * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，
     * 原地 对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
     */
    public void sortColors(int[] nums) {
//        insertionSort(nums);
            quickSort(nums, 0, nums.length - 1);
    }



    private void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            int j = i - 1;
            for (; j >= 0 && nums[j] > temp; j--) {
                nums[j + 1] = nums[j];
            }
            nums[j + 1] = temp;
        }
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int pivotIndex = paration(nums, left, right);
            quickSort(nums, left, pivotIndex - 1);
            quickSort(nums, pivotIndex + 1, right);
        }
    }
    private int paration(int[] nums, int low, int high) {
        int pivot = nums[low];
        int left = low + 1;
        int right = high;
        while (left <= right) {
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }

        }
        swap(nums, low, right);
        return right;
    }
    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
