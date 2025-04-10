package LeetCodeHot;

import java.util.*;

public class LeetCodeHot62 {
    /**
     * N皇后
     * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
     * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
     * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位
     */
    //试着自己做一下 分治：分解成各个小步骤
    private List<Deque<int[]>> queenLocation = new LinkedList<>();//存放皇后的位置 每个Queue对应一个棋盘
    private int[][] canPlace;//存放本棋盘可以放的地方 一维表示行 二维表示列 0表示可以放 1表示不行
    private int rowNum;
    private Deque<int[]> cur = new LinkedList<>();
    public List<List<String>> solveNQueens(int n) {
        canPlace = new int[n][n];
        rowNum = n;
        findQueenLocation(n);//找到queen的locations
//        queenLocation.stream().forEach(e->{
//            Deque<int[]> elem = e;
//            while (!elem.isEmpty()) {
//                int[] poll = elem.poll();
//                System.out.print("["+poll[0]+","+poll[1]+"]");
//            }
//            System.out.println();
//        });
        return generateRes(queenLocation);
    }

    //主方法 用于找到n个queen的location
    private void findQueenLocation(int n) {
        if (cur.size() == rowNum) {//返回条件
            queenLocation.add(new LinkedList<>(cur));
            return;
        }
        int curRow = cur.size();
        for (int i = 0; i < rowNum; i++) {//检查本行有没有能放的 如果有就放置
            if (canPlace[curRow][i] == 0) {
                cur.add(new int[]{curRow, i});
                handleCanPlace();//刷新棋盘可摆放位置
                findQueenLocation(n);//递归
                cur.removeLast();//回溯并刷新棋盘可摆放位置
                handleCanPlace();
            }
        }

    }

    private void handleCanPlace() {
        for (int i = 0; i < rowNum; i++) {
            Arrays.fill(canPlace[i], 0);
        }
        for (int[] elem:cur){
            int[] poll = elem;
            int i = poll[0];
            int j = poll[1];
            //根据放置皇后的位置设置canPlace数组
            Arrays.fill(canPlace[i], 1);//本行全不能放
            for (int k = 0; k < rowNum; k++) {//本列不能放
                canPlace[k][j] = 1;
            }
            //左上不能放
            for (int m = i - 1, n = j - 1; m >= 0 && n >= 0; m--, n--) {
                canPlace[m][n] = 1;
            }
            //右下不能放
            for (int m = i + 1, n = j + 1; m < rowNum && n < rowNum; m++, n++) {
                canPlace[m][n] = 1;
            }
            //左下不能放
            for (int m = i + 1, n = j - 1; m < rowNum && n >= 0; m++, n--) {
                canPlace[m][n] = 1;
            }
            //右上不能放
            for (int m = i - 1, n = j + 1; m >= 0 && n < rowNum; m--, n++) {
                canPlace[m][n] = 1;
            }
        }

    }
    //用于根据皇后的位置生成返回结果
    public List<List<String>> generateRes(List<Deque<int[]>> queenLocations) {
        List<List<String>> res = new ArrayList<>();
        for (Queue<int[]> queenLocation : queenLocations) {//循环为每个queenLocation添加一个新的棋盘
            List<StringBuilder> cur = new ArrayList<>();
            StringBuilder row = new StringBuilder();
            //初始化棋盘
            row.append(".".repeat(queenLocation.size()));
            for (int i = 0; i < queenLocation.size(); i++) {
                cur.add(new StringBuilder(row));
            }
            //摆放皇后
            while (!queenLocation.isEmpty()) {
                int[] poll = queenLocation.poll();
                int i = poll[0];
                int j = poll[1];
                cur.get(i).setCharAt(j, 'Q');
            }
            //转换成List<String>
            List<String> cur2 = new ArrayList<>(cur.stream().map(StringBuilder::toString).toList());
            res.add(cur2);
        }
        return res;//返回结果
    }

    public static void main(String[] args) {
        LeetCodeHot62 leetCodeHot62 = new LeetCodeHot62();
        System.out.println(leetCodeHot62.solveNQueens(4));
    }
}
