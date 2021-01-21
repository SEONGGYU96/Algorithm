import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[][] sum = new int[3][N + 1];

        for (int i = 1; i <= N; i++) {
            int type = Integer.parseInt(br.readLine());
            for (int j = 0; j < 3; j++) {
                sum[j][i] = sum[j][i - 1];
                if (j == type - 1) {
                    sum[j][i]++;
                }
            }
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            for (int j = 0; j < 3; j++) {
                bw.write(sum[j][end] - sum[j][start - 1] + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }
}

