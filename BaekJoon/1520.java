import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    private static final int[][] offsets = {{-1, 0, 1, 0}, {0, 1, 0, -1}};
    private static int[][] map;
    private static int[][] dp;
    private static int N;
    private static int M;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], -1);
        }
        dp[N - 1][M - 1] = 1;
        System.out.println(getWays(0, 0));
    }

    private static int getWays(int row, int col) {
        if (dp[row][col] != -1) {
            return dp[row][col];
        }
        int ways = 0;
        for (int i = 0; i < 4; i++) {
            int nextRow = row + offsets[0][i];
            int nextCol= col + offsets[1][i];
            if (nextRow >= N || nextCol >= M || nextRow < 0 || nextCol < 0) {
                continue;
            }
            if (map[nextRow][nextCol] < map[row][col]) {
                ways += getWays(nextRow, nextCol);
            }
        }
        dp[row][col] = ways;
        return ways;
    }
}

