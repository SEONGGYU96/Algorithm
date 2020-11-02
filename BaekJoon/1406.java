import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String initial = br.readLine();
        Stack<String> before = new Stack<>();
        Stack<String> after = new Stack<>();
        for (int i = 0; i < initial.length(); i++) {
            before.push(initial.substring(i, i + 1));
        }
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String input = st.nextToken();

            switch (input) {
                case "P" :
                    before.push(st.nextToken());
                    break;
                case "D" :
                    if (!after.isEmpty()) {
                        before.push(after.pop());
                    }
                    break;
                case "L" :
                    if (!before.isEmpty()) {
                        after.push(before.pop());
                    }
                    break;
                case "B" :
                    if (!before.isEmpty()) {
                        before.pop();
                    }
                    break;
            }
        }
        while (!before.isEmpty()) {
            after.push(before.pop());
        }
        while(!after.isEmpty()) {
            bw.write(after.pop());
        }
        bw.flush();
        bw.close();
    }
}
