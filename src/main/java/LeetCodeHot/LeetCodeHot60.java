package LeetCodeHot;

public class LeetCodeHot60 {
    /**
     * 单词搜索
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     *
     */
    private boolean res;
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {//与岛屿问题类似 每个点都进行一次深度优先
                int[][] visited = new int[row][col];
                helper(board, word, new StringBuilder(), i, j, 0,visited);
            }
        }
        return res;
    }

    private void helper(char[][] board, String word, StringBuilder sb, int row, int col, int index,int[][] visited) {
        if(!res){

            if (index == word.length()) {
                if (sb.toString().equals(word)) {
                    res = true;
                }
                return;
            }
            if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
                return;
            }
            if (visited[row][col] == 1) {
                return;
            }
            char c = board[row][col];
            if (c != word.charAt(index)) {
                return;
            }
            sb.append(c);
            visited[row][col] = 1;
            helper(board, word, sb, row + 1, col, index + 1, visited);
            helper(board, word, sb, row, col + 1, index + 1, visited);
            helper(board, word, sb, row, col - 1, index + 1, visited);
            helper(board, word, sb, row - 1, col, index + 1, visited);
            //搜完都没有搜到 要回溯刚刚加的量 确保下次不受影响
            sb.deleteCharAt(sb.length() - 1);
            visited[row][col] = 0;
        }
    }

    public static void main(String[] args) {
        LeetCodeHot60 leetcode = new LeetCodeHot60();
        char[][] board = {{'C','A','A'},{'A','A','A'},{'B','C','D'}};

        System.out.println(leetcode.exist(board, "AAB"));
    }
}
