import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final int IMPOSSIBLE = 1000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] cost = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int isTurnOn = 0;
        int count = 0;

        String state = br.readLine();
        for (int i = 0; i < N; i++) {
            if (state.charAt(i) == 'Y') {
                isTurnOn |= (1 << i);
                count++;
            }
        }

        int minimum = Integer.parseInt(br.readLine());

        System.out.println(solution(N, cost, minimum, isTurnOn, count));

    }

    private static int solution(int N, int[][] cost, int minimum, int isTurnOn, int count) {

        int[] dp = new int[1 << N];
        Arrays.fill(dp, -1);
        
        int answer = turnOn(dp, cost, N, minimum, isTurnOn, count);

        return answer == IMPOSSIBLE ? -1 : answer;
    }

    private static int turnOn(int[] dp, int[][] cost, int N, int minimum, int isTurnOn, int count) {
        if (count >= minimum) {
             return 0;
        }

        if (dp[isTurnOn] != -1) {
            return dp[isTurnOn];
        }

        int sum = IMPOSSIBLE;

        for (int i = 0; i < N; i++) {
            if ((isTurnOn & 1 << i) == 0) {
                continue;
            }
            for (int j = 0; j < N; j++){
                if ((isTurnOn & 1 << j) == 0) {
                    sum = Math.min(sum, turnOn(dp, cost, N, minimum, isTurnOn | (1 << j), count + 1) + cost[i][j]);
                }
            }
        }
        dp[isTurnOn] = sum;
        return sum;
    }
}

