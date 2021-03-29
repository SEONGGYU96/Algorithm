class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int n = lock.length;
        int m = key.length;

        int count = 0;

        for (int[] row : lock) {
            for (int col : row) {
                if (col == 0) {
                    count++;
                }
            }
        }
        for (int rotate = 0; rotate < 4; rotate++) {
            for (int i = 1 - m; i < n; i++) {
                for (int j = 1 - m; j < n; j++) {
                    int blank = count;
                    boolean isFailure = false;
                    for (int r = Math.max(0, i); r < Math.min(n, m + i); r++) {
                        for (int c = Math.max(0, j); c < Math.min(n, m + j); c++) {
                            int lockType = lock[r][c];
                            int keyType = key[r - i][c - j];
                            if (lockType == 0 && keyType == 1) {
                                blank--;
                            } else if (lockType == 1 && keyType == 1) {
                                isFailure = true;
                                break;
                            }
                        }
                        if (isFailure) {
                            break;
                        }
                    }
                    if (blank == 0) {
                        return true;
                    }
                }
            }
            int[][] newKey = new int[m][m];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    newKey[i][j] = key[m - j - 1][i];
                }
            }
            key = newKey;
        }
        return false;
    }
}
