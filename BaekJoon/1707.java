import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        boolean[] answer = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int[][] edge = new int[E][2];
            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 2; k++) {
                    edge[j][k] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
            answer[i] = solution(V, edge);
        }

        for (boolean _answer : answer) {
            if (_answer) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean solution(int V, int[][] edgeData) {
        int[] colors = new int[V];
        List<List<Integer>> edge = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            edge.add(new ArrayList<>());
            colors[i] = 0;
        }

        for (int[] vertex : edgeData) {
            edge.get(vertex[0]).add(vertex[1]);
            edge.get(vertex[1]).add(vertex[0]);
        }

        for (int i = 0; i < V; i++) {
            if (colors[i] == 0) {
                if (!bfs(edge, colors, i, 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean bfs(List<List<Integer>> edge, int[] colors, int startVertex, int color) {
        Queue<Integer> tree = new LinkedList<>();

        tree.offer(startVertex);
        colors[startVertex] = color;

        while (!tree.isEmpty()) {
            int currentVertex = tree.poll();

            for (int connectedVertex : edge.get(currentVertex)) {
                if (colors[connectedVertex] == 0) {
                    tree.offer(connectedVertex);
                    colors[connectedVertex] = colors[currentVertex] * -1;
                } else if (currentVertex != connectedVertex && colors[connectedVertex] + colors[currentVertex] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
