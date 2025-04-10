package LeetCodeHot;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCodeHot51 {
    /**
     * 岛屿数量
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * 此外，你可以假设该网格的四条边均被水包围。
     */
    //自己做的 时间、空间复杂度都很差
    private int[][] visited;
    public int numIslands(char[][] grid) {
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

    //参考题解 用的是dfs
    //搜索每个岛屿 上下左右
    void dfs(char[][] grid,int r,int c){
        int nr = grid.length;
        int nc = grid[0].length;
        if(r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] <= '0'){
            return;
        }
        grid[r][c] = '0';//设置为水 表示已经遍历过了
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }
    public int numIslands2(char[][] grid) {
        //总体思想一样 找到一个点，就把它相邻的全都遍历完
        //这样的思想，遍历整个岛的所有点
        if(grid == null || grid.length == 0){
            return 0;
        }
        int numIsland = 0;
        int nr = grid.length;
        int nc = grid[0].length;
        for(int r = 0; r < nr; ++r){
            for(int c = 0; c < nc; ++c){
                if(grid[r][c] == '1'){
                    numIsland++;
                    dfs(grid,r,c);
                }
            }
        }
        return numIsland;
    }



    public static void main(String[] args) {
        LeetCodeHot51 instance = new LeetCodeHot51();
        System.out.println(instance.numIslands2(new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}}));
    }
}
