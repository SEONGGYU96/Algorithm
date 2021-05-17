import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        int[] dp = new int[12];
        dp[1] = 1;
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            bw.write(getWays(dp, n) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int getWays(int[] dp, int n) {
        if (n == 0) {
            return 1;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        int ways = 0;
        for (int i = 1; i <= Math.min(n, 3); i++) {
            ways += getWays(dp, n - i);
        }
        dp[n] = ways;
        return dp[n];
    }
}