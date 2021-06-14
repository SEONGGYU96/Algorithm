import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    private static final int MAX = 100000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] costs = new int[N];
        Arrays.fill(costs, MAX);
        ArrayList<Route>[] routes = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            routes[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            routes[s].add(new Route(e, c));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;

        Queue<Position> queue = new PriorityQueue<>();
        queue.offer(new Position(start, 0));
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

        System.out.println(costs[end]);
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
