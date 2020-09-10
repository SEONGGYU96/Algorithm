import java.util.Stack;

class Solution {
        public int solution(String s) {
            int answer = s.length();

            for (int i = 1; i <= s.length() / 2; i++) {
                String compressedString = compression(s, i);
                if (compressedString.length() < answer) {
                    answer = compressedString.length();
                }
            }
            compression(s, 2);

            return answer;
        }

        private String compression(String s, int unit) {
            Stack<String> cache = new Stack<>();
            StringBuilder result = new StringBuilder();
            int head = 0;

            while (head < s.length()) {
                String currentToken;
                if (head + unit < s.length()) {
                    currentToken = s.substring(head, head + unit);
                } else {
                    currentToken = s.substring(head);
                }

                if (!cache.isEmpty() && !cache.peek().equals(currentToken)) {
                    if (cache.size() > 1) {
                        result.append(cache.size());
                    }
                    result.append(cache.pop());
                    cache.clear();
                }
                cache.push(currentToken);

                head += unit;
            }

            if (!cache.isEmpty()) {
                if (cache.size() > 1) {
                    result.append(cache.size());
                }
                result.append(cache.pop());
                cache.clear();
            }

            return result.toString();
        }
    }
