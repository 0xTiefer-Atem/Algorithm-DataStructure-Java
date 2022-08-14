package org.wq.leetcode.day26;

public class Solution26 {
    // 72. 编辑距离
    public int minDistance(String word1, String word2) {
//        int[][] memory = new int[word1.length()][word2.length()];
//
//        for (int i = 0; i < word1.length(); i++) {
//            for (int j = 0; j < word2.length(); j++) {
//                memory[i][j] = -1;
//            }
//        }
//        dp2(word1, word1.length() - 1, word2, word2.length() - 1, memory);
        return dp3(word1, word2);
    }

    private int dp1(String word1, int i, String word2, int j) {
        if (i == -1) {
            return j + 1;
        }

        if (j == -1) {
            return i + 1;
        }

        if (word1.charAt(i) == word2.charAt(j)) {
            return dp1(word1, i - 1, word2, j - 1);
        }

        return Math.min(dp1(word1, i, word2, j - 1), Math.min(dp1(word1, i - 1, word2, j), dp1(word1, i - 1, word2, j - 1))) + 1;

    }


    // 72. 编辑距离
    // 带记忆版本
    public int dp2(String word1, int i, String word2, int j, int[][] memory) {
        if (i == -1) {
            return j + 1;
        }

        if (j == -1) {
            return i + 1;
        }

        if (memory[i][j] != -1) {
            return memory[i][j];
        }

        if (word1.charAt(i) == word2.charAt(j)) {
            memory[i][j] = dp2(word1, i - 1, word2, j - 1, memory);
        } else {
            memory[i][j] = Math.min(dp2(word1, i, word2, j - 1, memory), Math.min(dp2(word1, i - 1, word2, j, memory), dp2(word1, i - 1, word2, j - 1, memory))) + 1;
        }


        return memory[i][j];
    }

    // 72. 编辑距离
    // 动态规划版
    public int dp3(String word1, String word2) {
        word1 = " " + word1;
        word2 = " " + word2;
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];


        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= word2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                }
            }
        }


        return dp[word1.length()][word2.length()];
    }

    // 64. 最小路径和
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length < 1) {
            return 0;
        }
        int r = grid.length;
        int c = grid[0].length;

        int[][] memory = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                memory[i][j] = -1;
            }
        }

        return dp6(grid);
    }

    private int dp4(int[][] grid, int r, int c) {
        if (r == 0 && c == 0) {
            return grid[r][c];
        }

        if (r < 0 || c < 0) {
            return Integer.MAX_VALUE;
        }

        return grid[r][c] + Math.min(dp4(grid, r - 1, c), dp4(grid, r, c - 1));
    }

    // 最小路径和 dp数组
    // 备忘录
    private int dp5(int[][] grid, int r, int c, int[][] memory) {
        if (r == 0 && c == 0) {
            return grid[r][c];
        }

        if (r < 0 || c < 0) {
            return Integer.MAX_VALUE;
        }

        if (memory[r][c] != -1) {
            return memory[r][c];
        }

        int subP = grid[r][c] + Math.min(dp5(grid, r - 1, c, memory), dp5(grid, r, c - 1, memory));
        memory[r][c] = subP;

        return memory[r][c];
    }

    // 最小路径和 dp数组
    // 自顶向下
    private int dp6(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int[][] dp = new int[r][c];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < r; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }

        for (int i = 1; i < c; i++) {
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }

        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[r-1][c-1];
    }


    public static void main(String[] args) {
        Solution26 s26 = new Solution26();

//        String word1 = "horse", word2 = "ros";


        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(s26.minPathSum(grid));
    }
}
