import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    private static final int MAX = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        ArrayList<Way>[] graph = new ArrayList[N + 1];
        ArrayList<Way>[] reverseGraph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            graph[start].add(new Way(end, time));
            reverseGraph[end].add(new Way(start, time));
        }

        int[] go = diikstra(N, X, reverseGraph);
        int[] come = diikstra(N, X, graph);
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            int sum = go[i] + come[i];
            answer = Math.max(answer, sum);
            
        }
        System.out.println(answer);
    }

    private static int[] diikstra(int N, int start, ArrayList<Way>[] graph) {
        int[] distances = new int[N + 1];
        Arrays.fill(distances, MAX);
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(start, 0));

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            for (Way way : graph[current.position]) {
                int nextTime = current.times + way.time;
                if (distances[way.destination] > nextTime) {
                    distances[way.destination] = nextTime;
                    queue.offer(new Position(way.destination, nextTime));
                }
            }
        }
        return distances;
    }

    private static class Position {
        private final int position;
        private final int times;

        public Position(int position, int times) {
            this.position = position;
            this.times = times;
        }
    }

    private static class Way {
        private final int destination;
        private final int time;

        public Way(int destination, int time) {
            this.destination = destination;
            this.time = time;
        }
    }
}


