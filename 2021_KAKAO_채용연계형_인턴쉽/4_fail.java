import java.util.*;

class Solution {
    private static ArrayList<Edge>[] edges;
    private static boolean[] isChanged;
    private static boolean[] isTrap;
    private static int[][] times;

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        edges = new ArrayList[n];
        isChanged = new boolean[n];
        isTrap = new boolean[n];
        times = new int[2][n];
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int trap : traps) {
            isTrap[trap - 1] = true;
        }
        for (int i = 0; i < 2; i++) {
            Arrays.fill(times[i], Integer.MAX_VALUE);
        }

        for (int[] road : roads) {
            int s = road[0] - 1;
            int e = road[1] - 1;
            Edge edge = new Edge(road);
            edges[s].add(edge);
            edges[e].add(edge);
        }
        move(start - 1, 0);

        return Math.min(times[0][end - 1], times[1][end - 1]);
    }

    private void move(int start, int currentTime) {
        for (Edge edge : edges[start]) {
            int nextPosition = edge.getDestination();
            if (nextPosition == start) {
                continue;
            }
            int nextTime = currentTime + edge.time;
            int nextState = isChanged[nextPosition] ? 1 : 0;
            if (times[nextState][nextPosition] > nextTime) {
                times[nextState][nextPosition] = nextTime;
                if (isTrap[nextPosition]) {
                    isChanged[nextPosition] = !isChanged[nextPosition];
                    for (Edge nextEdges : edges[nextPosition]) {
                        nextEdges.changeState();
                    }
                    move(nextPosition, nextTime);
                    for (Edge nextEdges : edges[nextPosition]) {
                        nextEdges.changeState();
                    }
                    isChanged[nextPosition] = !isChanged[nextPosition];
                } else {
                    move(nextPosition, nextTime);
                }
            }
        }
    }

    private static class Edge {
        private final int[] destination;
        private final int time;
        private int state = 0;

        public Edge(int[] road) {
            this.destination = new int[]{road[1] - 1, road[0] - 1};
            this.time = road[2];
        }

        public int getDestination() {
            return destination[state];
        }

        public void changeState() {
            state = state == 0 ? 1 : 0;
        }
    }
}
