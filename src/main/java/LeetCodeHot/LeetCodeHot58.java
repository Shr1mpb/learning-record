package LeetCodeHot;

import java.util.*;

public class LeetCodeHot58 {
    /**
     * 组合总和
     * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
     * 找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
     * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
     * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
     *
     */
    List<Integer> cur = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        quickSort(candidates);//这里为了练习 自己重写了一下quickSort
        traceback(candidates, target, 0);
        //去重：
        List<List<Integer>> uniqueList = res.stream().distinct().toList();
        return uniqueList;
    }

    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        quickSort(candidates);
        traceback1(candidates, target, 0, 0);
        return res;
    }

    public void traceback(int[] candidates, int target, int sum) {
        if (sum == target) {
            //去重会用到，要保持有序
            cur.sort(Comparator.comparingInt(c -> c));
            res.add(cur.stream().toList());//把cur复制一份赋值给结果
            return;
        }else if (sum > target) {
            return;
        }

        //每层都是candidates，每层都要深度优先
        for (int candidate : candidates) {
            if (candidate + sum <= target) {
                sum += candidate;
                cur.add(candidate);
                traceback(candidates, target, sum);
                sum -= candidate;
                cur.remove((Object)(Integer)candidate);
            } else {
                break;
            }
        }


    }

    //这个是看了去重的方法后自己改的
    public void traceback1(int[] candidates, int target, int sum,int index) {
        if (sum == target) {
            //去重会用到，要保持有序
            res.add(cur.stream().toList());//把cur复制一份赋值给结果
            return;
        }else if (sum > target) {
            return;
        }

        //每层都是candidates，每层都要深度优先
        for (int i = index;i < candidates.length;i++) {
            if (candidates[i] + sum <= target) {
                sum += candidates[i];
                cur.add(candidates[i]);
                traceback1(candidates, target, sum, i);
                sum -= candidates[i];
                cur.removeLast();
            } else {
                break;
            }
        }


    }

    public static void quickSort(int[] nums) {
        quickSortHelper(nums, 0, nums.length - 1);
    }

    public static void quickSortHelper(int[] nums, int low, int high) {
        if (low < high) {
            int pivotIndex = paration(nums, low, high);
            quickSortHelper(nums, low, pivotIndex - 1);
            quickSortHelper(nums, pivotIndex + 1, high);
        }
    }
    public static int paration(int[] nums, int low, int high) {
        int cur = nums[low];
        int left = low + 1;
        int right = high;
        while (left <= right) {
            while(left <= right && nums[left] < cur) {
                left++;
            }
            while(left <= right && nums[right] > cur) {
                right--;
            }
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        swap(nums, low, right);
        return right;
    }
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }



    //优化后的解法 不保存sum而是修改target
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, 0, target, new ArrayList<>(), res);
        return res;
    }

    void backtrack(int[] candidates, int index, int target, List<Integer> list, List<List<Integer>> res) {
        if(0 == target) {
            res.add(new ArrayList<>(list));//复制List类型的方法之一
        } else for (int i = index; i < candidates.length; ++i) {
            if(target < candidates[i]) continue;//剪枝
            list.add(candidates[i]);
            backtrack(candidates, i, target - candidates[i], list, res);//这里优化最大：下一次循环不需要遍历之前的，直接从本身开始向后遍历，即可去重
            list.remove(list.size() - 1); //回溯
        }
    }

    public static void main(String[] args) {
        LeetCodeHot58 leetcode = new LeetCodeHot58();
        leetcode.combinationSum(new int[]{2, 3, 5}, 8);
        System.out.println();
    }

}
