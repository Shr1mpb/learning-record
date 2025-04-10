package LeetCodeHot;


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class LeetCodeHot21 {
    /**
     * 搜索二维矩阵
     * 看作二叉树 逐步缩小矩阵 这里称Z字形搜索(一边升序一边降序，然后缩小范围)
     * 时间复杂度O(m+n)
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     * @param matrix 给定矩阵
     * @param target 给定值
     * @return 返回是否搜索到
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        //看成二叉树 右上角是根 下右子树，左左子树
        //然后逐步缩小矩阵(缩小二叉树范围)，直到找不到为止
        int row = matrix.length;
        int column = matrix[0].length;
        int x = 0;
        int y = column - 1;
        while (x < row && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            }
            if (matrix[x][y] > target) {
                y--;
            } else {
                x++;
            }
        }
        return false;
    }


}
