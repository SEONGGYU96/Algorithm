iclass Solution {
    public int solution(int[][] board) {
        int n = board.length;
        int m = board[0].length;

        int[][] map = new int[n + 1][m + 1];

        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i + 1][j + 1] = board[i][j];
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] != 0) {
                    map[i][j] = Math.min(Math.min(map[i - 1][j], map[i][j - 1]), map[i - 1][j - 1]) + 1;
                    answer = Math.max(answer, map[i][j]);
                }
            }
        }

        return answer * answer;
    }
}

