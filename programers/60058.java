import java.util.*;

class Solution {
    public String solution(String p) {
        if (p.isEmpty() || isBalanced(p)) {
            return p;
        }

        int leftCount = 0;
        int rightCount = 0;
        String u = "";
        String v = "";
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                leftCount++;
            } else {
                rightCount++;
            }
            if (leftCount == rightCount) {
                u = p.substring(0, i + 1);
                v = p.substring(i + 1);
                break;
            }
        }

        if (isBalanced(u)) {
            return u + solution(v);
        }

        StringBuilder builder = new StringBuilder("(");
        builder.append(solution(v))
                .append(")");
        for (int i = 1; i < u.length() - 1; i++) {
            builder.append(u.charAt(i) == ')' ? "(" : ")");
        }

        return builder.toString();
    }

    private boolean isBalanced(String p) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (stack.isEmpty() || stack.peek() != '(' || c != ')') {
                stack.push(c);
            } else  {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
