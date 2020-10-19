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
        int[] lessons = new int[N];
        st = new StringTokenizer(br.readLine());
        int max = -1;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            lessons[i] = Integer.parseInt(st.nextToken());
            if (i == 0) {
                max = lessons[i];
            } else {
                max = Math.max(max, lessons[i]);
            }
            sum += lessons[i];
        }
        System.out.println(solution(M, lessons, max, sum));
    }

    public static int solution(int M, int[] lessons, int head, int tail) {
        while (head <= tail) {
            int middle = (tail + head) / 2;
            int sum = 0;
            int count = 0;
            for (int lesson : lessons) {
                if (sum + lesson > middle) {
                    count++;
                    sum = 0;
                }
                sum += lesson;
            }
            if (sum != 0) {
                count++;
            }
            if (count <= M) {
                tail = middle - 1;
            } else {
                head = middle + 1;
            }
        }

        return head;
    }
}

