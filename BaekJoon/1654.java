import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        long[] cable = new long[K];
        long min = -1;
        for (int i = 0; i < K; i++) {
            cable[i] = Integer.parseInt(br.readLine());
            if (i == 0) {
                min = cable[i];
            } else {
                min = Math.max(min, cable[i]);
            }
        }
        System.out.println(solution(N, cable, min));
    }

    public static long solution(int N, long[] cable, long min) {
        long head = 1;
        long tail = min;

        while (head <= tail) {
            int count = 0;
            long middle = (head + tail) / 2;
            for (long _cable : cable) {
                count += _cable / middle;
                if (count >= N) {
                    break;
                }
            }
            if (count >= N) {
                head = middle + 1;
            } else {
                tail = middle - 1;
            }
        }
        return tail;
    }
}

