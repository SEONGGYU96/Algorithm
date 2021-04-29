import java.io.*;
import java.util.*;

class Main {

    private static int N;
    private static int M;
    private static ArrayList<Position> viruses;
    private static final int[][] offsets = {{-1, 0, 1, 0}, {0, 1, 0, -1}};
    private static int minInfectedArea;
    private static final int ADDITIONAL_WALLS = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        minInfectedArea = N * M;
        int[][] map = new int[N][M];
        viruses = new ArrayList<>();
        int wallCount = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    viruses.add(new Position(i, j));
                } else if (map[i][j] == 1) {
                    wallCount++;
                }
            }
        }

        build(map, ADDITIONAL_WALLS, 0, 0);
        int safeArea = N * M - minInfectedArea - wallCount - ADDITIONAL_WALLS;
        System.out.println(safeArea);
    }

    private static void build(int[][] map, int walls, int startRow, int startCol) {
        if (walls == 0) {
            bfs(map);
            return;
        }

        for (int i = startRow; i < N; i++) {
            for (int j = i == startRow ? startCol : 0; j < M; j++) {
                if (map[i][j] != 0) {
                    continue;
                }
                map[i][j] = 1;
                build(map, walls - 1, i, j);
                map[i][j] = 0;
            }
        }
    }

    private static void bfs(int[][] map) {
        Queue<Position> queue = new LinkedList<>(viruses);
        boolean[][] isVisited = new boolean[N][M];
        for (Position virus : viruses) {
            isVisited[virus.row][virus.col] = true;
        }
        int infectedArea = queue.size();
        while(!queue.isEmpty()) {
            Position current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = current.row + offsets[0][i];
                int nextCol = current.col + offsets[1][i];

                if (nextRow >= N || nextCol >= M || nextRow < 0 || nextCol < 0) {
                    continue;
                }

                if (isVisited[nextRow][nextCol] || map[nextRow][nextCol] != 0) {
                    continue;
                }

                isVisited[nextRow][nextCol] = true;
                queue.offer(new Position(nextRow, nextCol));
                infectedArea++;
            }
        }
        minInfectedArea = Math.min(minInfectedArea, infectedArea);
    }

    private static class Position {
        private final int row;
        private final int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}

