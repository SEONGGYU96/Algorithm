public class Solution {
    public long solution(int[][] land, int P, int Q) {
        long answer = Long.MAX_VALUE;
        int max = 0;
        int min = Integer.MAX_VALUE;

        for (int[] row : land) {
            for (int col : row) {
                max = Math.max(max, col);
                min = Math.min(min, col);
            }
        }

        int head = min;
        int tail = max;

        while (head <= tail) {
            int center = (head + tail) / 2;
            long cost = getCost(land, center, P, Q);
            long nextCost = getCost(land, center + 1, P, Q);
            if (cost > nextCost) {
                head = center + 1;
                answer = Math.min(answer, nextCost);
            } else {
                tail = center - 1;
                answer = Math.min(answer, cost);
            }
        }
        return answer;
    }
    
    private long getCost(int[][] land, int height, int P, int Q) {
        long cost = 0;
        for (int[] row : land) {
            for (int col : row) {
                long distance = height - col;
                cost += distance > 0 ? distance * P : distance * Q * -1;
            }
        }
        return cost;
    }
}
