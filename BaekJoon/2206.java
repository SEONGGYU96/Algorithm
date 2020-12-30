import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        System.out.println(solution(N, M, map));
    }

    private static int solution(int N, int M, char[][] map) {
        int answer = -1;
        int[] rowOffset = {1, 0, -1, 0};
        int[] colOffset = {0, 1, 0, -1};

        boolean[][][] isVisited = new boolean[2][N][M];
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(0, 0, true));
        isVisited[0][0][0] = true;

        while (!queue.isEmpty()) {
            answer++;
//            System.out.println("============== " + answer + " ============");
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Position current = queue.poll();
//                System.out.println(current);
                int row = current.row;
                int col = current.col;
                boolean hasChance = current.hasChance;
                if (row == N - 1 && col == M - 1) {
                    return ++answer;
                }
                for (int j = 0; j < 4; j++) {
                    int newRow = row + rowOffset[j];
                    int newCol = col + colOffset[j];
                    if (newRow >= N || newRow < 0 || newCol >= M || newCol < 0 || isVisited[hasChance ? 0 : 1][newRow][newCol]) {
                        continue;
                    }
                    if (map[newRow][newCol] == '0') {
                        queue.add(new Position(newRow, newCol, hasChance));
                        isVisited[hasChance ? 0 : 1][newRow][newCol] = true;
                    } else {
                        if (hasChance) {
                            queue.add(new Position(newRow, newCol, false));
                            isVisited[1][newRow][newCol] = true;
                        }
                    }
                }
            }
        }
        return -1;
    }

    static class Position {
        int row;
        int col;
        boolean hasChance;

        public Position(int row, int col, boolean hasChance) {
            this.row = row;
            this.col = col;
            this.hasChance = hasChance;
        }

        @Override
        public String toString() {
            return " row : " + row + " col : " + col + " " + hasChance;
        }
    }
}

