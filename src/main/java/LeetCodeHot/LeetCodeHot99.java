package LeetCodeHot;

import java.util.Arrays;

public class LeetCodeHot99 {
    /**
     * 下一个排列
     * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
     * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
     * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，
     * 那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，
     * 那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
     * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
     * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
     * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
     * 给你一个整数数组 nums ，找出 nums 的下一个排列。
     * <p>
     * 必须 原地 修改，只允许使用额外常数空间。
     */

    public static void main(String[] args) {
        int[] ints = {1, 2, 3};
        new LeetCodeHot99().nextPermutation(ints);
        System.out.println(Arrays.toString(ints));
    }

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        // 从右边开始找到第一个降序的元素 即右边的已经最大了，需要变小
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            // 找到降序的右边元素的第一个比i大的元素
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);// 交换位置
        }
        reverse(nums, i + 1);// 直接反转 得到下一个右边最小
    }


    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 有问题没通过 132会变成231而不是213
    public void nextPermutation0(int[] nums) {
        boolean loop = true;
        for (int i = nums.length - 1; i > 0 && loop; i--) {
            for (int j = i -1; j >= 0 && loop; j--) {
                if (nums[j] < nums[i]) {
                    swap(nums, i, j);
                    loop = false;
                }
            }
        }
        if (loop) {// 上面没有找到
            Arrays.sort(nums);
        }
    }


}
