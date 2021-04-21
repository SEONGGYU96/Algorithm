import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        
        String temp = s;
        for (int i = 0; i < s.length(); i++) {
            temp = rotate(temp);
            if (isValid(temp)) {
                answer++;
            }
        }
        return answer;
    }
    
    private String rotate(String s) {
        return s.substring(1) + s.charAt(0);
    }
    
    private boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(current);
                continue;
            }
            char previous = stack.peek();
            if (previous == ']' || previous == '}' | previous == ')') {
                return false;
            }
            if ((previous == '[' && current == ']') || (
                    previous == '{' && current == '}') || (
                    previous == '(' && current == ')')) {
                stack.pop();
            } else {
                stack.push(current);
            }
        }
        return stack.isEmpty();
    }
}
