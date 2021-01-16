import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int IMPOSSIBLE = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] weights = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                weights[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(N, weights));

    }

    private static int solution(int N, int[][] weights) {

        int[][] dp = new int[N][1 << N];

        return move(dp, weights, N, 0, 1);
    }

    private static int move(int[][] dp, int[][] weights, int N, int current, int isVisited) {
        if (dp[current][isVisited] != 0) {
            return dp[current][isVisited];
        }

        if (isVisited == (1 << N)  - 1) {
            if (weights[current][0] != 0) {
                return weights[current][0];
            } else {
                return IMPOSSIBLE;
            }
        }

        int sum = IMPOSSIBLE;

        for (int i = 0; i < N; i++) {
            if (weights[current][i] != 0 && (isVisited & 1 << i) == 0) {
                sum = Math.min(sum, move(dp, weights, N, i, isVisited | (1 << i)) + weights[current][i]);
            }
        }
        dp[current][isVisited] = sum;
        return sum;
    }
}

