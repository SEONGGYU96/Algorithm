class Solution {
        public String solution(String phone_number) {
            String[] parsing = phone_number.split("");
            StringBuilder answer = new StringBuilder();

            for (int i = 0; i < parsing.length; i++) {
                if (i > parsing.length - 5) {
                    answer.append(parsing[i]);
                } else {
                    answer.append("*");
                }
            }
            return answer.toString();
        }
    }
