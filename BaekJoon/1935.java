import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String expression = br.readLine();
        int[] corresponds = new int[N];
        for (int i = 0; i < N; i++) {
            corresponds[i] = Integer.parseInt(br.readLine());
        }
        System.out.printf("%.2f%n", solution(expression, corresponds));
    }

    public static double solution(String expression, int[] corresponds) {
        final int PREINT = 65;
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);
            if (current != '+' && current != '-' && current != '/' && current != '*') {
                stack.push((double) corresponds[current - PREINT]);
            } else {
                double result;
                double second = stack.pop();
                double first = stack.pop();
                switch (current) {
                    case '+' :
                        result = first + second;
                        break;

                    case '-' :
                        result = first - second;
                        break;

                    case '*' :
                        result = first * second;
                        break;

                    default :
                        result = first / second;
                        break;
                }
                stack.push(result);
            }
        }
        return stack.pop();
    }
}
