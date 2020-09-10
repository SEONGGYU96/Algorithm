import java.util.LinkedList;
import java.util.Queue;

class Solution {
        public int solution(int bridge_length, int weight, int[] truck_weights) {
            int answer = 0;
            int head = 0;
            int sumWeight = 0;

            Queue<Integer> bridge = new LinkedList<>();

            while (sumWeight != 0 || head != truck_weights.length) {
                answer++;

                if (bridge.size() >= bridge_length) {
                    sumWeight -= bridge.poll();
                }

                if (head != truck_weights.length && sumWeight + truck_weights[head] <= weight) {
                    bridge.offer(truck_weights[head]);
                    sumWeight += truck_weights[head++];

                } else {
                    bridge.offer(0);
                }

            }

            return answer;
        }
    }
