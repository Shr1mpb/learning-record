package LeetCodeHot1;

import java.util.Arrays;

public class Hot18 {
    // 矩阵置零
    /*
           有0的行列都置为0
           用O(1)的空间 - > 标记行列 最后再设置
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 看看标记列本身带不带0
        boolean row = false;
        boolean column = false;
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) {
                row = true;
                break;
            }
        }
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                column = true;
                break;
            }
        }
        // 遍历数组 找到有0的位置 把对应的行列的第一个元素置为特殊值(这里置为0即可)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        // 开始设置 遍历第一行和第一列即可
        // 注意：遍历第一行的时候 不能设置第一列的东西 不然会影响第一列的遍历
        //      遍历第一列的时候 不能设置第一行的东西 逻辑不对
        for (int i = 1; i < n; i++) {// 第一行
            if (matrix[0][i] == 0) {
                for (int j = 0; j < m; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {// 第一列
            if (matrix[i][0] == 0) {
                Arrays.fill(matrix[i], 0);
            }
        }

        // 单独处理标记行列
        if (column){
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
        if (row){
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Hot18 hot18 = new Hot18();
        hot18.setZeroes(new int[][]{{1, 2, 3, 4}, {5, 0, 7, 8}, {0, 10, 11, 12}, {13, 14, 15, 0}});
    }
}
