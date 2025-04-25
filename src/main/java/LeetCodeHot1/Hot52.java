package LeetCodeHot1;

import java.util.LinkedList;
import java.util.Queue;

public class Hot52 {
    // 腐烂的橘子
    // 这里只能用广度优先搜索，每次广度优先算一分钟
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 先看看有多少腐烂的橘子和新鲜的橘子
        int rotten = 0;
        int fresh = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {// 腐烂
                    rotten++;
                    queue.add(new int[]{i, j});
                }
                if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        // 如果没有新鲜橘子 返回0 已全腐烂或压根没橘子
        if (fresh == 0) {
            return 0;
        }
        // 如果没有腐烂的橘子 但是有新鲜橘子 返回-1 即永远无法完全腐烂
        if (rotten == 0){
            return -1;
        }
        // 开始腐烂
        int res = 0;
        int expectedRottenCount = queue.size();// 这一轮要发生腐烂的个数
        int rottenCount = 0;// 现在腐烂了的轮数
        int currentRottenOrNot = 0;// 本轮是否发生过腐烂 如果没有发生过 那就是已经没了 不用再加分钟了
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            // 腐烂四周的新鲜橘子 并把周边的橘子加入队列中
            // 腐烂的时候更新新鲜橘子的个数 便于后续统计是否有不会腐烂的橘子
            int temp0 = Math.min(x + 1, m-1);
            if (grid[temp0][y] == 1) {
                grid[temp0][y] = 2;
                queue.add(new int[]{temp0, y});
                fresh--;
                currentRottenOrNot++;
            }
            int temp1 = Math.min(y + 1, n-1);
            if (grid[x][temp1] == 1) {
                grid[x][temp1] = 2;
                queue.add(new int[]{x, temp1});
                fresh--;
                currentRottenOrNot++;
            }
            int temp2 = Math.max(x - 1, 0);
            if (grid[temp2][y] == 1) {
                grid[temp2][y] = 2;
                queue.add(new int[]{temp2, y});
                fresh--;
                currentRottenOrNot++;
            }
            int temp3 = Math.max(y - 1, 0);
            if (grid[x][temp3] == 1) {
                grid[x][temp3] = 2;
                queue.add(new int[]{x, temp3});
                fresh--;
                currentRottenOrNot++;
            }

            // 到达腐烂轮数 重置变量 并让时间+1
            if (++rottenCount == expectedRottenCount && currentRottenOrNot > 0) {
                rottenCount = 0;
                expectedRottenCount = queue.size();
                res++;
                currentRottenOrNot = 0;
            }

        }

        // 有没有腐烂的橘子
        if (fresh != 0) {
            return -1;
        }
        return res;
    }

    public static void main(String[] args) {
        Hot52 hot52 = new Hot52();
        System.out.println(hot52.orangesRotting(new int[][]{
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        }));

    }
}
