import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[][] ground = new boolean[N][M];
        for (boolean[] horizon : ground) {
            Arrays.fill(horizon, false);
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            ground[y][x] = true;
        }
        System.out.println(solution(ground));
    }

    private static int solution(boolean[][] ground) {
        int max = 0;
        boolean[][] isVisited = new boolean[ground.length][ground[0].length];
        for (boolean[] horizon : isVisited) {
            Arrays.fill(horizon, false);
        }
        for (int y = 0; y < ground.length; y++) {
            for (int x = 0; x < ground[y].length; x++) {
                if (ground[y][x] && !isVisited[y][x]) {
                    isVisited[y][x] = true;
                    max = Math.max(max, dfs(ground, isVisited, x, y));
                }
            }
        }
        return max;
    }

    private static int dfs(boolean[][] ground, boolean[][] isVisited, int x, int y) {
        int count = 1;
        if (y - 1 >= 0 && ground[y - 1][x] && !isVisited[y - 1][x]) {
            isVisited[y - 1][x] = true;
            count += dfs(ground, isVisited, x, y - 1);
        }

        if (x + 1 < ground[0].length && ground[y][x + 1] && !isVisited[y][x + 1]) {
            isVisited[y][x + 1] = true;
            count += dfs(ground, isVisited, x + 1, y);
        }

        if (y + 1 < ground.length && ground[y + 1][x] && !isVisited[y + 1][x]) {
            isVisited[y + 1][x] = true;
            count += dfs(ground, isVisited, x, y + 1);
        }

        if (x - 1 >= 0 && ground[y][x - 1] && !isVisited[y][x - 1]) {
            isVisited[y][x - 1] = true;
            count += dfs(ground, isVisited, x - 1, y);
        }
        return count;
    }
}

