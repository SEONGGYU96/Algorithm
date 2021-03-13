import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        int[] timeSum = new int[N];
        int[][] times = new int[N][N];
        Arrays.fill(timeSum,  500001);
        timeSum[0] = 0;

        for (int[] _road : road) {
            int a = _road[0] - 1;
            int b = _road[1] - 1;
            int c = _road[2];
            if (times[a][b] == 0) {
                times[a][b] = c;
            } else {
                times[a][b] = Math.min(times[a][b], c);
            }
            if (times[b][a] == 0) {
                times[b][a] = c;
            } else {
                times[b][a] = Math.min(times[b][a], c);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            int[] nearbyTowns = times[current];
            for (int townIndex = 0; townIndex < nearbyTowns.length; townIndex++) {
                int time = nearbyTowns[townIndex];
                if (time == 0) {
                    continue;
                }
                int totalTime = time + timeSum[current];
                if (timeSum[townIndex] > totalTime) {
                    queue.add(townIndex);
                    timeSum[townIndex] = totalTime;
                }
            }
        }

        for (int time : timeSum) {
            if (time <= K) {
                answer++;
            }
        }
        return answer;
    }
}
