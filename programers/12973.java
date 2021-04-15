import java.util.*;

class Solution {
    public int solution(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (stack.isEmpty() || stack.peek() != current) {
                stack.push(current);
            } else {
                stack.pop();
            }
        }
        return stack.isEmpty() ? 1 : 0;
    }
}
