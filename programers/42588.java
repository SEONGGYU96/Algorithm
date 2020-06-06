class Solution {
        public int[] solution(int[] heights) {
            int[] answer = new int[heights.length];
            
            for (int transmit_tower = 0; transmit_tower < heights.length; transmit_tower++) {
                answer[transmit_tower] = 0;
                for (int receive_tower = transmit_tower - 1; receive_tower >= 0; receive_tower--) {

                    if (heights[transmit_tower] < heights[receive_tower]) {
                        answer[transmit_tower] = receive_tower + 1;
                        break;
                    }
                }
            }
            
            return answer;
        }
    }
