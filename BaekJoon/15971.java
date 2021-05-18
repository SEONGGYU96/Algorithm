import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int robot1 = Integer.parseInt(st.nextToken()) - 1;
        int robot2 = Integer.parseInt(st.nextToken()) - 1;
        ArrayList<Edge>[] edges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) -1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int length = Integer.parseInt(st.nextToken());
            edges[start].add(new Edge(end, length));
            edges[end].add(new Edge(start, length));
        }
        boolean[] isVisited = new boolean[n];
        Queue<Robot> queue = new LinkedList<>();
        queue.offer(new Robot(0, robot1, 0));
        while (!queue.isEmpty()) {
            Robot current = queue.poll();
            if (current.position == robot2) {
                System.out.println(current.distance - current.maxLength);
                break;
            }
            for (Edge edge : edges[current.position]) {
                int next = edge.destination;
                int nextDistance = current.distance + edge.length;
                if (isVisited[next]) {
                    continue;
                }
                isVisited[next] = true;
                queue.offer(new Robot(nextDistance, edge.destination, Math.max(current.maxLength, edge.length)));
            }
        }

    }

    private static class Robot {
        private final int distance;
        private final int position;
        private final int maxLength;

        public Robot(int distance, int position, int maxLength) {
            this.distance = distance;
            this.position = position;
            this.maxLength = maxLength;
        }
    }


    private static class Edge {
        private final int destination;
        private final int length;

        public Edge(int destination, int length) {
            this.destination = destination;
            this.length = length;
        }
    }
}
