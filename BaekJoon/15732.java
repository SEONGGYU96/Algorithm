import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int[][] rules = new int[K][3];
        int min = 0;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                rules[i][j] = Integer.parseInt(st.nextToken());
            }
            if (i == 0) {
                min = rules[i][0];
            } else {
                min = Math.min(min, rules[i][0]);
            }
        }

        System.out.println(solution(N, D, rules, min));
    }

    public static int solution(int N, int D, int[][] rules, int min) {
        int head = min;
        int tail = N;

        while (head <= tail) {
            int middle = (head + tail) / 2;
            long count = 0;
            boolean isSmall = true;

            for (int[] rule : rules) {
                int start = rule[0];
                int end = rule[1];
                int unit = rule[2];
                if (middle < start) {
                    continue;
                }
                count += (Math.min(middle, end) - start) / unit + 1;
                if (count >= D) {
                    isSmall = false;
                    break;
                }
            }
            if (isSmall) {
                head = middle + 1;
            } else {
                tail = middle - 1;
            }
        }
        return head;
    }
}
