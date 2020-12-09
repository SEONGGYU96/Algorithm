import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] painting = new char[N][N];
        for (int i = 0; i < N; i++) {
            painting[i] = br.readLine().replaceAll(" ", "").toCharArray();
        }
        int[][] answer = solution(N, painting);
        for (int[] row : answer) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }

    private static int[][] solution(int N, char[][] graph) {
        int[][] answer = new int[N][N];

        for (int start = 0; start < N; start++) {
            boolean[] isVisited = new boolean[N];
            for (int target = 0; target < N; target++) {
                if (graph[start][target] == '1') {
                    isVisited[target] = true;
                    dfs(graph, isVisited, target);
                }
            }
            for (int i = 0; i < N; i++) {
                answer[start][i] = isVisited[i] ? 1 : 0;
            }
        }
        return answer;
    }

    private static void dfs(char[][] graph, boolean[] isVisited, int point) {
        if (point >= isVisited.length) {
            return;
        }
        for (int target = 0; target < isVisited.length; target++) {
            if (!isVisited[target] && graph[point][target] == '1') {
                isVisited[target] = true;
                dfs(graph, isVisited, target);
            }
        }
    }
}
