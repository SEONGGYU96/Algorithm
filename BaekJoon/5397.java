import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            bw.write(solution(br.readLine()) + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static String solution(String str) {
        StringBuilder answer = new StringBuilder();
        Stack<Character> before = new Stack<>();
        Stack<Character> after = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            switch (current) {
                case '<' :
                    if (!before.isEmpty()) {
                        after.push(before.pop());
                    }
                    break;

                case '>' :
                    if (!after.isEmpty()) {
                        before.push(after.pop());
                    }
                    break;

                case '-' :
                    if (!before.isEmpty()) {
                        before.pop();
                    }
                    break;
                    
                default:
                    before.push(current);
                    break;
            }
        }
        while (!before.isEmpty()) {
            after.push(before.pop());
        }
        while (!after.isEmpty()) {
            answer.append(after.pop());
        }
        return answer.toString();
    }
}
