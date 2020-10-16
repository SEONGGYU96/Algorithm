import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        long[] trees = new long[N];
        long max = -1;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Long.parseLong(st.nextToken());
            if (i == 0) {
                max = trees[i];
                continue;
            }
            if (max < trees[i]) {
                max = trees[i];
            }
        }

        System.out.println(solution(M, trees, max));
    }

    public static long solution(long M, long[] trees, long max) {
        long head = 0;
        long tail = max;
        long H = -1;

        while (tail - head > 1) {
            H = (head + tail) / 2;
            long sum = 0;

            for (long tree : trees) {
                long extra = tree - H;
                if (extra < 0) {
                    continue;
                }
                sum += extra;
            }
            if (sum < M) {
                tail = H;
            } else {
                head = H;
            }
        }
        return head;
    }
}

