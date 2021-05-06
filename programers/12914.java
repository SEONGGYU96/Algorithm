import java.util.Arrays;

class Solution {

    private long[] dp;

    public long solution(int n) {
        dp = new long[n + 1];
        Arrays.fill(dp, -1);
        dp[n] = 1;

        return jump(0, n);
    }

    private long jump(int start, int end) {
        if (start > end) {
            return 0;
        }
        if (dp[start] != -1) {
            return dp[start];
        }
        dp[start] = (jump(start + 1, end) + jump(start + 2, end)) % 1234567;
        return dp[start];
    }
}
