import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        System.out.println(solution(N, K));
    }

    public static int solution(int N, int K) {
        int[][] dp = new int[N + 1][K + 1];

        dp[1][0] = 1;
        if (K >= 1) {
            dp[1][1] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= Math.min(i, K); j++) {
                if (i == j || j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                dp[i][j] = (dp[i - 1][j] + dp[i - 1][j - 1]) % 10007;
            }
        }
        return dp[N][K];
    }
}
