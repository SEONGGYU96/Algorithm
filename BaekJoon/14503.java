import java.io.*;
import java.util.*;

class Main {

    private static int row;
    private static int col;
    private static int direction;
    private static int rotateCount;
    private static boolean[][] map;
    private static boolean[][] isVisited;
    private static final int[][] offsets = {{-1, 0, 1, 0}, {0, -1, 0, 1}};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = 1;
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        isVisited = new boolean[N][M];

        st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        direction = Integer.parseInt(st.nextToken());
        if (direction == 1) {
            direction = 3;
        } else if (direction == 3) {
            direction = 1;
        }
        rotateCount = 0;
        isVisited[row][col] = true;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = st.nextToken().equals("1");
            }
        }

        while(true) {
            if (rotateCount == 4) {
                rotateCount = 0;
                int backwardDirection = direction + 2 >= 4 ? direction - 2 : direction + 2;
                int backwardRow = row + offsets[0][backwardDirection];
                int backwardCol = col + offsets[1][backwardDirection];
                if (isInMap(backwardRow, backwardCol) && !isWall(backwardRow, backwardCol)) {
                    row = backwardRow;
                    col = backwardCol;
                    continue;
                } else {
                    break;
                }
            }
            int forwardDirection = direction + 1 >= 4 ? 0 : direction + 1;
            int forwardRow = row + offsets[0][forwardDirection];
            int forwardCol = col + offsets[1][forwardDirection];
            if (isValidPosition(forwardRow, forwardCol)) {
                row = forwardRow;
                col = forwardCol;
                direction = forwardDirection;
                answer++;
                isVisited[forwardRow][forwardCol] = true;
                rotateCount = 0;
            } else {
                rotate();
            }
        }

        System.out.println(answer);
    }

    private static void rotate() {
        direction += 1;
        if (direction >= 4) {
            direction = 0;
        }
        rotateCount++;
    }

    private static boolean isValidPosition(int row, int col) {
        return isInMap(row, col) && !isWall(row, col) && !isVisitedPosition(row, col);
    }

    private static boolean isVisitedPosition(int row, int col) {
        return isVisited[row][col];
    }

    private static boolean isWall(int row, int col) {
        return map[row][col];
    }

    private static boolean isInMap(int row, int col) {
        return row < map.length && col < map[0].length && row >= 0 && col >= 0;
    }
}
