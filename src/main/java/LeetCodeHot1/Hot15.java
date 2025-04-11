package LeetCodeHot1;

public class Hot15 {
    // 轮转数组
    // 翻转法 O(n) + O(1)即可完成
    // 轮转时 尾部j个元素会到头部 可以先反转 让尾部元素到头部 然后再反转回去 相当于移动到了头部 头部元素向后移动同理
    public void rotate(int[] nums, int k) {
        int rotateNum = k % nums.length;
        if (rotateNum == 0) {
            return;
        }
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, rotateNum - 1);
        reverse(nums, rotateNum, nums.length - 1);

    }
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }

}
