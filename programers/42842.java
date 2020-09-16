import java.util.ArrayList;
import java.util.List;

class Solution {
        public int[] solution(int brown, int yellow) {
            int[] answer = new int[2];

            List<Integer> divisor = new ArrayList<>();

            for (int i = 1; i <= yellow / 2; i++) {
                if (yellow % i == 0) {
                    divisor.add(i);
                }
            }

            divisor.add(yellow);

            int wWithH = (brown - 4)/2;

            int h = 0;
            int w = divisor.size() - 1;

            while (h <= w) {
                int currentHeight = divisor.get(h);
                int currentWidth = divisor.get(w);
                if (currentHeight + currentWidth == wWithH) {
                    answer[0] = currentWidth + 2;
                    answer[1] = currentHeight + 2;
                    break;
                }
                h++;
                w--;
            }

            return answer;
        }
    }
