import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    private static final int MAX = 3000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int initial = Integer.parseInt(br.readLine()) - 1;
        int[] distances = new int[V];
        Arrays.fill(distances, MAX);
        distances[initial] = 0;
        ArrayList<Edge>[] edges = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int distance = Integer.parseInt(st.nextToken());

            edges[start].add(new Edge(end, distance));
        }

        Queue<Position> queue = new PriorityQueue<>();
        queue.offer(new Position(initial, 0));
        boolean[] isVisited = new boolean[V];

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            isVisited[current.position] = true; 
            for (Edge edge : edges[current.position]) {
                int destination = edge.destination;
                int distance = edge.distance + current.distance;
                if (isVisited[destination]) {
                    continue;
                }
                if (distances[destination] > distance) {
                    distances[destination] = distance;
                    queue.offer(new Position(destination, distance));
                }
            }
        }

        for (int distance : distances) {
            System.out.println(distance == MAX ? "INF" : distance);
        }
    }

    private static class Position implements Comparable<Position> {
        private final int position;
        private final int distance;

        public Position(int position, int distance) {
            this.position = position;
            this.distance = distance;
        }

        @Override
        public int compareTo(Position o) {
            return Integer.compare(distance, o.distance);
        }
    }

    private static class Edge {
        private final int destination;
        private final int distance;

        public Edge(int destination, int distance) {
            this.destination = destination;
            this.distance = distance;
        }
    }
}
