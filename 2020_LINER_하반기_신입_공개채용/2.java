import java.util.LinkedList;
import java.util.Queue;

class Solution {
        public int[] solution(int[] ball, int[] order) {
            int[] answer = new int[ball.length];
            Queue<Integer> orderQueue = new LinkedList<>();

            int left = 0;
            int right = ball.length - 1;
            int orderIndex = 0;
            int answerIndex = 0;
            while (left <= right) {
                int currentOrder;

                if (orderIndex < order.length) {
                    currentOrder = order[orderIndex++];
                } else {
                    currentOrder = orderQueue.poll();
                }

                if (ball[left] == currentOrder) {
                    answer[answerIndex++] = ball[left++];
                    while (!orderQueue.isEmpty() && orderQueue.contains(ball[left])) {
                        orderQueue.remove(ball[left]);
                        answer[answerIndex++] = ball[left++];
                    }
                } else if (ball[right] == currentOrder) {
                    answer[answerIndex++] = ball[right--];
                    while (!orderQueue.isEmpty() && orderQueue.contains(ball[right])) {
                        orderQueue.remove(ball[right]);
                        answer[answerIndex++] = ball[right--];
                    }
                } else {
                    orderQueue.offer(currentOrder);
                }
            }

            return answer;
        }
    }
