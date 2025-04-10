package LeetCodeHot1;

import java.util.*;

public class Hot6 {
    // 三数之和
    // 类两数之和解法 时间复杂度不够看
    public List<List<Integer>> threeSum0(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Set<List<Integer>> temp = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int now = nums[i];
            int target = 0 - now;
            HashMap<Integer,Integer> map = new HashMap<>();
            for (int j = i + 1; j < nums.length; j++) {
                if (map.containsKey(target - nums[j])) {
                    List<Integer> num = new ArrayList<>(List.of(nums[i], nums[map.get(target - nums[j])], nums[j]));
                    num.sort(Integer::compareTo);
                    if (temp.contains(num)) {
                        continue;
                    }
                    res.add(num);
                    temp.add(num);
                }else{
                    map.put(nums[j],j);
                }
            }
        }
        return res;
    }

    // 双指针
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> set = new ArrayList<>();
        HashSet<Integer> set1 = new HashSet<>();// 用于防止第一个数字重复
        for (int i = 0; i < nums.length - 2; i++) {
            if (set1.contains(nums[i])) {
                continue;
            }
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    set1.add(nums[i]);
                    set.add(List.of(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;// right/left跳过相同
                    left++;
                    right--;
                }
                if (sum > 0) {
                    right--;
                }
                if (sum < 0) {
                    left++;
                }
            }
        }
        return set;
    }

    public static void main(String[] args) {
        Hot6 hot6 = new Hot6();
        System.out.println(hot6.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
