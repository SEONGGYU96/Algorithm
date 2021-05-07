import java.util.Arrays;

class Solution {
    public int solution(int n) {
        int[] anotherCols = new int[n];
        Arrays.fill(anotherCols, -1);
        return batch(n, 0, anotherCols, new boolean[n][n]);
    }
    
    private int batch(int n, int row, int[] anotherCols, boolean[][] map) {
        if (row == n) {
            return 1;
        }
        
        int count = 0;
        for (int col = 0; col < n; col++) {
            boolean isValid = true;
            for (int i = 0; i < row; i++) {
                if (anotherCols[i] == col || Math.abs(anotherCols[i] - col) == row - i) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                anotherCols[row] = col;
                map[row][col] = true;
                count += batch(n, row + 1, anotherCols, map);
                map[row][col] = false;
            }
        }
        return count;
    }
}
