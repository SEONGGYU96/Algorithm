import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int answer = 0;
        final int n = maps.length;
        final int m = maps[0].length;
        final int[][] offsets = {{-1, 0, 1, 0}, {0, 1, 0, -1}};

        boolean[][] isVisited = new boolean[n][m];
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(0, 0));
        isVisited[0][0] = true;

        while(!queue.isEmpty()) {
            int size = queue.size();
            answer++;
            for (int i = 0; i < size; i++) {
                Position current = queue.poll();
                if (current.row == n - 1 && current.col == m - 1) {
                    return answer;
                }
                for (int j = 0; j < 4; j++) {
                    int nextRow = current.row + offsets[0][j];
                    int nextCol = current.col + offsets[1][j];

                    if (nextRow >= n || nextCol >= m || nextRow < 0 || nextCol < 0) {
                        continue;
                    }
                    if (isVisited[nextRow][nextCol] || maps[nextRow][nextCol] == 0) {
                        continue;
                    }

                    isVisited[nextRow][nextCol] = true;
                    queue.offer(new Position(nextRow, nextCol));
                }
            }
        }

        return -1;
    }

    private static class Position {
        int row;
        int col;

        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
