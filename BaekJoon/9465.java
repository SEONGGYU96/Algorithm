import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] answer = new int[T];
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[][] stickers = new int[2][N];
            for (int j = 0; j < 2; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < N; k++) {
                    stickers[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            answer[i] = solution(stickers);
        }

        for (int _answer : answer) {
            System.out.println(_answer);
        }
    }

    private static int solution(int[][] stickers) {
        int numberOfCol = stickers[0].length;
        int[][] dp = new int[numberOfCol][3];
        for (int j = 0; j < numberOfCol; j++) {
            Arrays.fill(dp[j], -1);
        }

        return getSticker(dp, stickers, 0, 0);
    }

    private static int getSticker(int[][] dp, int[][] stickers, int col, int status) {
        int numberOfCol = dp.length;
        if (col == numberOfCol) {
            return 0;
        }
        if (dp[col][status] != -1) {
            return dp[col][status];
        }

        int result = getSticker(dp, stickers, col + 1, 0);
        if (status != 1) {
            result = Math.max(result, getSticker(dp, stickers, col + 1, 1) + stickers[0][col]);
        }
        if (status != 2) {
            result = Math.max(result, getSticker(dp, stickers, col + 1, 2) + stickers[1][col]);
        }

        dp[col][status] = result;
        return result;
    }
}
