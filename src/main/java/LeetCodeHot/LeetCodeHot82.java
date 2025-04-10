package LeetCodeHot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LeetCodeHot82 {
    /**
     * 杨辉三角
     * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
     */
    // 杨辉三角每一行的数字都是二项式系数
    public List<List<Integer>> generate(int numRows) {
        // C00 C10 C11 C20 C21 C22 ...
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (numRows == 1) {
            res.add(Arrays.asList(1));
            return res;
        }
        if (numRows == 2) {
            res.add(Arrays.asList(1));
            res.add(Arrays.asList(1, 1));
            return res;
        }
        res.add(Arrays.asList(1));
        res.add(Arrays.asList(1, 1));
        for (int i = 3; i <= numRows; i++) {
            int temp = i - 1;// 本行数字是 C temp 0~temp 一共i个数字 temp是当前行的编号
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == i - 1) {
                    list.add(1);
                    continue;
                }
                list.add(res.get(temp - 1).get(j - 1) + res.get(temp - 1).get(j));

            }
            res.add(list);
        }
        return res;

    }

    public static void main(String[] args) {
        LeetCodeHot82 leetCodeHot82 = new LeetCodeHot82();
        leetCodeHot82.generate(5);
    }



}
