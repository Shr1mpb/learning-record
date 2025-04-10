package LeetCodeHot;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LeetCodeHot18 {
    /**
     * 矩阵置零
     * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
     *
     * 时间复杂度 O(mn) 空间复杂度O(m+n) 、 O(1)
     */
    //wrong
    public void setZeroes0(int[][] matrix) {
        Set<Integer> flagr = new HashSet<>();
        Set<Integer> flagc = new HashSet<>();

        int r = 0;//行
        while (r != matrix.length ) {
            int c = 0;//列
            while (c != matrix[0].length ) {
                if (matrix[r][c] == 0 && !flagr.contains(r) && !flagc.contains(c)) {
                    for (int i = 0; i < matrix[0].length; i++) {
                        matrix[r][i] = 0;
                    }
                    for (int j = 0; j < matrix.length; j++) {
                        matrix[j][c] = 0;
                    }
                    flagc.add(c);
                    flagr.add(r);
                }
                c++;
            }
            r++;
        }
    }

    //该方法用第一行第一列作为标志位，仅用了额外的 O(1) 复杂度 用的是矩阵本身自己的复杂度
    public void setZeroes(int[][] matrix) {
            int row = matrix.length;
            int col = matrix[0].length;
            boolean row0_flag = false;
            boolean col0_flag = false;
            // 第一行是否有零
            for (int j = 0; j < col; j++) {
                if (matrix[0][j] == 0) {
                    row0_flag = true;
                    break;
                }
            }
            // 第一列是否有零
            for (int i = 0; i < row; i++) {
                if (matrix[i][0] == 0) {
                    col0_flag = true;
                    break;
                }
            }
            // 把第一行第一列作为标志位
            for (int i = 1; i < row; i++) {
                for (int j = 1; j < col; j++) {
                    if (matrix[i][j] == 0) {
                        matrix[i][0] = matrix[0][j] = 0;
                    }
                }
            }
            // 置0
            for (int i = 1; i < row; i++) {
                for (int j = 1; j < col; j++) {
                    if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }
            if (row0_flag) {
                for (int j = 0; j < col; j++) {
                    matrix[0][j] = 0;
                }
            }
            if (col0_flag) {
                for (int i = 0; i < row; i++) {
                    matrix[i][0] = 0;
                }
            }

    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,1,1}, {1,0,1}, {1,1,1}};
        new LeetCodeHot18().setZeroes(matrix);
        System.out.println(System.getProperty("user.dir"));//输出 D:\JavaProjects\Common
    }
}
