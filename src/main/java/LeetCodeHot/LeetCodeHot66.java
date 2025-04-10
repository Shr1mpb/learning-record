package LeetCodeHot;

import java.util.Arrays;

public class LeetCodeHot66 {
    /**
     * 搜索旋转排序数组
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
     * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
     * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int startIndex = findStartIndex(nums);
        Arrays.sort(nums);
        int ret = binarySearch(nums, target);
        if (ret == -1) return -1;
        return (ret + startIndex) % (nums.length);
    }

    private int findStartIndex(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i + 1;
            }
        }
        return 0;
    }


    private int binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                low = mid + 1;
            }
            if (nums[mid] > target) {
                high = mid - 1;
            }
        }
        return -1;
    }

    //上面的时间复杂度太差 优化如下
    public int search2(int[] nums, int target) {
        int n=nums.length;
        if(n==0) return -1;
        if(n==1){
            return nums[0]==target?0:-1;
        };
        int left=0;
        int right=n-1;
        while(left<=right){
            int mid=(right+left)/2;
            if(nums[mid]==target) return mid;
            if(nums[0]<=nums[mid]){
                if(nums[0]<=target&&nums[mid]>target){
                    right=mid-1;
                }else{
                    left=mid+1;
                }
            }else{
                if(nums[mid]<target&&nums[n-1]>=target){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LeetCodeHot66 leetCodeHot66 = new LeetCodeHot66();
        System.out.println(leetCodeHot66.search(new int[]{1,3}, 3));
    }
}
