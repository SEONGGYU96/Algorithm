import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
        public int solution(int[] d, int budget) {
            int answer = 0;

            List<Integer> numList = Arrays.stream(d).sorted().boxed().collect(Collectors.toList());

            for (int request: numList) {
                budget -= request;
                if (budget < 0) {
                    break;
                }
                answer++;
            }
            return answer;
        }
    }
