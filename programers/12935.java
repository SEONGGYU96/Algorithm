import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Arrays;

class Solution {
        public int[] solution(int[] arr) {
            int[] answer;

            IntStream intStream = Arrays.stream(arr);

            List<Integer> integerList = Arrays.stream(arr).boxed().collect(Collectors.toList());

            int min = intStream.min().getAsInt();

            for (int i = 0; i < integerList.size(); i++) {
                if (integerList.get(i) == min) {
                    integerList.remove(i);
                    break;
                }
            }

            if (integerList.isEmpty()) {
                integerList.add(-1);
            }

            answer = new int[integerList.size()];

            for (int i = 0; i < answer.length; i++) {
                answer[i] = integerList.get(i);
            }

            return answer;
        }
    }
