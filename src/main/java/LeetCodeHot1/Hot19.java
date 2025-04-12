package LeetCodeHot1;

import java.util.ArrayList;
import java.util.List;

public class Hot19 {
    // 螺旋矩阵
    /*
        顺时针旋转返回矩阵元素
     */
    private int[][] matrix;
    private int[][] alreadyOut;
    private int alreadyOutCount;
    private List<Integer> result;
    public List<Integer> spiralOrder(int[][] matrix) {
        //获取几行几列
        int row = matrix.length;
        int column = matrix[0].length;
        this.alreadyOutCount = row * column;
        this.matrix = matrix;
        //标记“已输出”的数组
        this.alreadyOut = new int[row][column];
        //存储结果
        this.result = new ArrayList<>();
        //开始执行
        //上右下左 0123
        deepSeek(0, 0, 1);
        //返回结果
        return result;
    }

    private void deepSeek(int r, int c,int dir) {
        //没访问过 标记访问并加到结果中 已输出结果+1
        if (alreadyOut[r][c] == 0) {
            alreadyOut[r][c] = 1;
            result.add(matrix[r][c]);
            alreadyOutCount--;
        }

        //全部输出 直接结束
        if (alreadyOutCount == 0) {
            return;
        }
        if (dir == 1) {//右边走，走不动拐下
            if (alreadyOut[r][Math.min(matrix[0].length - 1, c + 1)] == 0) {
                deepSeek(r, Math.min(matrix[0].length - 1, c + 1), 1);
            }else{
                deepSeek(Math.min(r + 1, matrix.length - 1), c, 2);
            }
        }else if (dir == 2){//下边走，走不动拐左
            if (alreadyOut[Math.min(r + 1, matrix.length - 1)][c] == 0) {
                deepSeek(Math.min(r + 1, matrix.length - 1), c, 2);
            }else{
                deepSeek(r, Math.max(0, c - 1), 3);
            }
        }else if (dir == 3){//左边走，走不动拐上
            if (alreadyOut[r][Math.max(0, c - 1)] == 0) {
                deepSeek(r, Math.max(0, c - 1), 3);
            }else {
                deepSeek(Math.max(0, r - 1), c, 0);
            }
        } else if (dir == 0) {//上边走，走不动拐右
            if(alreadyOut[Math.max(0, r - 1)][c] == 0){
                deepSeek(Math.max(0, r - 1), c, 0);
            }else{
                deepSeek(r, Math.min(matrix[0].length - 1, c + 1), 1);
            }
        }



    }


    public static void main(String[] args) {
        Hot19 hot19 = new Hot19();
        hot19.spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
    }
}
