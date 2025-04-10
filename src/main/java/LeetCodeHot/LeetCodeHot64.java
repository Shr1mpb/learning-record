package LeetCodeHot;

public class LeetCodeHot64 {
    /**
     * 搜索二维矩阵
     * 给你一个满足下述两条属性的 m x n 整数矩阵：
     * 每行中的整数从左到右按非严格递增顺序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int maxRow = matrix.length - 1;
        int minRow = 0;
        int left = minRow;
        int right = maxRow;
        int colNum = matrix[0].length;
        int findRow = -1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[mid][0] <= target && matrix[mid][colNum-1] >= target) {
                findRow = mid;
                break;
            }
            if (matrix[mid][0] > target) {
                right = mid - 1;
            }
            if (matrix[mid][colNum-1] < target) {
                left = mid + 1;
            }
        }
        if (findRow == -1) {
            return false;
        }
        left = 0;
        right = colNum - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[findRow][mid] == target) {
                return true;
            }
            if (matrix[findRow][mid] < target) {
                left = mid + 1;
            }
            if (matrix[findRow][mid] > target) {
                right = mid - 1;
            }
        }
        return false;
    }


    //优化写法：二维数组看成递增的一维数组
    public boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m * n - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (matrix[mid / n][mid % n] == target) {
                return true;
            } else if (matrix[mid / n][mid % n] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        LeetCodeHot64 leetCodeHot64 = new LeetCodeHot64();
        leetCodeHot64.searchMatrix(new int[][]{{1, 3}}, 3);
        System.out.println();
    }

}
