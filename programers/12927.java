import java.util.*;

class Solution {
    
    public long solution(int n, int[] works) {
        long answer = 0;
        
        Queue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int work : works) {
            heap.offer(work);
        }
        
        while (n > 0) {
            int current = heap.poll();
            if (current == 0) {
                return 0;
            }
            current--;
            n--;
            heap.offer(current);
        }
        while (!heap.isEmpty()) {
            int current = heap.poll();
            answer += current * current;
        }
        return answer;
    }
}
