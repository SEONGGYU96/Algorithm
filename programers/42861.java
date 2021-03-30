import java.util.*;

class Solution {

    private int[] parents;

    public int solution(int n, int[][] costs) {
        int answer = 0;

        Queue<Bridge> heap = new PriorityQueue<>();
        parents = new int[n];
        Arrays.fill(parents, -1);
        int component = n;

        for (int[] cost : costs) {
            heap.add(new Bridge(cost));
        }

        while (!heap.isEmpty() && component > 1) {
            Bridge current = heap.poll();
            if (!merge(current.island1, current.island2)) {
                continue;
            }
            answer += current.cost;
            component--;
        }
        return answer;
    }

    private int find(int n) {
        if (parents[n] < 0) {
            return n;
        }
        parents[n] = find(parents[n]);
        return parents[n];
    }

    private boolean merge(int n, int m) {
        int parentOfN = find(n);
        int parentOfM = find(m);
        if (parentOfN == parentOfM) {
            return false;
        }
        parents[parentOfM] = parentOfN;
        return true;
    }

    static class Bridge implements Comparable<Bridge> {
        int island1;
        int island2;
        int cost;

        public Bridge(int island1, int island2, int cost) {
            this.island1 = island1;
            this.island2 = island2;
            this.cost = cost;
        }

        public Bridge(int[] cost) {
            this(cost[0], cost[1], cost[2]);
        }

        @Override
        public int compareTo(Bridge o) {
            return Integer.compare(cost, o.cost);
        }
    }
}
