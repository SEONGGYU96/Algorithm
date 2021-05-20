import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] schedules = new int[N][2];
        int[] dp = new int[N];
        Arrays.fill(dp, -1);
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                schedules[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(getMaxValue(schedules, dp, 0));
    }

    private static int getMaxValue(int[][] schedules, int[] dp, int depth) {
        if (depth >= schedules.length) {
            return 0;
        }
        if (dp[depth] != -1) {
            return dp[depth];
        }

        dp[depth] = getMaxValue(schedules, dp, depth + 1);

        if (schedules[depth][0] + depth <= schedules.length) {
            dp[depth] = Math.max(dp[depth],
                    schedules[depth][1] + getMaxValue(schedules, dp, depth + schedules[depth][0]));
        }
        return dp[depth];
    }
}
