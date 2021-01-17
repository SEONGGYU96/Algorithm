import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(solution(N));
    }

    private static long solution(int N) {
        long[][][] dp = new long[10][N][1 << 10];

        long answer = 0;

        for (int i = 1; i <= 9 ; i++) {
            answer = (answer + recursive(N, dp, i, 1, 1 << i)) % 1000000000;
        }
        return answer;
    }

    private static long recursive(int N, long[][][] dp, int lastNumber, int depth, int using) {
        if (depth == N) {
            if (using == ((1 << 10) - 1)) {
                return 1;
            } else {
                return 0;
            }
        }

        if (dp[lastNumber][depth][using] != 0) {
            return dp[lastNumber][depth][using];
        }



        if (lastNumber == 9) {
            dp[lastNumber][depth][using] = recursive(N, dp, 8, depth + 1, using | 1 << 8);
        } else if (lastNumber == 0) {
            dp[lastNumber][depth][using] = recursive(N, dp, 1, depth + 1, using | 1 << 1);
        } else {
            dp[lastNumber][depth][using] = (recursive(N, dp, lastNumber + 1, depth + 1, using | 1 << (lastNumber + 1)) + recursive(N, dp, lastNumber - 1, depth + 1, using | 1 << (lastNumber - 1))) % 1000000000;

        }

        return dp[lastNumber][depth][using];
    }
}

