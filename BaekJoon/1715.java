import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            queue.add(Integer.parseInt(br.readLine()));
        }
        int answer = 0;

        while (queue.size() >= 2) {
            int draw1 = queue.poll();
            int draw2 = queue.poll();
            int sum = draw1 + draw2;
            answer += sum;
            queue.add(sum);
        }
        System.out.println(answer);
    }
}

