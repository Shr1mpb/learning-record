package LeetCodeHot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LeetCodeHot14 {
    /**
     * 合并区间
     * @param intervals 给定一个数组表示若干区间的集合 合并所有的区间
     * @return 返回合并的区间数组
     */
    //思路 排序
    public int[][] merge(int[][] intervals) {
        //记录末尾数组和初始数组

        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            int L = interval[0], R = interval[1];
            if (merged.isEmpty() || merged.getLast()[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);

    }

    public static void main(String[] args) {
        LeetCodeHot14 leetCodeHot14 = new LeetCodeHot14();
        int[][] intervals = new int[][]{new int[]{1, 3}, new int[]{2, 6}, new int[]{8, 10}, new int[]{15, 18}};

        System.out.println(Arrays.deepToString(leetCodeHot14.merge(intervals)));
    }
}
