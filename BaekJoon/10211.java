import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] sum = new int[N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
            }
            bw.write(solution(sum) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int solution(int[] sum) {
        int max = Integer.MIN_VALUE;
        for (int head = 1; head < sum.length; head++) {
            for (int tail = head; tail < sum.length; tail++) {
                max = Math.max(max, sum[tail] - sum[head - 1]);
            }
        }
        return max;
    }
}
