import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        String[] lines = new String[N];
        for (int i = 0; i < N; i++) {
            lines[i] = br.readLine();
        }
        solution(lines);
    }

    public static void solution(String[] lines) {
        LinkedList<Integer> queue = new LinkedList<>();

        for (String line : lines) {
            switch (line) {
                default:
                    int num = Integer.parseInt(line.substring(5));
                    queue.offer(num);
                    break;

                case "pop":
                    if (queue.isEmpty()) {
                        System.out.println(-1);
                    } else {
                        System.out.println(queue.poll());
                    }
                    break;

                case "size":
                    System.out.println(queue.size());
                    break;

                case "empty":
                    System.out.println(queue.isEmpty() ? 1 : 0);
                    break;

                case "front":
                    if (queue.isEmpty()) {
                        System.out.println(-1);
                    } else {
                        System.out.println(queue.peek());
                    }
                    break;

                case "back":
                    if (queue.isEmpty()) {
                        System.out.println(-1);
                    } else {
                        System.out.println(queue.get(queue.size() - 1));
                    }
                    break;
            }
        }
    }
}
