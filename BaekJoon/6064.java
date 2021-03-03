import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        final int M = 0;
        final int N = 1;
        final int x = 2;
        final int y = 3;

        while (T > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] params = new int[4];
            for (int i = 0; i < params.length; i++) {
                params[i] = Integer.parseInt(st.nextToken());
            }

            int maxYear = lcm(params[M], params[N]);

            while (params[x] <= maxYear && (params[x] - 1) % params[N] + 1 != params[y]) {
                params[x] += params[M];
            }

            answer.append(params[x] > maxYear ? -1 : params[x]).append("\n");
            T--;
        }

        System.out.println(answer);
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}

