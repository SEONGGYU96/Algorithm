class Solution {
        public String solution(String s) {
            String answer;
            int centerIndex = s.length() / 2;
            
            if (s.length() % 2 == 0) {
                answer = s.substring(centerIndex - 1, s.length() - centerIndex + 1);
            } else {
                answer = String.valueOf(s.charAt(centerIndex));
            }

            return answer;
        }
    }
