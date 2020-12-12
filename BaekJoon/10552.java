import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int[] graph = new int[M + 1];
        Arrays.fill(graph, -1);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int favorite = Integer.parseInt(st.nextToken());
            int hate = Integer.parseInt(st.nextToken());
            if (graph[hate] == -1) {
                graph[hate] = favorite;
            }
        }
        System.out.println(solution(M, P, graph));
    }

    private static int solution(int M, int P, int[] graph) {
        boolean[] isVisited = new boolean[M + 1];
        Arrays.fill(isVisited, false);

        return dfs(graph, isVisited, P);
    }

    private static int dfs(int[] graph, boolean[] isVisited, int currentChannel) {
        isVisited[currentChannel] = true;
        int channel = graph[currentChannel];
        if (channel == -1) {
            return 0;
        }
        if (!isVisited[channel]) {
            int count = dfs(graph, isVisited, channel);
            return count == -1 ? count : count + 1;
        } else {
            return -1;
        }
    }
}
