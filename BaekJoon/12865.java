import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] stock = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                stock[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution(stock, K));
    }

    public static int solution(int[][] stock, int K) {
        int[][] dp = new int[stock.length + 1][K + 1];

        for (int itemIndex = 1; itemIndex <= stock.length; itemIndex++) {
            int stockWeight = stock[itemIndex - 1][0];
            int stockValue = stock[itemIndex - 1][1];
            for (int currentWeight = itemIndex; currentWeight <= K; currentWeight++) {
                if (currentWeight < stockWeight) {
                    dp[itemIndex][currentWeight] = dp[itemIndex - 1][currentWeight];
                } else {
                    dp[itemIndex][currentWeight] = Math.max(dp[itemIndex - 1][currentWeight], dp[itemIndex - 1][currentWeight - stockWeight] + stockValue);
                }
            }
        }
        return dp[stock.length][K];
    }
}

