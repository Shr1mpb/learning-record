package LeetCodeHot;

import java.util.*;

public class LeetCodeHot53 {
    /**
     * 课程表你
     * 这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
     * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，
     * 其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
     * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
     * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false
     */

    //看了视频讲解 有两种思路
    //一种是BFS 从入度出发 把入度为0的课程加入队列，然后将它的下面的课的入度相应减少
    //以此循环，到没有入度被减 学完了所有课程
    //另一种是DFS 先放最深的节点，即将出度为0的节点依次入栈 之后再从栈中取出课程
    //下面是官方的BFS解法

    List<List<Integer>> edges; // 邻接表，用于表示课程之间的依赖关系 存储的是出去的边 用于后续减去出去边的入度
    int[] indeg; // 保存每个课程的入度（依赖的课程数量）

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 初始化邻接表
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<>()); // 为每门课程初始化一个空列表
        }
        // 初始化每门课程的入度为0
        indeg = new int[numCourses];

        // 建立邻接表并计算每个课程的入度
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]); // info[1] 是先修课程, info[0] 是后续课程
            ++indeg[info[0]]; // 后续课程的入度加1
        }

        // 使用队列来进行拓扑排序
        Queue<Integer> queue = new LinkedList<>();
        // 将所有入度为0的课程加入队列（这些课程可以先学习）
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }

        int visited = 0; // 记录已访问的课程数量
        // 进行BFS，即广度优先搜索
        while (!queue.isEmpty()) {
            ++visited; // 每次从队列中取出一门课程即表示访问了它
            int u = queue.poll(); // 取出队列首元素，u为当前课程
            // 遍历u先修后可修的课程
            for (int v : edges.get(u)) {
                --indeg[v]; // 将后续课程的入度减1
                if (indeg[v] == 0) { // 如果入度变为0，说明可以学习这门课程
                    queue.offer(v); // 将可以学习的课程加入队列
                }
            }
        }

        // 如果访问的课程数量等于总课程数量，则返回true，表示可以完成所有课程
        return visited == numCourses;
    }


}
