package LeetCodeHot;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class LeetCodeHot17 {
    /**
     * 缺失的第一个整数
     * @param nums 未排序的整数数组nums
     * @return 找出没有出现的最小正整数
     */
    //方法：将数组放在哈希表里 然后从1开始枚举正整数直至出现为止
    public int firstMissingPositive(int[] nums) {
        Set<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            if (!hashSet.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 4, -1, 1};
        System.out.println(new LeetCodeHot17().firstMissingPositive(nums));

    }

}
