class Solution {
        public String solution(String s, int n) {
            StringBuilder answer = new StringBuilder();

            for (int i = 0; i < s.length(); i++) {
                char character = s.charAt(i);

                if (character == ' ') {
                    answer.append(character);
                    continue;
                }

                if (Character.getNumericValue(character) + n > 35) {
                    character = (char) (character + n - 26);
                } else {
                    character = (char) (character + n);
                }

                answer.append(character);
            }

            return answer.toString();
        }
    }
