import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] connection = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                connection[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solution(N, connection);
        System.out.println(count);
    }

    private static void solution(int N, int[][] connection) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        boolean[] isVisited = new boolean[N];
        Arrays.fill(isVisited, false);
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : connection) {
            adj.get(edge[0] - 1).add(edge[1] - 1);
            adj.get(edge[1] - 1).add(edge[0] - 1);
        }
        for (int i = 0; i < N; i++) {
            if (!isVisited[i]) {
                count++;
                isVisited[i] = true;
                dfs(adj, isVisited, i);
            }
        }
    }

    private static void dfs(ArrayList<ArrayList<Integer>> adj, boolean[] isVisited, int start) {
        for (int vertex : adj.get(start)) {
            if (!isVisited[vertex]) {
                isVisited[vertex] = true;
                dfs(adj, isVisited, vertex);
            }
        }
    }
}
