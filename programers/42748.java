import java.util.ArrayList;
import java.util.Collections;

class Solution {
        public int[] solution(int[] array, int[][] commands) {
            ArrayList<Integer> answerList = new ArrayList<>();

            for (int[] commend : commands) {
                answerList.add(parseArray(array, commend));
            }

            int[] answer = new int[answerList.size()];

            for (int i = 0; i < answer.length; i++) {
                answer[i] = answerList.get(i);
            }

            return answer;
        }

        private int parseArray(int[] array, int[] commend) {
            ArrayList<Integer> parsedArray = new ArrayList<>();

            for (int i = commend[0]; i <= commend[1]; i++) {
                parsedArray.add(array[i - 1]);
            }

            Collections.sort(parsedArray);

            return parsedArray.get(commend[2] - 1);
        }
    }
