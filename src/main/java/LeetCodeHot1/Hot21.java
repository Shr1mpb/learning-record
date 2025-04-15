package LeetCodeHot1;

public class Hot21 {
    // 搜索二维矩阵 II
    public boolean searchMatrix(int[][] matrix, int target) {
        // 看成二叉树 右上角为根
        int m = 0;
        int n = matrix[0].length - 1;
        while (m <= matrix.length - 1 && n >= 0) {
            if (matrix[m][n] == target) {
                return true;
            }
            if (matrix[m][n] > target) {
                n--;
            }else{
                m++;
            }
        }
        return false;
    }
}
