package LeetCodeHot1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hot58 {
    // 组合总和
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        insertionSort(candidates);// 排序输入
        List<List<Integer>> res = new ArrayList<>();
        backtracking(res, new ArrayList<>(), 0, candidates, target,0);
        return res;
    }

    private void backtracking(List<List<Integer>> res, List<Integer> cur, int sum, int[] candidates,int target,int index) {
        if (sum > target) {// 超过 不继续
            return;
        }
        if (sum == target){// 等于 添加进入
            res.add(cur.stream().toList());
            return;
        }
        // 小于 继续加数
        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] + sum <= target) {
                cur.add(candidates[i]);
                sum += candidates[i];
                backtracking(res, cur, sum, candidates, target,i);
                cur.remove(cur.size() - 1);
                sum -= candidates[i];
            }
            else{
                break;
            }

        }
    }



    private void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i];
            int j = i - 1;
            for (; j >= 0 && nums[j] > cur; j--) {
                nums[j + 1] = nums[j];
            }
            nums[j + 1] = cur;
        }
    }

    public static void main(String[] args) {
        Hot58 hot58 = new Hot58();
        int[] ints = {4,9,1,2,4,5,6,3,7,88,55,62,72,81};
        hot58.insertionSort(ints);
        System.out.println(Arrays.toString(ints));
        System.out.println("===验证插入排序完成===");

        int[] input = new int[]{2, 3, 5};
        List<List<Integer>> lists = hot58.combinationSum(input, 8);
        System.out.println(lists.toString());
    }
}
