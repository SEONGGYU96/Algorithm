import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        char[][] map = new char[R][];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.print(solution(map, R, C, K));
    }

    private static int solution(char[][] map, int R, int C, int K) {
        boolean[][] isVisited = new boolean[R][C];
        isVisited[R - 1][0] = true;
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};

        return backTracking(map, new Stack<>(), isVisited, rowOffset, colOffset, R - 1, 0, 1, R, C, K);
    }

    private static int backTracking(char[][] map, Stack<Point> stack, boolean[][] isVisited, int[] rowOffset, int[] colOffset, int row, int col, int depth, int R, int C, int K) {
        if (row == 0 && col == C - 1 && K == depth) {
            //print(map, stack);
            return 1;
        }

        if (depth >= K) {
            return 0;
        }

        int count = 0;

        for (int i = 0; i < 4; i++) {
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];
            if (newRow < R && newRow >= 0 && newCol < C && newCol >= 0) {
                if (map[newRow][newCol] == '.' && !isVisited[newRow][newCol]) {
                    isVisited[newRow][newCol] = true;
                    stack.add(new Point(newCol, newRow));
                    count += backTracking(map, stack, isVisited, rowOffset, colOffset, newRow, newCol, depth + 1, R, C, K);
                    stack.pop();
                    isVisited[newRow][newCol] = false;
                }
            }
        }
        return count;
    }

    private static void print(char[][] map, Stack<Point> stack) {
        char[][] result = new char[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            System.arraycopy(map[i], 0, result[i], 0, map[0].length);
        }

        for (Point current : stack) {
            result[current.y][current.x] = '*';
        }

        result[map.length - 1][0] = '*';

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(result[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}

