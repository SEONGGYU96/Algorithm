import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < N; i++) {
            int commend = Integer.parseInt(br.readLine());
            if (commend == 0) {
                if (queue.isEmpty()) {
                    answer.append(0);
                } else {
                    answer.append(queue.poll());
                }
                answer.append("\n");
                continue;
            }
            queue.add(commend);
        }

        System.out.println(answer);
    }
}

