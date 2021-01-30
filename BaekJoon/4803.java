import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        for (int tc = 1;; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) {
                break;
            }

            List<Integer>[] tree = new ArrayList[n];

            for (int i = 0; i < n; i++) {
                tree[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int node1 = Integer.parseInt(st.nextToken()) - 1;
                int node2 = Integer.parseInt(st.nextToken()) - 1;

                tree[node1].add(node2);
                tree[node2].add(node1);
            }

            boolean[] isVisited = new boolean[n];

            int numberOfTrees = 0;

            for (int i = 0; i < n; i++) {
                if (isVisited[i]) {
                    continue;
                }
                int edge = 0;
                int node = 0;
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                isVisited[i] = true;
                while (!queue.isEmpty()) {
                    int head = queue.poll();
                    node++;
                    for (int child : tree[head]) {
                        edge++;
                        if (!isVisited[child]) {
                            isVisited[child] = true;
                            queue.add(child);
                        }
                    }
                }
                if (node - (edge / 2) == 1) {
                    numberOfTrees++;
                }
            }
            answer.append("Case ").append(tc).append(": ");
            if (numberOfTrees == 0) {
                answer.append("No trees.");
            } else if (numberOfTrees == 1) {
                answer.append("There is one tree.");
            } else {
                answer.append("A forest of ").append(numberOfTrees).append(" trees.");
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }
}

