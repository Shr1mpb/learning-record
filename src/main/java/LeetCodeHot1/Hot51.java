package LeetCodeHot1;

import java.util.LinkedList;
import java.util.Queue;

public class Hot51 {
    // 岛屿数量
    // 思路1：对每一个点都进行广度优先搜索 搜索过的点标记为已搜索 搜索完成后把岛屿数量+1
    private int[][] visited;
    public int numIslands0(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int length = grid.length;
        int length0 = grid[0].length;
        visited = new int[length][length0];//标记是否被访问过
        int count = 0;//岛屿数量
        //广度优先搜索 每个节点都进行 第一个节点被进行时岛屿数量+1
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(visited[i][j] == 0 && grid[i][j] == '1') {
                    count++;
                    visited[i][j] = 1;
                    //向上下左右都进行遍历 如果有就标记已访问
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        int currX = curr[0];
                        int currY = curr[1];

                        int temp0 = Math.max(currY - 1, 0);
                        if (visited[currX][temp0] == 0 && grid[currX][temp0] == '1') {//左
                            visited[currX][temp0] = 1;
                            queue.add(new int[]{currX, temp0});
                        }
                        int temp1 = Math.min(currY + 1, grid[0].length - 1);
                        if (visited[currX][temp1] == 0 && grid[currX][temp1] == '1') {//右
                            visited[currX][temp1] = 1;
                            queue.add(new int[]{currX, temp1});
                        }
                        int temp2 = Math.max(currX - 1, 0);
                        if (visited[temp2][currY] == 0 && grid[temp2][currY] == '1') {//上
                            visited[temp2][currY] = 1;
                            queue.add(new int[]{temp2, currY});
                        }
                        int temp3 = Math.min(currX + 1, grid.length - 1);
                        if (visited[temp3][currY] == 0 && grid[temp3][currY] == '1') {//下
                            visited[temp3][currY] = 1;
                            queue.add(new int[]{temp3, currY});
                        }
                    }
                }
            }
        }
        return count;
    }

    // 思路2：与1一致，但对每个点进行深度优先搜索
    private static final int[][] DIR = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int m;
    private int n;
    public int numIslands(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == '1'){
                    dfs(i, j, grid);
                    cnt++;
                }
            }
        }
        return cnt;
    }
    private void dfs(int i, int j, char[][] grid){
        grid[i][j] = '2';// 搜索到的点置为2

        for (int di = 0; di <= 3; di++) {// 向四面继续深度优先搜索
            int x = i + DIR[di][0];
            int y = j + DIR[di][1];

            if(x >= 0 && x < m && y >= 0 && y <n && grid[x][y] != '2'  && grid[x][y] != '0'){
                dfs(x, y, grid);
            }
        }

    }

    public static void main(String[] args) {
        Hot51 hot51 = new Hot51();
        System.out.println(
                hot51.numIslands0(new char[][]{
                        {'1', '1', '1', '1', '0'},
                        {'1', '1', '0', '1', '0'},
                        {'1', '1', '0', '0', '0'},
                        {'0', '0', '0', '0', '0'},
                })
        );
    }
}
