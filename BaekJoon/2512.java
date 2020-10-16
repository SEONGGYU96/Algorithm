import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] budget = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = -1;
        for (int i = 0; i < N; i++) {
            budget[i] = Integer.parseInt(st.nextToken());
            if (i == 0) {
                max = budget[i];
                continue;
            }
            if (budget[i] > max) {
                max = budget[i];
            }
        }
        long M = Long.parseLong(br.readLine());
        System.out.println(solution(M, budget, max + 1));
    }

    public static int solution(long M, int[] budget, int max) {
        int head = 0;
        int tail = max;
        while (tail - head > 1) {
            int upperLimit = (head + tail) / 2;
            long sum = 0;
            for (int j : budget) {
                sum += Math.min(j, upperLimit);
            }
            if (sum > M) {
                tail = upperLimit;
            } else {
                head = upperLimit;
            }
        }
        return head;
    }
}

