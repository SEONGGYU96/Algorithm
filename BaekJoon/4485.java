import java.io.*;
import java.util.*;

class Main {

    private static final int MAX = 9 * 125 * 2;
    private static final int[][] offsets = {{-1, 0, 1, 0}, {0, 1, 0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int tc = 1; ; tc++) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }
            int[][] map = new int[N][N];
            int[][] result = new int[N][N];
            boolean[][] isVisited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    result[i][j] = MAX;
                }
            }
            result[0][0] = map[0][0];
            Queue<Position> queue = new PriorityQueue<>();
            queue.offer(new Position(0, 0, result[0][0]));
            isVisited[0][0] = true;
            while (!queue.isEmpty()) {
                Position current = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nextRow = current.row + offsets[0][i];
                    int nextCol = current.col + offsets[1][i];
                    if (nextRow >= N || nextCol >= N || nextRow < 0 || nextCol < 0) {
                        continue;
                    }
                    if (isVisited[nextRow][nextCol]) {
                        continue;
                    }
                    int nextCost = current.cost + map[nextRow][nextCol];
                    if (nextCost < result[nextRow][nextCol]) {
                        result[nextRow][nextCol] = nextCost;
                        queue.offer(new Position(nextRow, nextCol, nextCost));
                    }
                }
            }
            bw.write("Problem " + tc + ": " + result[N - 1][N - 1] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
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
