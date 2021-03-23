import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    
    private int[] parents;
    private int n;
    
    public int solution(int[][] land, int height) {
        int answer = 0;
        n = land.length;
        final int[] rowOffset = {-1, 0, 1, 0};
        final int[] colOffset = {0, 1, 0, -1};
        parents = new int[n * n];
        Arrays.fill(parents, -1);

        Queue<Position> queue = new LinkedList<>();
        Queue<Edge> edgeHeap = new PriorityQueue<>();
        queue.add(new Position(0, 0));
        boolean[][] isVisited = new boolean[n][n];

        int numberOfComponent = n * n;

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            int weight = land[current.row][current.col];
            for (int i = 0; i < rowOffset.length; i++) {
                int nextRow = current.row + rowOffset[i];
                int nextCol = current.col + colOffset[i];
                if (nextRow >= n || nextCol >= n || nextRow < 0 || nextCol < 0) {
                    continue;
                }
                int nextWeight = land[nextRow][nextCol];
                int heightDiff = Math.abs(nextWeight - weight);
                Position nextPosition = new Position(nextRow, nextCol);

                if (heightDiff <= height) {
                    if (merge(getIndex(current.row, current.col), getIndex(nextRow, nextCol))) {
                        numberOfComponent--;
                    }
                } else {
                    edgeHeap.add(new Edge(current, nextPosition, heightDiff));
                }
                if (!isVisited[nextRow][nextCol]) {
                    isVisited[nextRow][nextCol] = true;
                    queue.add(nextPosition);
                }
            }
        }

        while (!edgeHeap.isEmpty() && numberOfComponent != 1) {
            Edge current = edgeHeap.poll();
            if (merge(getIndex(current.component1), getIndex(current.component2))) {
                answer += current.weight;
                numberOfComponent--;
            }
        }
        return answer;
    }
    
    private int getIndex(Position position) {
        return getIndex(position.row, position.col);
    }

    private int getIndex(int row, int col) {
        return row * n + col;
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
        if (parents[parentOfN] > parents[parentOfM]) {
            parents[parentOfM] += parents[parentOfN];
            parents[parentOfN] = parentOfM;
        } else {
            parents[parentOfN] += parents[parentOfM];
            parents[parentOfM] = parentOfN;
        }
        return true;
    }

    private class Position {
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private class Edge implements Comparable<Edge> {
        Position component1;
        Position component2;
        int weight;

        public Edge(Position component1, Position component2, int weight) {
            this.component1 = component1;
            this.component2 = component2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
