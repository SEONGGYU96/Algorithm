import java.util.ArrayList;

public class Solution {
        public int[] solution(int []arr) {
            int[] answer;

            int temp = -1;
            
            ArrayList<Integer> answers = new ArrayList<>();

            for (int number : arr) {
                if (number != temp) {
                    temp = number;
                    answers.add(number);
                }
            }
            
            answer = new int[answers.size()];
            
            for (int i = 0; i < answer.length; i++) {
                answer[i] = answers.get(i);
            }

            return answer;
        }
    }
