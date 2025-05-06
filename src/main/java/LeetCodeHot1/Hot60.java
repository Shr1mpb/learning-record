package LeetCodeHot1;

public class Hot60 {
    // 单词搜索
    private boolean res;
    public boolean exist0(char[][] board, String word) {
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


    // 3ms执行时间 优化方法
    // 出现次数 + dfs + 原地标记visited
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private boolean dfs(char[][] board, char[] word, int i, int j, int index) {
        // 这个位置不对
        if(board[i][j] != word[index]) return false;
        // 这个位置对的
        if(index == word.length - 1) return true;
        board[i][j] = 0;// 这里置为已访问
        for(int[] dir : DIRS) {
            int x = i + dir[0];
            int y = j + dir[1];
            if(x >= 0 && x < board.length && y >= 0 && y < board[0].length && dfs(board, word, x, y, index + 1)) return true;
        }
        board[i][j] = word[index];// 置回原本的样子
        return false;
    }

    public boolean exist(char[][] board, String word) {
        char[] cWord = word.toCharArray();
        // 统计board和word的字符频率
        int[] dict = new int[128];  // 存储board中每个字符的出现次数
        int[] cDict = new int[128]; // 存储word中每个字符的出现次数
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                dict[board[i][j]]++;
            }
        }
        for(int i = 0; i < cWord.length; i++) {
            if(++cDict[cWord[i]] > dict[cWord[i]]) return false; // word的字符在board中不足
        }
        // 优化：如果word的首字符比尾字符更稀有，反转word以减少搜索量
        if(cDict[cWord[0]] > cDict[cWord[cWord.length - 1]]) {
            cWord = new StringBuilder(word).reverse().toString().toCharArray();
        }
        // 遍历board的每个起点，尝试DFS
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(dfs(board, cWord, i, j, 0)) return true;
            }
        }
        return false;
    }
}
