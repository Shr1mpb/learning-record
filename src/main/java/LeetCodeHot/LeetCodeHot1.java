package LeetCodeHot;

import java.util.ArrayList;
import java.util.HashMap;

public class LeetCodeHot1 {
    /**
     * 两数之和
     * 时间复杂度O(1)  这里告诉我们 如果有两个确定的量 用他们去检不确定的量效率会大大提高eg：此处target和当前位置的数字确定，然后去找那个不确定的
     * 而不是遍历不确定的数(此处为m集合) 去一个一个找看看有没有符合的
     * 因为contains和get都是根据hash算法算出 取出元素的复杂度是O(1)
     * @param nums 传入的数组
     * @param target 目标和
     * @return 返回数组的下标
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (m.containsKey(target - nums[i])) {
                return new int[]{m.get(target - nums[i]), i};
            }else {
                m.put(nums[i], i);
            }
        }
        return null;
    }

    /**
     * 时间复杂度O(n²)
     * 涉及到数组扩容/遍历数组
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (arrayList.contains(target - nums[i])) {
                return new int[]{arrayList.indexOf(target - nums[i]), i};
            }else {
                arrayList.add(nums[i]);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        LeetCodeHot1 leetCodeHot1 = new LeetCodeHot1();
        int[] nums = new int[]{2, 7, 11, 15};
        int target =  9;
        int[] ints = leetCodeHot1.twoSum(nums, target);
        for (int out : ints){
            System.out.print(out + "\t");
        }
    }
}
