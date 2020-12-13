import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int target = Integer.parseInt(st.nextToken());
            graph.get(i).add(target);
            graph.get(target).add(i);
        }
        System.out.println(solution(n, k, graph));
    }

    private static int solution(int n, int k, ArrayList<ArrayList<Integer>> graph) {
        boolean[] isVisited = new boolean[n + 1];
        Arrays.fill(isVisited, false);
        ArrayList<Integer> groupCount = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!isVisited[i]) {
                groupCount.add(dfs(graph, isVisited, i));
            }
        }
        int[][] dp = new int[groupCount.size() + 1][k + 1];
        Arrays.fill(dp[0], 0);
        for (int i = 1; i < groupCount.size() + 1; i++) {
            int current = groupCount.get(i - 1);
            for (int j = i; j < k + 1; j++) {
                if (current > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - current] + current);
                }
            }
        }
        return dp[groupCount.size()][k];
    }

    private static int dfs( ArrayList<ArrayList<Integer>> graph, boolean[] isVisited, int currentPeople) {
        isVisited[currentPeople] = true;
        int count = 1;
        ArrayList<Integer> list = graph.get(currentPeople);
        for (int people : list) {
            if (!isVisited[people]) {
                count += dfs(graph, isVisited, people);
            }
        }
        return count;
    }
}
