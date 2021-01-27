import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[][] pSum = new int[R + 1][C + 1];

        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; j++) {
                int current = Integer.parseInt(st.nextToken());
                pSum[i][j] = pSum[i][j - 1] + pSum[i - 1][j] - pSum[i - 1][j - 1] + current;
            }
        }

        for (int i = 0; i < Q; i++ ) {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            int sum = pSum[endX][endY] - pSum[startX - 1][endY] - pSum[endX][startY - 1] + pSum[startX - 1][startY - 1];
            int average = sum / ((endX - startX + 1) * (endY - startY + 1));

            bw.write(average + "\n");
        }
        bw.flush();
        bw.close();
    }
}

