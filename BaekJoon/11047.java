import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int K = scanner.nextInt();
        scanner.nextLine();

        int[] values = new int[N];

        for (int i = 0; i < N; i++) {
            values[i] = Integer.parseInt(scanner.nextLine());
        }

        System.out.println(solution(values, K));
    }

    private static int solution(int[] values, int K) {
        int answer = 0;

        for (int i = values.length - 1; i >= 0; i--) {
            if (values[i] > K) {
                continue;
            }

            int count = K / values[i];
            answer += count;
            K -= count * values[i];

            if (K == 0) {
                break;
            }
        }

        return answer;
    }
}
