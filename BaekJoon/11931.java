import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        int N = scanner.nextInt();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            stack.push(scanner.nextInt());
        }

        Stack<Integer> buffer = new Stack<>();

        while (!stack.isEmpty()) {
            int value = stack.pop();
            while (!buffer.isEmpty() && buffer.peek() > value) {
                stack.push(buffer.pop());
            }
            buffer.push(value);
        }

        while (!buffer.isEmpty()) {
            System.out.println(buffer.pop());
        }
    }
}
