import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        for (int i = 1; i <= N; i++) {
            graph.get(i).sort(Collections.reverseOrder());
        }
        dfs(N, V, graph);
        System.out.println();
        bfs(N, V, graph);
    }

    private static void bfs(int N, int V, ArrayList<ArrayList<Integer>> graph) {
        boolean[] isVisited = new boolean[N + 1];
        Arrays.fill(isVisited, false);
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(V);
        isVisited[V] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");
            ArrayList<Integer> targets = graph.get(current);
            for (int i = targets.size() - 1; i >= 0; i--) {
                int target = targets.get(i);
                if (!isVisited[target]) {
                    isVisited[target] = true;
                    queue.offer(target);
                }
            }
        }
    }

    private static void dfs(int N, int V, ArrayList<ArrayList<Integer>> graph) {
        boolean[] isVisited = new boolean[N + 1];
        Arrays.fill(isVisited, false);
        Stack<Integer> stack = new Stack<>();

        stack.push(V);

        int count = 0;

        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (isVisited[current]) {
                continue;
            }
            isVisited[current] = true;
            System.out.print(current + " ");
            if (++count == N) {
                break;
            }
            ArrayList<Integer> targets = graph.get(current);
            for (int target : targets) {
                    stack.push(target);
            }
        }
    }
}

