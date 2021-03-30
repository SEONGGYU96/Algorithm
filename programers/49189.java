import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;

        int[] distances = new int[n];
        Arrays.fill(distances, n + 1);
        distances[0] = 0;

        List<Integer>[] edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int[] _edge : edge) {
            edges[_edge[0] - 1].add(_edge[1] - 1);
            edges[_edge[1] - 1].add(_edge[0] - 1);
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0));
        int max = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for (int next : edges[current.index]) {
                if (distances[next] > current.distance + 1) {
                    distances[next] = current.distance + 1;
                    queue.add(new Node(next, current.distance + 1));
                    max = Math.max(max, current.distance + 1);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (distances[i] == max) {
                answer++;
            }
        }

        return answer;
    }

    private static class Node {
        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }
}
