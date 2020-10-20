import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] daily = new int[N];
        int min = -1;
        int max = 0;
        for (int i = 0; i < N; i++) {
            daily[i] = Integer.parseInt(br.readLine());
            if (i == 0) {
                min = daily[i];
            } else {
                min = Math.max(min, daily[i]);
            }
            max += daily[i];
        }
        System.out.println(solution(M, daily, min, max));
    }

    public static int solution(int M, int[] daily, int min, int max) {
        int head = min;
        int tail = max;

        while (head <= tail) {
            int middle = (head + tail) / 2;
            int current = middle;
            int count = 1;

            for (int day : daily) {
                if (day > current) {
                    current = middle - day;
                    count++;
                } else {
                    current -= day;
                }
            }

            if (count > M) {
                head = middle + 1;
            } else {
                tail = middle - 1;
            }
        }
        return head;
    }
}

