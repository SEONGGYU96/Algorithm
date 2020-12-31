import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] tomato = new int[N][M];
        Queue<Position> queue = new LinkedList<>();
        boolean[][] isVisited = new boolean[N][M];
        int emptyCount = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                tomato[i][j] = Integer.parseInt(st.nextToken());
                if (tomato[i][j] == 1) {
                    queue.add(new Position(i, j));
                    isVisited[i][j] = true;
                } else if (tomato[i][j] == -1) {
                    emptyCount++;
                }
            }
        }
        System.out.println(solution(N, M, tomato, queue, isVisited, emptyCount));
    }

    private static int solution(int N, int M, int[][] tomato, Queue<Position> queue, boolean[][] isVisited, int emptyCount) {
        int answer = -1;
        int count = emptyCount;
        int[] rowOffset = {1, 0, -1, 0};
        int[] colOffset = {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            answer++;
//            System.out.println("============== " + answer + " ============");
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                count++;
                Position current = queue.poll();
//                System.out.println(current);
                int row = current.row;
                int col = current.col;
                for (int j = 0; j < 4; j++) {
                    int newRow = row + rowOffset[j];
                    int newCol = col + colOffset[j];
                    if (newRow >= N || newRow < 0 || newCol >= M || newCol < 0 || isVisited[newRow][newCol]) {
                        continue;
                    }
                    if (tomato[newRow][newCol] == 0) {
                        queue.add(new Position(newRow, newCol));
                        isVisited[newRow][newCol] = true;
                    }
                }
            }
        }
        return count == N * M ? answer : -1;
    }

    static class Position {
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return " row : " + row + " col : " + col;
        }
    }
}

