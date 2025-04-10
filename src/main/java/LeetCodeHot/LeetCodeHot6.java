package LeetCodeHot;

import java.util.*;
import java.util.stream.Collectors;

public class LeetCodeHot6 {
    /**
     * 三数之和
     * @param nums 判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。
     * @return 请你返回所有和为 0 且不重复的三元组。
     */

    //此处就当练手 写了一个返回所有索引组合的结果 后续又改成了和题目相同的返回结果
    //此方法超时了
    public List<List<Integer>> threeSum2(int[] nums) {
        HashMap<Integer,List<Integer>> sums = new HashMap<>();
        List<List<Integer>> ret = new ArrayList<>();//存储返回结果
        //计算全部的两数和并存储到sums中
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (sums.containsKey(sum)) {
                    List<Integer> indexes = sums.get(sum);
                    indexes.add(i);
                    indexes.add(j);
                }else {
                    List<Integer> indexes = new ArrayList<>();
                    indexes.add(i);
                    indexes.add(j);
                    sums.put(sum, indexes);
                }
            }
        }
        //从sums取出所有的key 再遍历看看能不能和第三个数组成0
        Set<Integer> integers = sums.keySet();
        for (int sum2 : integers) {
            for (int u = 0;u<nums.length;u++) {
                int num = nums[u];
                if (sum2 + num == 0) {
                    List<Integer> indexes = sums.get(sum2);
                    int loop = indexes.size() / 2;
                    int index = 0;
                    for (int i = 0; i < loop; i++) {
                        if (indexes.get(index * 2) == u || indexes.get(index * 2 + 1) == u) {
                            index++;
                        } else {
                            List<Integer> list = new ArrayList<>();//存储当组元素
                            //重复索引不添加
                            list.add(u);
                            list.add(indexes.get(index * 2));
                            list.add(indexes.get(index * 2 + 1));
                            Collections.sort(list);
                            if (!ret.contains(list)) {
                                ret.add(list);
                            }
                            index++;
                        }
                    }
                }

            }
        }
        //过滤掉有相同数字结果的东西
        /*
        这样就不会修改ret的内容
        ret.stream().filter((list) -> {
            list.replaceAll(integer -> nums[integer]);
            return true;
        });
        而这样会
        ret.stream().filter((list) -> {
            list.replaceAll(integer -> nums[integer]);
            return true;
        }).toList();   //toList触发了流的执行 流的方法有中间操作和终端操作 forEach()、collect()、reduce()、count()等都是终端操作，会执行流
        */
        ret.forEach((list) -> {
            list.replaceAll(integer -> nums[integer]);
        });
//        ret.forEach((list)->{Collections.sort(list);});
        ret.forEach(Collections::sort);
        Set<List<Integer>> collect = ret.stream().collect(Collectors.toSet());
        List<List<Integer>> list = collect.stream().toList();
        ret = list;

        return ret;
    }

    /**
     * 同样，超时
     * 看成 类似于HOT1的 "两数之和" 问题
     */
    public List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int target = -nums[i];
            HashSet<Integer> map = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                if (map.contains(target - nums[j])) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(target - nums[j]);
                    list.add(nums[j]);
                    if (!ret.contains(list)) {
                        ret.add(list);
                    }
                    while(j < nums.length-1 && nums[j] == nums[j+1]){
                        j++;
                    }
                }else{
                    map.add(nums[j]);
                }
            }
            while (i < nums.length - 3 && nums[i] == nums[i+1]){
                i++;
            }
        }
        return ret;
    }
    //双指针解法

    /**
     *  双指针：排序后设 左指针、右指针
     *  算法类似，优化的地方在于，双指针一个从右向左遍历，对于排好序的nums大大提高效率
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] + nums[i] == 0) {
                    List<Integer> answer = new ArrayList<>();
                    answer.add(nums[i]);
                    answer.add(nums[left++]);
                    answer.add(nums[right--]);
                    result.add(answer);
                    while (left < nums.length - 1 && nums[left - 1] == nums[left]) left++;
                    while (right > i && nums[right] == nums[right + 1]) right--;
                } else if (nums[left] + nums[right] + nums[i] < 0){
                    left++;
                } else {
                    right--;
                }
            }
            while(i < nums.length - 2 && nums[i] == nums[i + 1]) i++;
        }
        return result;
    }


    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        LeetCodeHot6 leetCodeHot6 = new LeetCodeHot6();
        System.out.println(leetCodeHot6.threeSum(nums));
    }
}
