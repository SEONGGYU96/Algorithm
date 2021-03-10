import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};
        int N = board.length;
        int[][] dp = new int[N][N];
        Queue<Point> queue = new LinkedList<>();

        for (int i = 0; i < 4; i++) {
            queue.add(new Point(0, 0, i, 0));
        }

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            for (int newDirection = 0; newDirection < 4; newDirection++) {
                if (Math.abs(newDirection - current.direction) == 2) {
                    continue;
                }

                int nextRow = current.row + rowOffset[newDirection];
                int nextCol = current.col + colOffset[newDirection];

                if (nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N || board[nextRow][nextCol] == 1) {
                    continue;
                }

                int nextCost = current.cost;
                if (current.direction == newDirection) {
                    nextCost += 100;
                } else {
                    nextCost += 600;
                }

                if (dp[nextRow][nextCol] == 0 || dp[nextRow][nextCol] >= nextCost) {
                    dp[nextRow][nextCol] = nextCost;
                    queue.add(new Point(nextRow, nextCol, newDirection, nextCost));
                }
            }
        }

        return dp[N - 1][N - 1];
    }
    
    private class Point {
        int row;
        int col;
        int direction;
        int cost;

        public Point(int row, int col, int direction, int cost) {
            this.row = row;
            this.col = col;
            this.direction = direction;
            this.cost = cost;
        }
    }
}
