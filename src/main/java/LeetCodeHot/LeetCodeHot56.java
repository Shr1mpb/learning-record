package LeetCodeHot;

import java.util.ArrayList;
import java.util.List;

public class LeetCodeHot56 {
    /**
     * 子集
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     */
     /*
    和全排列类似，但是比全排列简单，
    全排列需要考虑那些元素已经放入了，所以需要动态维护一个集合，
    但是本题完全可以按照着数组的顺序来递归,如下图，遍历到最后一个元素时会自动不进入for循环，不用担心会有重复集合
    回溯题目的关键是在
                    找到合适的树形结构
     123
    / | \
    1 2 3
    /\ \
    2 3 3
   /
   3
     */
    //可能的子集
    private List<List<Integer>> res;
    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        backTracking(nums, 0, new ArrayList<>());
        return res;
    }

    /**
     *
     * @param nums 源数组，不需要动态维护
     * @param index 往集合中开始插入的位置
     * @param list 用于存放各种子集
     */
    public void backTracking(int[] nums,int index,List<Integer> list) {
        //任何一个能进入递归的都一定是一个正确的集合，因为源数组每个元素都不相同，并且1只能和2或3组成集合
        // ,2只能和3组成集合,3只能和自己，所以进入的一定是唯一的
        if (true) {
            res.add(new ArrayList<>(list));
//            return;在生成子集的回溯实现中，每一个可以进入递归的集合都代表一个有效的子集，需要将其添加到结果列表。没有条件性返回的必要
        }

        //按层遍历
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            backTracking(nums,i+1,list);//递归
            list.remove(list.size()-1);//回溯
        }
    }
    //例如本题 对应树形结构 进入顺序为 【】 1 12 123 23 23 3
    //2025.3.19理解 ： 深度优先的递归是只有一个返回结果，而回溯是找到所有的返回结果
    //所以回溯要多带一个变量 在本题中为list 上题中为cur 代表现在的答案
    //每次递归不是简单的if满足return 而是把满足条件时的结果添加到结果集中
    //之后递归开始往回走了，由于普通的dfs没有多带的那个变量，自然而然的就消失了
    //但是回溯要用多带的变量存储结果 所以在递归结束时应该进行回溯，把刚刚结束的一层递归的数据删除掉
    public static void main(String[] args) {
        LeetCodeHot56 leetCodeHot56 = new LeetCodeHot56();
        leetCodeHot56.subsets(new int[]{1, 2, 3});
    }
}
