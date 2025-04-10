package LeetCodeHot1;

public class Hot7 {
    // 接雨水
    // 按层遍历 超时
    public int trap0(int[] height) {
        int res = 0;
        // 一层一层遍历
        int max = Integer.MIN_VALUE;
        for (int j : height) {
            max = Math.max(max, j);
        }
        for (int k = 1; k <= max; k++) {// 一共max层
            int left = 0, right = height.length - 1;
            // 找到这层的边界
            while(height[left] < k) {
                left++;
            }
            while(height[right] < k) {
                right--;
            }
            if (left >= right) {
                break;
            }
            int temp = right - left - 1;// 能放的水数
            // 找找边界里有几个地方不能放水
            for (int i = left + 1; i <= right - 1; i++) {
                if (height[i] >= k){
                    temp--;
                }
            }
            res += temp;
        }


        return res;
    }

    // 按列遍历
    public int trap(int[] height) {
        int n = height.length;
        int res = 0;
        // 左右指针：分别指向左右两边界的列
        int left = 0, right = n - 1;
        // 左指针的左边最大高度、右指针的右边最大高度
        int leftMax = height[left], rightMax = height[right];
        // 最两边的列存不了水
        left++;
        right--;
        // 向中间靠拢
        while(left <= right){
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if(leftMax < rightMax){
                // 左指针的leftMax比右指针的rightMax矮
                // 说明：左指针的右边至少有一个板子 > 左指针左边所有板子
                // 根据水桶效应，保证了左指针当前列的水量决定权在左边
                // 那么可以计算左指针当前列的水量：左边最大高度-当前列高度
                res += leftMax - height[left];
                left++;
            }else{
                // 右边同理
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }
}
