class Solution {

    private int[][] dp;

    int solution(int[][] land) {
        dp = new int[land.length][land[0].length];
        return dfs(land, 0, -1, 0);
    }

    public int dfs(int[][] land, int row, int col, int sum) {
        if (row == land.length) {
            return sum;
        }

        int max = 0;

        for (int i = 0; i < land[row].length; i++) {
            if (i == col) {
                continue;
            }
            if (dp[row][i] == 0) {
                dp[row][i] = dfs(land, row + 1, i, land[row][i]);
            }
            max = Math.max(max, dp[row][i] + sum);
        }
        return max;
    }
}
