import java.util.*;

class Solution {
    
    private static final int MAX = 2000000000;

    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        ArrayList<Integer>[] connections = new ArrayList[n + 1];
        
        for (int i = 1; i <= n; i++) {
            connections[i] = new ArrayList<>();
        }

        for (int[] edges : edge_list) {
            int start = edges[0];
            int end = edges[1];
            connections[start].add(end);
            connections[end].add(start);
        }

        int[][] dp = new int[n][n + 1];
        for (int [] row : dp) {
            Arrays.fill(row, MAX);
        }
        dp[0][gps_log[0]] = 0;

        for (int i = 1; i < k; i++) {
            for (int pos = 1; pos <= n; pos++) {
                if (dp[pos - 1][pos] == MAX) {
                    continue;
                }
                for (int nextPos : connections[pos]) {
                    int count = 0;
                    if (gps_log[i] != nextPos) {
                        count++;
                    }
                    dp[i][nextPos] = Math.min(dp[i][nextPos], dp[i - 1][pos] + count);
                }
            }
        }

        return dp[k - 1][gps_log[k - 1]] < MAX ? dp[k - 1][gps_log[k - 1]] : -1;
    }
}
