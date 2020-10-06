import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        System.out.println(solution(N));
    }

    private static long solution(int N) {
        long[][] dp = new long[N + 1][11];

        Arrays.fill(dp[1], 1);
        dp[1][0] = 0;
        dp[1][10] = 0;

        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i - 1][1];
            for (int j = 1; j <= 9; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1000000000;
            }
        }
        return Arrays.stream(dp[N]).sum() % 1000000000;
    }
}
