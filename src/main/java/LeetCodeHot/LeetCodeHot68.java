package LeetCodeHot;

import java.util.ArrayList;
import java.util.Comparator;

public class LeetCodeHot68 {
    /**
     * 寻找两个正序数组的中位数
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * 算法的时间复杂度应该为 O(log (m+n)) 。
     */
    //时间复杂度为O(log(m+n))的方法暂时想不出来
    //想不到如何不在O(n)的情况下使用二分查找
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        ArrayList<Integer> list1 = new ArrayList<>();
        for (int k : nums1) {
            list1.add(k);
        }
        for (int j : nums2) {
            list1.add(j);
        }
        list1.sort(Integer::compare);
        if (list1.size() % 2 == 0) {
            return  (double)(list1.get(list1.size() / 2 - 1) + list1.get(list1.size() / 2)) / 2;
        }
        else {
            return (double)list1.get(list1.size() / 2);
        }
    }

    //有了 双指针寻找分割线
    //分割线定义：若总数为奇数 则让分割线左边的元素多一个
    //若总数为偶数 则分割线左边和右边的元素个数相等
    //满足：第一个数组分割线左边的元素小于第二个数组分割线右边的元素
    //      第二个数组分割线左边的元素小于第一个数组分割线右边的元素
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {//让nums1永远都是较短的一方
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int m  = nums1.length;
        int n = nums2.length;
        int totalLeft = (m + n + 1) / 2;//奇数偶数都适用
        //开始在第一个数组中寻找分割线的位置
        int left = 0;
        int right = m;
        //nums1[i-1] <= nums2[j] && nums2[j-1] <= nums1[i]
        while (left < right) {//最终有 left == right ： 找出最大的i 第二个条件会自动满足
            int i = left + (right - left + 1) / 2;//取中间位置
            int j = totalLeft - i; //第二个数组左边元素的个数(分割线右边数字的索引)
            //根据上面的条件取反(由于是&& 仅取反前一个)，开始寻找
            if (nums1[i - 1] > nums2[j]) {//不满足条件，不是这个位置，直接向左搜索
                //下一轮向左搜索
                right = i - 1;//mid - 1
            }else {//满足条件，万一是这个位置呢？保留这个位置并向右搜索找最优
                //下一轮向右搜索
                left = i;//注意这里到最后会死循环，所以在i的地方再加1 然后i-1也不会越界了
            }
        }
        int i = left;
        int j = totalLeft - i;
        //越界分析 无意义时让其不参与大小比较
        int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
        int nums1RightMin = i == m ? Integer.MAX_VALUE : nums1[i];
        int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
        int nums2RightMin = j == n ? Integer.MAX_VALUE : nums2[j];
        if ((m + n) % 2 == 1) {
            return Math.max(nums1LeftMax, nums2LeftMax);
        }else{
            return (double) (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin)) / 2;
        }

    }



    public static void main(String[] args) {
        LeetCodeHot68 leetCodeHot68 = new LeetCodeHot68();
        leetCodeHot68.findMedianSortedArrays2(new int[]{2,2,4,4}, new int[]{2,2,2,4,4});
    }
}
