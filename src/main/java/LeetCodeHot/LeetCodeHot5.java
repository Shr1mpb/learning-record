package LeetCodeHot;

public class LeetCodeHot5 {
    /**
     * 盛水最多的容器 ---双指针问题 从两端向中间靠拢 一步一步找到最大
     * @param height 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i])
     *               若向内 移动短板 ，水槽的短板 min(h[i],h[j]) 可能变大，因此下个水槽的面积 可能增大 。
     *               若向内 移动长板 ，水槽的短板 不变或变小，因此下个水槽的面积 一定变小 。

     *               短板内收 抛弃不好的
     * @return 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     */
    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int ret = 0;
        while (l < r) {
            ret = height[l] > height[r] ? Math.max(ret,calculateSize(height, l, r--)) : Math.max(ret,calculateSize(height, l++, r));
        }
        return ret;
    }

    private int calculateSize(int[] height,int l,int r) {
        return Math.min(height[l], height[r]) * (r - l);
    }
}
