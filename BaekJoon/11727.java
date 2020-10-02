import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] answer = new int[N + 1];
        Arrays.fill(answer, -1);
        answer[0] = 0;
        answer[1] = 1;
        if (N >= 2) {
            answer[2] = 3;
        }
        System.out.println(solution(answer, N));
    }

    private static int solution(int[] answer, int N) {
        if (answer[N] != -1) {
            return answer[N];
        }
        answer[N] = (2 * solution(answer, N - 2) + solution(answer, N - 1)) % 10007;
        return answer[N];
    }
}
