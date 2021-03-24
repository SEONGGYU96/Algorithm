import java.util.*;

class Solution {
    private static final int MOD = 20170805;
    private final int[] rowOffset = {0, 1};
    private final int[] colOffset = {1, 0};
    private int[][][] dp;
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        dp = new int[m][n][2];
        for (int[][] row : dp) {
            for (int [] _row : row) {
                Arrays.fill(_row, -1);
            }
        }
        dp[m - 1][n - 1][0] = 1;
        dp[m - 1][n - 1][1] = 1;
        return dfs(cityMap, 0, 0, 0);
    }

    private int dfs(int[][] cityMap, int row, int col, int direction) {
        if (dp[row][col][direction] != -1) {
            return dp[row][col][direction];
        }

        int m = dp.length;
        int n = dp[0].length;
        int count = 0;

        switch (cityMap[row][col]) {
            case 0 -> {
                for (int i = 0; i < rowOffset.length; i++) {
                    count += explore(cityMap, row, col, i, m, n);
                }
            }
            case 2 -> count += explore(cityMap, row, col, direction, m, n);
        }
        dp[row][col][direction] = count % MOD;
        return dp[row][col][direction];
    }

    private int explore(int[][] cityMap, int row, int col, int direction, int m, int n) {
        int nextRow = row + rowOffset[direction];
        int nextCol = col + colOffset[direction];

        if (nextRow >= m || nextCol >= n) {
            return 0;
        }
        return dfs(cityMap, nextRow, nextCol, direction);
    }
}
