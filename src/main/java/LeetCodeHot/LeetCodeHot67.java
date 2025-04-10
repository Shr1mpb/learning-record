package LeetCodeHot;

public class LeetCodeHot67 {
    /**
     * 寻找旋转排序数组中的最小值
     */
    //一个数组至少旋转几次能够得到输入数组
    //即求出旋转数组中最小元素的索引 返回即可
    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0;
        int right = nums.length - 1;
        int ret = 0;
        while(left <= right) {
            int mid = (left + right) / 2;
            if (mid != 0 && nums[mid] < nums[mid - 1]) {
                ret = mid;
                break;
            }else if (mid != nums.length - 1 && nums[mid + 1] < nums[mid]) {
                ret = mid + 1;
                break;
            }
            if (nums[mid] >= nums[0]) {
                left = mid + 1;
            } else if (nums[mid] <= nums[nums.length - 1]) {
                right = mid - 1;
            }
        }
        return nums[ret];
    }

    public static void main(String[] args) {
        LeetCodeHot67 leetcode = new LeetCodeHot67();
        leetcode.findMin(new int[]{3, 4, 5, 1, 2});
    }
}
