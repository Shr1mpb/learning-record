package LeetCodeHot;

public class LeetCodeHot7 {

    /**
     * 接雨水
     * @param height 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，
     * @return 计算按此排列的柱子，下雨之后能接多少雨水。
     */
    /*
        初步拿到这个题没什么太大思路 总体有两种 一种是按行求， 一种是按列求
        这个思路是逐步优化的，下面逐步一个一个写
     */

    //按行求雨水 时间复杂度O(n²)
    public int trap1(int[] height) {
        int ret =  0;
        int maxlength = 0;
        //求出最大列
        for (int j : height) {
            if (j > maxlength) {
                maxlength = j;
            }
        }
        //遍历每个行 然后求每列
        //设置变量isStart 当这一行出现第一个块 开始包围水时 开始记录左块后水的数量
        //之后遇到又块，总水数+temp_sum 之后把总水数记0 重复之前的操作
        for (int i = 1; i <= maxlength; i++) {
            boolean isStart = false;
            int temp_sum = 0;
            for (int j = 0; j < height.length; j++) {
                if (isStart && height[j] < i) {
                    temp_sum++;
                }
                if (height[j] >= i) {
                    ret = ret + temp_sum;
                    temp_sum = 0;
                    isStart = true;
                }
            }
        }
        return ret;
    }

    //按列求 遍历到当前列时，寻找有没有比当前列高的左右列，如果有，就把小-当前列加到ret上
    public int trap2(int[] height) {
        if (height.length <= 2) {
            return 0;
        }
        int ret = 0;
        //由于左右两端为0肯定没水 所以遍历跳过左右两端为0的部分
        int left = 0;
        int right = height.length-1;
        while(height[left] == 0) left++;
        while(height[right] == 0) right--;
        //第一个有块儿的地方也不会有水，故遍历也去掉
        left++;
        right--;
        //开始遍历
        for (int i = left; i <= right; i++) {
            int max_left = 0;
            int max_right = 0;

            //找出左边最大
            for (int j = left-1; j < i; j++) {
                if (height[j] >= height[i]) {
                    max_left = Math.max(height[j],max_left);
                }
            }
            //找出右边最大
            for (int k = i + 1; k <= right+1; k++) {
                if (height[k] >= height[i]) {
                    max_right = Math.max(height[k],max_right);
                }
            }
            //都找到了
            if (max_left != 0 && max_right != 0) {
                ret += Math.min(max_left,max_right) - height[i];
            }

        }
        return ret;
    }

    //上面两种 不管按行还是按列 复杂度都是O(n²)
    //对于按行求来说 每一行的情况都不同
    //下面对按列求进行优化 比较直观
    /*
        动态规划；
            使用max_left和max_right数组 存储每一列左右最高列
            那么下一列的左最高，即是max(左列记录的最高高度,左列的高度)
                      右最高同理 max(右列记录的最高高度,右列的高度)

        时间复杂度O(n) 空间复杂度O(n)(因为需要用两个数组来保存信息)
     */
    public int trap3(int[] height) {
        if (height.length <= 2) {
            return 0;
        }
        int ret = 0;
        //由于左右两端为0肯定没水 所以遍历跳过左右两端为0的部分
        int left = 0;
        int right = height.length-1;
        while(height[left] == 0) left++;
        while(height[right] == 0) right--;
        //第一个有块儿的地方也不会有水，故遍历也去掉
        left++;
        right--;
        //存储max_left和max_right
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];
        for (int i = left; i <= right; i++) {
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
        }for (int i = right; i >= left; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }
        for (int i = left; i <= right; i++) {
            //都找到了
            int min = Math.min(max_left[i],max_right[i]);
            if (min >= height[i]) {
                ret += min - height[i];
            }
        }
        return ret;
    }

    /*
        解法4:双指针对撞
        推荐： 时间复杂度O(n) 空间复杂度O(1)
            思路：如果从左到右遍历要水的列，可以把max_left改成一个变量，从而节省空间
                但是如果要想把max_right数组去掉，则需从右向左遍历，否则无法去掉
                因此，双向遍历

                理解：从两边向中间 帖边的列的水的高度是由两边的最大高度决定的
                    只看当列 当列左边有没有比当列右边高的
                    第一个if块： 当列左边没有比当列右边高的，说明当列左边的max决定当列的高低
     */
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
            //这里的好处是 leftMax和rightMax与当前指针列也会有关系 当当前指针列与max一样高时，添加的水数为0
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


    public static void main(String[] args) {
        LeetCodeHot7 leetCodeHot7 = new LeetCodeHot7();

        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(leetCodeHot7.trap(height));

        System.out.println();

        int[] height2 = {4,2,0,3,2,5};
        System.out.println(leetCodeHot7.trap(height2));
    }

}
