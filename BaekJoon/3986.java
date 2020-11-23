import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }
        System.out.println(solution(words));
    }

    public static int solution(String[] words) {
        int answer = 0;
        Stack<Character> pairStack = new Stack<>();

        for (String word : words) {
            for (int j = 0; j < word.length(); j++) {
                char current = word.charAt(j);
                if (!pairStack.isEmpty() && pairStack.peek() == current) {
                    pairStack.pop();
                } else {
                    pairStack.push(current);
                }
            }
            if (pairStack.isEmpty()) {
                answer++;
            }
            pairStack.clear();
        }

        return answer;
    }
}
