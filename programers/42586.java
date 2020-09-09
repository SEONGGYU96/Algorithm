import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
        public int[] solution(int[] progresses, int[] speeds) {
            Queue<Integer> releaseStack = new LinkedList<>();
            List<Integer> answerList = new ArrayList<>();


            for (int i = 0; i < progresses.length; i++) {
                int extraDay = (100 - progresses[i]) / speeds[i];
                if ((100 - progresses[i]) % speeds[i] > 0) {
                    extraDay++;
                }

                if (!releaseStack.isEmpty() && releaseStack.peek() < extraDay) {
                    answerList.add(releaseStack.size());
                    releaseStack.clear();
                }
                releaseStack.offer(extraDay);
            }

            if (!releaseStack.isEmpty()) {
                answerList.add(releaseStack.size());
            }

            int[] answer = new int[answerList.size()];

            for (int i = 0; i < answerList.size(); i++) {
                answer[i] = answerList.get(i);
            }

            return answer;
        }
    }
