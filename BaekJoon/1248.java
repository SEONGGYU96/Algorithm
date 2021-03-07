import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int N;
    private static char[][] S;
    private static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        A = new int[N];
        S = new char[N][N];
        String str = br.readLine();

        for (int i = 0, head = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                S[i][j] = str.charAt(head++);
            }
        }

        findA(0);
    }

    private static boolean findA(int depth) {
        if (depth == N) {
            for (int element : A) {
                System.out.print(element + " ");
            }
            return true;
        }

        for (int i = -10; i <= 10; i++) {
            A[depth] = i;

            if (isPossible(depth)) {
                if (findA(depth + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isPossible(int until) {
        int sum = 0;

        for (int row = until; row >= 0; row--) {
            sum += A[row];

            if (S[row][until] == '+' && sum <= 0
                    || S[row][until] == '-' && sum >= 0
                    || S[row][until] == '0' && sum != 0) {
                return false;
            }
        }
        return true;

    }
}

