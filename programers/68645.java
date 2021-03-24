import java.util.*;

class Solution {
    public int[] solution(int n) {
        int[] answer = new int[n * (n + 1) / 2];
        final int[] rowOffset = {1, 0, -1};
        final int[] colOffset = {0, 1, -1};
        int[][] tree = new int[n][];
        for (int i = 0; i < n; i++) {
            tree[i] = new int[i + 1];
        }

        int num = 1;
        int row = 0;
        int col = 0;
        int direction = 0;
        while (num <= answer.length) {
            tree[row][col] = num++;
            int nextRow = row + rowOffset[direction];
            int nextCol = col + colOffset[direction];
            if (nextRow == n || nextCol > nextRow || tree[nextRow][nextCol] != 0) {
                direction++;
                direction %= 3;
            }
            row = row + rowOffset[direction];
            col = col + colOffset[direction];
        }

        for (int i = 0, k = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                answer[k++] = tree[i][j];
            }
        }

        return answer;
    }
}
