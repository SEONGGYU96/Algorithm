import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];
        Arrays.fill(dp, -1);

        System.out.println(solution(dp, N));
    }

    private static int solution(int[] dp, int N) {
        if (N == 1) {
            return 0;
        }
        if (dp[N] != -1) {
            return dp[N];
        }
        int result = solution(dp,N - 1) + 1;

        if (N % 2 == 0) {
            result = Math.min(result, solution(dp, N / 2) + 1);
        }
        if (N % 3 == 0) {
            result = Math.min(result, solution(dp, N / 3) + 1);
        }
        dp[N] = result;
        return result;
    }
}
