import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    private static final int[][] offsets = {{-1, 0, 1, 0}, {0, 1, 0, -1}};
    private static int N;
    private static int L;
    private static int R;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while (true) {
            boolean[][] isVisited = new boolean[N][N];
            boolean isChanged = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!isVisited[i][j]) {
                        Result result = new Result();
                        dfs(isVisited, i, j, result);

                        int population = result.total / result.elements.size();
                        for (Position element : result.elements) {
                            map[element.row][element.col] = population;
                        }
                        if (result.elements.size() > 1) {
                            isChanged = true;
                        }
                    }
                }
            }
            if (!isChanged) {
                break;
            }
            answer++;
        }
        System.out.println(answer);
    }


    private static void dfs(boolean[][] isVisited, int row, int col, Result result) {
        result.elements.add(new Position(row, col));
        result.total += map[row][col];
        isVisited[row][col] = true;
        for (int i = 0; i < 4; i++) {
            int nextRow = row + offsets[0][i];
            int nextCol = col + offsets[1][i];
            if (nextRow >= N || nextCol >= N || nextRow < 0 || nextCol < 0 || isVisited[nextRow][nextCol]) {
                continue;
            }
            int diffOfPopulation = Math.abs(map[nextRow][nextCol] - map[row][col]);
            if (L <= diffOfPopulation && diffOfPopulation <= R) {
                isVisited[nextRow][nextCol] = true;
                dfs(isVisited, nextRow, nextCol, result);
            }
        }
    }

    private static class Result {
        private final ArrayList<Position> elements;
        private int total;

        public Result() {
            this.elements = new ArrayList<>();
            this.total = 0;
        }
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
