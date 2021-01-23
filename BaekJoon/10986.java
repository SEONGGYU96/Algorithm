import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[] sum = new long[N + 1];
        long[] count = new long[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            sum[i] = (sum[i - 1] + Integer.parseInt(st.nextToken())) % M;
            count[(int) sum[i]]++;
        }
        long answer = count[0];
        for (int i = 0; i < M; i++) {
            answer += count[i] * (count[i] - 1) / 2;
        }
        System.out.println(answer);
    }
}

