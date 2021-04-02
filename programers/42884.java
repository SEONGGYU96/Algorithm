import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;

        Arrays.sort(routes, Comparator.comparing(o -> o[1]));
        Queue<int[]> queue = new LinkedList<>(Arrays.asList(routes));

        while (!queue.isEmpty()) {
            int camera = queue.poll()[1];
            answer++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] next = queue.poll();
                if (next[0] > camera) {
                    queue.offer(next);
                }
            }
        }
        return answer;
    }
}
