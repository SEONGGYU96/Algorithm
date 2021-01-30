import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Integer>[] tree = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        boolean[] isVisited = new boolean[N];
        int[] parents = new int[N];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        isVisited[0] = true;

        while (!queue.isEmpty()) {
            int head = queue.poll();
            for (int child : tree[head]) {
                if (!isVisited[child]) {
                    isVisited[child] = true;
                    parents[child] = head;
                    queue.add(child);
                }
            }
        }
        StringBuilder answer = new StringBuilder();
        for (int i = 1; i < N; i++) {
            answer.append(parents[i] + 1).append("\n");
        }
        System.out.println(answer);
    }
}

