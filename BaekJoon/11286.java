import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
            if (Math.abs(o1) < Math.abs(o2)) {
                return -1;
            } else if (Math.abs(o1) > Math.abs(o2)) {
                return 1;
            } else {
                return o1.compareTo(o2);
            }
        });

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

