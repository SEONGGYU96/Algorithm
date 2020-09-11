import java.util.Arrays;

class Solution {
        public int solution(int[] citations) {
            int answer = 0;

            Arrays.sort(citations);

            for (int i = citations.length - 1; i >= 0; i--) {
                if (citations[i] < ++answer) {
                    answer--;
                    break;
                }
            }

            return answer;
        }
    }
