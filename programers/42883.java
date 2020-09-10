import java.util.Stack;

class Solution {
        public String solution(String number, int k) {
            StringBuilder answer = new StringBuilder();
            Stack<Character> numberStack = new Stack<>();
            int answerLength = number.length() - k;

            for (int i = 0; i < number.length(); i++) {
                char currentNumber = number.charAt(i);
                while (!numberStack.isEmpty() && numberStack.peek() < currentNumber && k > 0) {
                    numberStack.pop();
                    k--;
                }
                numberStack.push(currentNumber);
            }

            for (int i = 0; i < answerLength; i++) {
                answer.append(numberStack.get(i));
            }

            return answer.toString();
        }
    }

