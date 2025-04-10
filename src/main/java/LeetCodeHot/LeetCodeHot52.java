package LeetCodeHot;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCodeHot52 {
    /**
     * 腐烂的橘子
     * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
     * 值 0 代表空单元格；
     * 值 1 代表新鲜橘子；
     * 值 2 代表腐烂的橘子。
     * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
     * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
     */
    public int orangesRotting(int[][] grid) {
        int nr = grid.length;
        int nc = grid[0].length;
        int normalCount = 0;//记录普通橘子的个数
        Queue<int[]> list = new LinkedList<>();//记录腐烂的橘子
        for (int i = 0; i < nr; i++) {
            for (int j = 0; j < nc; j++) {
                if (grid[i][j] == 1) {
                    normalCount++;
                }
                if (grid[i][j] == 2) {
                    list.add(new int[]{i, j});
                }
            }
        }
        if (normalCount == 0) return 0;//没有普通橘子 说明全腐烂了 返回0
        if (list.isEmpty()) return -1;//没有腐烂的橘子 说明不会腐烂 返回-1
        //开始腐烂
        //思路：从队列中每次取出上次的腐烂的橘子，去腐烂四周的橘子
        int minutes = 0;//记录腐烂用的步数(while循环次数)
        while (!list.isEmpty()) {
            int size = list.size();
            int check = 0;//每次队列进行，应该都有被腐烂的橘子才对，让每次橘子腐烂check都+1 最后check>0就是有橘子被腐烂
            for (int i = 0; i < size; i++) {
                int[] poll = list.poll();
                int currX = poll[0];
                int currY = poll[1];
                //取出的一定是烂了的橘子 看看四周有没有好橘子
                //如果有，就腐烂并加入队列，并让新鲜橘子数-1
                int temp0 = Math.max(currY - 1, 0);
                if (grid[currX][temp0] == 1) {//左
                    grid[currX][temp0] = 2;
                    list.add(new int[]{currX, temp0});
                    normalCount--;
                    check++;
                }
                int temp1 = Math.min(currY + 1, grid[0].length - 1);
                if (grid[currX][temp1] == 1) {//右
                    grid[currX][temp1] = 2;
                    list.add(new int[]{currX, temp1});
                    normalCount--;
                    check++;
                }
                int temp2 = Math.max(currX - 1, 0);
                if (grid[temp2][currY] == 1) {//上
                    grid[temp2][currY] = 2;
                    list.add(new int[]{temp2, currY});
                    normalCount--;
                    check++;
                }
                int temp3 = Math.min(currX + 1, grid.length - 1);
                if (grid[temp3][currY] == 1) {//下
                    grid[temp3][currY] = 2;
                    list.add(new int[]{temp3, currY});
                    normalCount--;
                    check++;
                }
            }
            //如果最后check等于0说明这次没有腐烂，以后也不会再腐烂，并且normalCount ！= 0的话，那么说明有不能被腐烂的 返回-1
            if (check == 0 && normalCount != 0) return -1;
            //有橘子被腐烂，分钟数+1
            if (check > 0) {
                minutes++;
            }

        }
        return minutes;
    }

    public static void main(String[] args) {
        LeetCodeHot52 solution = new LeetCodeHot52();
        System.out.println(solution.orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
    }

}
