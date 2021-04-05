import java.util.*;

class Solution {
    private static final int S = 0;
    private static final int A = 1;
    private static final int B = 2;
    private static final int MAX_COST = Integer.MAX_VALUE;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        ArrayList<Edge>[] elements = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            elements[i] = new ArrayList<>();
        }

        for (int[] fare : fares) {
            elements[fare[0] - 1].add(new Edge(fare[1] - 1, fare[2]));
            elements[fare[1] - 1].add(new Edge(fare[0] - 1, fare[2]));
        }

        int[][] costMap = new int[3][n];
        for (int[] row : costMap) {
            Arrays.fill(row, MAX_COST);
        }
        costMap[S][s - 1] = 0;
        costMap[A][a - 1] = 0;
        costMap[B][b - 1] = 0;

        Queue<Taxi> queue = new LinkedList<>();
        queue.offer(new Taxi(S, 0, s - 1));
        queue.offer(new Taxi(A, 0, a - 1));
        queue.offer(new Taxi(B, 0, b - 1));

        while (!queue.isEmpty()) {
            Taxi current = queue.poll();

            for (Edge next : elements[current.position]) {
                int previousCost = costMap[current.from][next.destination];
                int currentCost = current.sumOfCost + next.cost;
                if (currentCost < previousCost) {
                    costMap[current.from][next.destination] = currentCost;
                    queue.offer(new Taxi(current.from, currentCost, next.destination));
                }
            }
        }

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < 3; j++) {
                sum += costMap[j][i];
            }
            answer = Math.min(answer, sum);
        }

        return answer;
    }

    private static class Edge {
        private int destination;
        private int cost;

        public Edge(int destination, int cost) {
            this.destination = destination;
            this.cost = cost;
        }
    }

    private static class Taxi {
        private final int from;
        private int sumOfCost;
        private int position;

        public Taxi(int from, int sumOfCost, int position) {
            this.from = from;
            this.sumOfCost = sumOfCost;
            this.position = position;
        }
    }
}
