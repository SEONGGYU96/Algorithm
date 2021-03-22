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
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parents = new int[N];
        Arrays.fill(parents, -1);

        Queue<Edge> edgeHeap = new PriorityQueue<>();


        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int component1 = Integer.parseInt(st.nextToken()) - 1;
            int component2 = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            edgeHeap.add(new Edge(weight, component1, component2));
        }

        int min = 0;

        while (!edgeHeap.isEmpty()) {
            Edge current = edgeHeap.poll();

            if (merge(current.component1, current.component2)) {
                min += current.weight;
                if (Math.abs(parents[find(current.component1)]) == N) {
                    break;
                }
            }
        }
        System.out.println(min);
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
        }
        parents[parentOfN] += parents[parentOfM];
        parents[parentOfM] = parentOfN;
        return true;
    }

    private static class Edge implements Comparable<Edge> {
        int weight;
        int component1;
        int component2;

        public Edge(int weight, int component1, int component2) {
            this.weight = weight;
            this.component1 = component1;
            this.component2 = component2;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
