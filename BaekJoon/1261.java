import java.io.*;
import java.util.*;

class Main {

    private static final int[][] offsets = {{-1, 0, 1, 0}, {0, 1, 0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        boolean[][] maze = new boolean[N][M];
        boolean[][] isVisited = new boolean[N][M];
        int[][] costs = new int[N][M];
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = row.charAt(j) == '0';
            }
            Arrays.fill(costs[i], Integer.MAX_VALUE);
        }
        costs[0][0] = 0;

        Queue<Position> queue = new PriorityQueue<>();
        queue.offer(new Position(0, 0, 0));
        isVisited[0][0] = true;

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = current.row + offsets[0][i];
                int nextCol = current.col + offsets[1][i];

                if (nextRow >= N || nextCol >= M || nextRow < 0 || nextCol < 0) {
                    continue;
                }
                if (isVisited[nextRow][nextCol]) {
                    continue;
                }
                int nextCost = current.cost + (maze[nextRow][nextCol] ? 0 : 1);
                if (costs[nextRow][nextCol] > nextCost) {
                    costs[nextRow][nextCol] = nextCost;
                    queue.offer(new Position(nextRow, nextCol, nextCost));
                    isVisited[nextRow][nextCol] = true;
                }
            }
        }

        System.out.println(costs[N - 1][M - 1]);
    }

    private static class Position implements Comparable<Position> {
        private final int row;
        private final int col;
        private final int cost;

        public Position(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }

        @Override
        public int compareTo(Position o) {
            return Integer.compare(cost, o.cost);
        }
    }
}
