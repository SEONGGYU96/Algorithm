import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] speed = new int[N];
        int[] gapSum = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(st.nextToken());
            speed[i] = value;
            if (i >= 1) {
                gapSum[i] = gapSum[i - 1] + Math.abs(speed[i] - speed[i - 1]);
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            bw.write(solution(gapSum, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())) + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static int solution(int[] sum, int start, int end) {
        return sum[end - 1] - sum[start - 1];
    }
}

