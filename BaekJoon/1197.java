import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        parents = new int[V];
        Queue<Edge> edges = new PriorityQueue<>();

        Arrays.fill(parents, -1);
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            edges.offer(new Edge(start, end, weight));
        }

        int count = 1;
        int answer = 0;

        do {
            Edge current = edges.poll();
            if (merge(current.start, current.end)) {
                count++;
                answer += current.weight;
            }
        } while (!edges.isEmpty() && count != E);
        System.out.println(answer);
    }

    private static class Edge implements Comparable<Edge> {
        private final int start;
        private final int end;
        private final int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(weight, o.weight);
        }
    }

    private static int find(int n) {
        if (parents[n] < 0) {
            return n;
        }
        parents[n] = find(parents[n]);
        return parents[n];
    }

    private static boolean merge(int n, int m) {
        int parentOfN = find(n);
        int parentOfM = find(m);
        if (parentOfN == parentOfM) {
            return false;
        } else {
            parents[parentOfM] = parentOfN;
            return true;
        }
    }
}

