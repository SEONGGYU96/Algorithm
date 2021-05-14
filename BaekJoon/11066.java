import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int K = Integer.parseInt(br.readLine());
            int[] files = new int[K];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                files[i] = Integer.parseInt(st.nextToken());
            }
            int[][] dp = new int[K][K];
            for (int i = 0; i < K; i++) {
                Arrays.fill(dp[i], -1);
                dp[i][i] = 0;
            }
            int[] preSum = new int[K + 1];
            for (int i = 1; i <= K; i++) {
                preSum[i] = preSum[i - 1] + files[i - 1];
            }
            bw.write(dfs(preSum, files, dp, 0, K - 1) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int dfs(int[] preSum, int[] files, int[][] dp, int start, int end) {
        if (dp[start][end] != -1) {
            return dp[start][end];
        }

        if (end - start == 1) {
            dp[start][end] = files[start] + files[end];
            return dp[start][end];
        }

        int sum = 50000001;
        for (int i = start; i < end ; i++) {
            int temp = dfs(preSum, files, dp, start, i) + dfs(preSum, files, dp, i + 1, end);
            sum = Math.min(sum, temp);
        }
        dp[start][end] = sum + preSum[end + 1] - preSum[start];
        return dp[start][end];
    }
}
