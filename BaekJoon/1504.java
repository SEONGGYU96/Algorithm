import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    private static final int MAX = 200000001;
    private static ArrayList<Route>[] routes;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        routes = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            routes[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            routes[s].add(new Route(e, c));
            routes[e].add(new Route(s, c));
        }
        st = new StringTokenizer(br.readLine());
        int stopover1 = Integer.parseInt(st.nextToken()) - 1;
        int stopover2 = Integer.parseInt(st.nextToken()) - 1;

        int distOfStopovers = getMinDist(stopover1, stopover2);

        int dist1 = getMinDist(0, stopover1) + distOfStopovers + getMinDist(stopover2, N - 1);
        int dist2 = getMinDist(0, stopover2) + distOfStopovers + getMinDist(stopover1, N - 1);
        int result = Math.min(dist1, dist2);
        System.out.println(result >= MAX ? -1 : result);
    }

    private static int getMinDist(int from, int to) {
        if (from == to) {
            return 0;
        }
        int[] costs = new int[N];
        Arrays.fill(costs, MAX);
        costs[from] = 0;

        Queue<Position> queue = new PriorityQueue<>();
        queue.offer(new Position(from, 0));
        boolean[] isVisited = new boolean[N];

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            if (isVisited[current.position]) {
                continue;
            }
            isVisited[current.position] = true;
            for (Route route : routes[current.position]) {
                if (isVisited[route.destination]) {
                    continue;
                }
                int nextCost = current.sumOfCost + route.cost;
                if (costs[route.destination] > nextCost) {
                    costs[route.destination] = nextCost;
                    queue.offer(new Position(route.destination, nextCost));
                }
            }
        }
        return costs[to];
    }

    private static class Position implements Comparable<Position> {
        private final int position;
        private final int sumOfCost;

        public Position(int position, int sumOfCost) {
            this.position = position;
            this.sumOfCost = sumOfCost;
        }

        @Override
        public int compareTo(Position o) {
            return Integer.compare(sumOfCost, o.sumOfCost);
        }
    }

    private static class Route {
        private final int destination;
        private final int cost;

        public Route(int destination, int cost) {
            this.destination = destination;
            this.cost = cost;
        }
    }
}
