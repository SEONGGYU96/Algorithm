import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] area = new int[N][N];
        int max = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, area[i][j]);
            }
        }
        System.out.println(solution(N, max, area));

    }

    private static int solution(int N, int max, int[][] area) {
        int answer = 1;

        for (int i = 1; i < max; i++) {
            boolean[][] isVisited = new boolean[N][N];
            int numberOfSafeArea = 0;
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (!isVisited[row][col] && area[row][col] > i) {
                        isVisited[row][col] = true;
                        numberOfSafeArea++;
                        dfs(area, isVisited, row, col, i);
                    }
                }
            }
            answer = Math.max(answer, numberOfSafeArea);
        }
        return answer;
    }

    private static void dfs(int[][] area, boolean[][] isVisited, int row, int col, int height) {
        if (row - 1 >= 0) {
            explore(area, isVisited, row - 1, col, height);
        }
        if (col + 1 < area[0].length) {
            explore(area, isVisited, row, col + 1, height);
        }
        if (row + 1 < area.length) {
            explore(area, isVisited, row + 1, col, height);
        }
        if (col - 1 >= 0) {
            explore(area, isVisited, row, col - 1, height);
        }
    }

    private static void explore(int[][] area, boolean[][] isVisited, int row, int col, int height) {
        if (!isVisited[row][col] && area[row][col] > height) {
            isVisited[row][col] = true;
            dfs(area, isVisited, row, col, height);
        }
    }
}
