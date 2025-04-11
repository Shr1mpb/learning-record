package LeetCodeHot1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Hot14 {
    // 合并区间
    // 通过 但较慢
    public int[][] merge0(int[][] intervals) {
        // 先排序 按区间起点升序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < intervals.length - 1; i++) {// 已并入其他的数组 起点置为-1
            if (intervals[i][0] == -1) continue;
            for (int j = i + 1; j < intervals.length; j++) {
                if (intervals[i][0] == -1) {
                    continue;
                }
                if (intervals[j][0] <= intervals[i][1]
//                        && intervals[j][0] >= intervals[i][0] 这句不需要 因为j>i 而且前面已经排过序
                ) {// 在范围内
                    intervals[i][1] = Math.max(intervals[j][1], intervals[i][1]);// 设置范围
                    intervals[j][0] = -1;// 设置被并入
                }
            }
        }
        List<int[]> retTemp = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[0] != -1) {
                retTemp.add(interval);
            }
        }
        return retTemp.toArray(new int[retTemp.size()][]);
    }

    // 时间复杂度低一些的算法(差不多)
    public int[][] merge(int[][] intervals) {
        //记录末尾数组和初始数组
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> merged = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            int L = interval[0], R = interval[1];
            if (merged.isEmpty() || merged.getLast()[1] < L) {// 空或者不在区间里 添加进入merge
                merged.add(new int[]{L, R});
            } else {// 在区间里 肯定是和之前是连续的(因为已按interval[][0]第一位排序)
                merged.getLast()[1] = Math.max(merged.getLast()[1], R);// 更新右区间
            }
        }
        return merged.toArray(new int[merged.size()][]);

    }
}

