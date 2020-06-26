import java.util.ArrayList;

class Solution {
        public int solution(int n) {
            int answer = 0;

            ArrayList<Boolean> array = new ArrayList<>();

            array.add(false);
            array.add(false);

            for (int i = 2; i <= n; i++) {
                array.add(true);
            }

            for (int i = 2; (i * i) <= n; i++) {
                if (array.get(i)) {
                    for (int j = i * 2; j <= n; j += i) array.set(j, false);
                }
            }

            for(Boolean b : array) {
                if (b) {
                    answer++;
                }
            }

            return answer;
        }
    }
