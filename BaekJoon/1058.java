import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());
        final int INF = 60;

        int[][] weights = new int[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    weights[i][j] = 0;
                }
                weights[i][j] = s.charAt(j) == 'Y' ? 1 : INF;
            }
        }

        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (i == j || j == k || k == i) {
                        continue;
                    }
                    weights[i][j] = Math.min(weights[i][j], weights[i][k] + weights[k][j]);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            int count = 0;
            for (int j = 0; j < N; j++) {
                if (i != j && weights[i][j] <= 2) {
                    count++;
                }
            }
            answer = Math.max(answer, count);
        }
        System.out.println(answer);
    }
}
