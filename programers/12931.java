public class Solution {
        public int solution(int n) {
            int answer = 0;
            String str = String.valueOf(n);
            String[] strArray = str.split("");
            
            for (String nn : strArray) {
                answer += Integer.parseInt(nn);
            }

            return answer;
        }
    }
