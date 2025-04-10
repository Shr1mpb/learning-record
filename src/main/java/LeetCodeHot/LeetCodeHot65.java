package LeetCodeHot;

public class LeetCodeHot65 {
    /**
     * 在排序数组中查找元素的第一个和最后一个位置
     * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int i = binarySearch(nums, target);
        if (i == -1) {
            return new int[]{-1, -1};
        }
        int begin,end;
        begin = i;
        end = i;
        while(nums[begin] == target) {
            begin--;
            if (begin == -1) {
                break;
            }
        }
        while(nums[end] == target) {
            end++;
            if (end == nums.length) {
                break;
            }
        }
        return new int[]{begin + 1, end - 1};
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

    public static void main(String[] args) {
        LeetCodeHot65 leetcode = new LeetCodeHot65();
        System.out.println(leetcode.searchRange(new int[]{1}, 1));
    }
}
