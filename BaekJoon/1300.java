import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        System.out.println(solution(N, K));
    }

    public static int solution(int N, int K) {
        int head = 1;
        int tail = K;
        int answer = 0;

        while (head <= tail) {
            int middle = (head + tail) / 2;
            long count = 0;
            boolean isBig = false;
            for (int i = 1; i <= N; i++) {
                count += Math.min(middle / i, N);
                if (count >= K) {
                    isBig = true;
                    break;
                }
            }
            if (isBig) {
                tail = middle - 1;
                answer = middle;
            } else {
                head = middle + 1;
            }
        }
        return answer;
    }
}
