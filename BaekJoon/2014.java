import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] baseNumbers = new int[K];
        Queue<Long> queue = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < K; i++) {
            int current = Integer.parseInt(st.nextToken());
            baseNumbers[i] = current;
        }

        queue.add(1L);

        for (int i = 0; i < N; i++) {
            long current = queue.poll();

            for (int baseNumber : baseNumbers) {
                queue.add(current * baseNumber);
                if (current % baseNumber == 0) {
                    break;
                }
            }
        }
        System.out.println(queue.poll());
    }
}
