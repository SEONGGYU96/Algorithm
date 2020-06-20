import java.util.ArrayList;
import java.util.Collections;

class Solution {
        public int[] solution(int[] arr, int divisor) {
            int[] answer = {};

            ArrayList<Integer> array = new ArrayList<>();

            for (int num : arr) {
                if (num % divisor == 0) {
                    array.add(num);
                }
            }

            if (array.isEmpty()) {
                return new int[]{-1};
            }

            Collections.sort(array);

            answer = new int[array.size()];

            for (int i = 0; i < answer.length; i++) {
                answer[i] = array.get(i);
            }

            return answer;
        }
    }
