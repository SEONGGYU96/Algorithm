import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        System.out.println(solution(N));
    }

    private static int solution(int N) {
        int answer = 0;

        for (int i = 1; i < N; i++) {
            int current = i;
            int sum = current;
            do {
                sum += current % 10;
                current /= 10;
            } while (current > 0);
            if (sum == N) {
                answer = i;
                break;
            }
        }

        return answer;
    }
}
