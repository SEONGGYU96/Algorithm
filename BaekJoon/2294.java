import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] values = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            values[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(values);
        System.out.println(solution(values, k));
    }

    private static int solution(int[] values, int k) {
        final int MAX = 10001;
        int[] dp = new int[k + 1];

        Arrays.fill(dp, MAX);
        dp[0] = 0;

        for (int i = 1; i < values.length; i++) {
            for (int j = values[i]; j < k + 1; j++) {
                dp[j] = Math.min(dp[j], dp[j - values[i]] + 1);
            }
        }
        int result = dp[k];
        return result == MAX ? -1 : result;
    }
}
