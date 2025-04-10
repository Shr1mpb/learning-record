package LeetCodeHot;

public class LeetCodeHot74 {
    /**
     * 数组中的第K个最大元素
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */
    //思路：快速排序O(logn)+遍历找出最大元素
    public int findKthLargest(int[] nums, int k) {
        quickSort(nums);
        int temp = nums[nums.length - 1] + 1;
        int ret = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == temp){
                continue;
            }
            k--;
            if (k == 0) {
                ret = nums[i];
            }
        }
        return ret;

    }

    private void quickSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        quickSortHelper(nums, 0, nums.length - 1);
    }

    private void quickSortHelper(int[] nums, int low, int high) {
        if (low < high) {
            int pivotIndex = paration(nums, low, high);
            quickSortHelper(nums, low, pivotIndex - 1);
            quickSortHelper(nums, pivotIndex + 1, high);
        }
    }

    private int paration(int[] nums, int low, int high) {
        int pivot = nums[low];
        int left = low+1;
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
        swap(nums, low, right);
        return right;
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }


    //优化解法：有点牛逼，用数组自然而然的排序了数字大小并且存储了数字
    public int findKthLargest2(int[] nums, int k) {
        int[] sst = new int[20001];
        for (int num : nums) {//保证大的数被存在sst的较大位置
            sst[num + 10000]++;
        }

        for(int i = 20000; i >= 0; i--){
            k -= sst[i];//开始减大的数 若该位置有 则成功
            if(k <= 0) return i - 10000; //减到第k个 返回该数
        }
        return 0;
    }





}
