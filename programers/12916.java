class Solution {
        boolean solution(String s) {
            boolean answer = true;

            String string = s.toLowerCase();
            int p_y_stack = 0;

            for (int i = 0; i < string.length(); i++) {
                char c = string.charAt(i);
                if (c == 'p') {
                    p_y_stack++;
                } else if (c == 'y') {
                    p_y_stack--;
                }
            }

            answer = p_y_stack == 0;

            return answer;
        }
    }
