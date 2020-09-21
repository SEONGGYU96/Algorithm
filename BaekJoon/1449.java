import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int L = scanner.nextInt();
        scanner.nextLine();

        int[] point = new int[N];

        for (int i = 0; i < N; i++) {
            point[i] = scanner.nextInt();
        }

        System.out.println(solution(point, L));
    }

    private static int solution(int[] point, int L) {
        int answer = 0;
        
        Arrays.sort(point);

        int start = -1;

        for (int p : point) {
            if (start == -1 || p - start > L - 1) {
                start = p;
                answer++;
            }
        }

        return answer;
    }
}
