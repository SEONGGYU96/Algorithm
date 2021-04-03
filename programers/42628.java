import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];

        int size = 0;
        int m = 0;
        Queue<Integer> minHeap = new PriorityQueue<>();
        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        for (String operation : operations) {
            StringTokenizer st = new StringTokenizer(operation);
            String operator = st.nextToken();
            int n = Integer.parseInt(st.nextToken());

            if (operator.equals("I")) {
                minHeap.offer(n);
                maxHeap.offer(n);
                size++;
            } else {
                if (size != 0) {
                    if (n == 1) {
                        minHeap.remove(maxHeap.poll());
                    } else {
                        maxHeap.remove(minHeap.poll());
                    }
                    size--;
                }
            }
        }
        if (size == 0) {
            answer = new int[]{0, 0};
        } else {
            answer[0] = maxHeap.peek();
            answer[1] = minHeap.peek();
        }
        return answer;
    }
}
