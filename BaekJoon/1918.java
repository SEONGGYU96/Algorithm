import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        solution(br.readLine());
    }

    public static void solution(String str) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            if (current == '+' || current == '-' || current == '*' || current == '/') {
                while (!check(stack, current)) {
                    bw.write(stack.pop());
                }
                stack.push(current);
            } else if (current == '(') {
                stack.push(current);
            } else if (current == ')') {
                while (true) {
                    char temp = stack.pop();
                    if (temp == '(') {
                        break;
                    }
                    bw.write(temp);
                }
            } else {
                bw.write(current);
            }
        }
        while (!stack.isEmpty()) {
            bw.write(stack.pop());
        }
        bw.flush();
        bw.close();
    }

    private static boolean check(Stack<Character> stack, char current) {
        if (stack.isEmpty() || stack.peek() == '(') {
            return true;
        } else {
            return (current == '*' || current == '/') && (stack.peek() == '+' || stack.peek() == '-');
        }
    }
}
