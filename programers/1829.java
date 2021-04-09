class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        boolean[][] isVisited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!isVisited[i][j] && picture[i][j] != 0) {
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, dfs(picture, isVisited, i, j, 1));
                    numberOfArea++;
                }
            }
        }

        return new int[]{numberOfArea, maxSizeOfOneArea};
    }

    private static final int[][] offsets = {{-1, 0, 1, 0}, {0, 1, 0, -1}};

    private int dfs(int[][] picture, boolean[][] isVisited, int row, int col, int depth) {
        isVisited[row][col] = true;
        int count = 1;

        for (int i = 0; i < offsets[0].length; i++) {
            int nextRow = row + offsets[0][i];
            int nextCol = col + offsets[1][i];
            if (nextRow >= picture.length || nextCol >= picture[0].length || nextRow < 0 || nextCol < 0) {
                continue;
            }
            if (picture[nextRow][nextCol] != 0 && picture[nextRow][nextCol] == picture[row][col] && !isVisited[nextRow][nextCol]) {
                count += dfs(picture, isVisited, nextRow, nextCol, depth + 1);
            }
        }
        return count;
    }
}
