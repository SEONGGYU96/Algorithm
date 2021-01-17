import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N % 2 == 1) {
            System.out.println(0);
            return;
        }

        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[2] = 3;
        System.out.println(solution(dp, N));
    }

    private static int solution(int[] dp, int N) {
        if (N % 2 == 1) {
            return 0;
        }
        if (dp[N] != 0) {
            return dp[N];
        }

        dp[N] = dp[2] * solution(dp, N - 2);
        for (int i = 0; i <= N - 4; i++) {
            dp[N] += 2 * solution(dp, i);
        }
        return dp[N];
    }
}
