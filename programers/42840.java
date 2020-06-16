import java.util.ArrayList;

class Solution {
        public int[] solution(int[] answers) {
            int[] winner;
            int[] submit1 = {1, 2, 3, 4, 5};
            int[] submit2 = {2, 1, 2, 3, 2, 4, 2, 5};
            int[] submit3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5,};
            int[] answerCount = {0, 0, 0}; 

            for (int i = 0; i < answers.length; i++) {
                if (answers[i] == submit1[i % submit1.length]) {
                    answerCount[0]++;
                }

                if (answers[i] == submit2[i % submit2.length]) {
                    answerCount[1]++;
                }

                if (answers[i] == submit3[i % submit3.length]) {
                    answerCount[2]++;
                }
            }
            
            int max = answerCount[0];
            
            for (int i = 1; i < answerCount.length; i++) {
                if (answerCount[i] > max) {
                    max = answerCount[i];
                }
            }
            
            ArrayList<Integer> winners = new ArrayList<>();
            
            for (int i = 0; i < answerCount.length; i++) {
                if (answerCount[i] == max) {
                    winners.add(i + 1);
                }
            }
            
            winner = new int[winners.size()];
            
            for (int i = 0; i < winner.length; i++) {
                winner[i] = winners.get(i);
            }
            
            return winner;
        }
    }
