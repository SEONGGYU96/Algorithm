import java.util.*;

class Solution {

    String[] dp;

    public String solution(int n, int t, int m, int p) {
        String answer = "";
        dp = new String[p + m * (t - 1)];

        String temp = "";
        for (int i = 0; temp.length() < p + m * (t - 1); i++) {
            temp += convert(n, i);
        }
        System.out.println(temp);

        for (int i = p, count = 0; count < t; i += m, count++) {
            answer += temp.charAt(i - 1);
        }

        return answer;
    }

    private String convert(int base, int n) {
        if (n == 0) {
            return "0";
        }
        Stack<Integer> stack = new Stack<>();
        while (n > 0) {
            stack.push(n % base);
            n /= base;
        }
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (current >= 10) {
                builder.append((char) (65 + current - 10));
            } else {
                builder.append(current);
            }
        }
        return builder.toString();
    }
}
