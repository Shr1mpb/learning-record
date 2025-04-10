package LeetCodeHot1;

public class Hot5 {
    // 盛最多水的容器
    public int maxArea(int[] height) {
        // 从两边向中间找 找到比较大的板子
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right) {
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            }else {
                right--;
            }
        }
        return max;
    }

}
