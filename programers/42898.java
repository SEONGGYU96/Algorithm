class Solution {

    boolean[][] isPuddle;
    private int[][] dp;
    private final int[] rowOffset = {0, 1};
    private final int[] colOffset = {1, 0};
    private final int MOD = 1000000007;

    public int solution(int m, int n, int[][] puddles) {
        dp = new int[m][n];
        isPuddle = new boolean[m][n];

        dp[m - 1][n - 1] = 1;

        for (int[] puddle : puddles) {
            isPuddle[puddle[0] - 1][puddle[1] - 1] = true;
        }

        return dfs(m, n, 0, 0);
    }

    private int dfs(int m, int n, int row, int col) {
        if (dp[row][col] != 0) {
            return dp[row][col];
        }

        int count = 0;

        for (int i = 0; i < rowOffset.length; i++) {
            int nextRow = row + rowOffset[i];
            int nextCol = col + colOffset[i];

            if (nextRow >= m || nextCol >= n || isPuddle[nextRow][nextCol]) {
                continue;
            }
            count += dfs(m, n, nextRow, nextCol) % MOD;
        }
        return dp[row][col] = count % MOD;
    }
}
