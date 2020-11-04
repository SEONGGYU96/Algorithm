import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String currentOrder = st.nextToken();
            switch (currentOrder) {
                case "push" :
                    stack.push(Integer.parseInt(st.nextToken()));
                    break;

                case "pop" :
                    System.out.println(stack.isEmpty() ? -1 : stack.pop());
                    break;

                case "size" :
                    System.out.println(stack.size());
                    break;

                case "empty" :
                    System.out.println(stack.isEmpty() ? 1 : 0);
                    break;

                case "top" :
                    System.out.println(stack.isEmpty() ? -1 : stack.peek());
                    break;
            }
        }
    }
}
