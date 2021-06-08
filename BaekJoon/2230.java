import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        if (M == 0) {
            System.out.println(0);
            return;
        }
        int[] sequence = new int[N];

        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(sequence);

        int head = 0;
        int tail = 0;
        int answer = Integer.MAX_VALUE;
        while (tail < N) {
            int diff = Math.abs(sequence[head] - sequence[tail]);
            if (diff >= M) {
                answer = Math.min(answer, diff);
                head++;
            } else {
                tail++;
            }
        }
        System.out.println(answer);
    }
}


