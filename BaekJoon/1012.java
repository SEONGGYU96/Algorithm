import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            count = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            boolean[][] ground = new boolean[N][M];
            for (boolean[] x : ground) {
                Arrays.fill(x, false);
            }
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                ground[y][x] = true;
            }
            bw.write(solution(ground) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int solution(boolean[][] ground) {
        boolean[][] isVisited = new boolean[ground.length][ground[0].length];
        for (boolean[] x : isVisited) {
            Arrays.fill(x, false);
        }

        for (int y = 0; y < ground.length; y++) {
            for (int x = 0; x < ground[y].length; x++) {
                if (ground[y][x] && !isVisited[y][x]) {
                    count++;
                    dfs(ground, isVisited, x, y);
                }
            }
        }
        return count;
    }

    private static void dfs(boolean[][] ground, boolean[][] isVisited, int x, int y) {
        if (y - 1 >= 0) {
            if (ground[y][x] && !isVisited[y - 1][x]) {
                isVisited[y - 1][x] = true;
                dfs(ground, isVisited, x, y - 1);
            }
        }
        if (ground[y][x] && x + 1 < ground[y].length) {
            if (!isVisited[y][x + 1]) {
                isVisited[y][x + 1] = true;
                dfs(ground, isVisited, x + 1, y);
            }
        }
        if (ground[y][x] && y + 1 < ground.length) {
            if (!isVisited[y + 1][x]) {
                isVisited[y + 1][x] = true;
                dfs(ground, isVisited, x, y + 1);
            }
        }
        if (ground[y][x] && x - 1 >= 0) {
            if (!isVisited[y][x - 1]) {
                isVisited[y][x - 1] = true;
                dfs(ground, isVisited, x - 1, y);
            }
        }
    }
}

