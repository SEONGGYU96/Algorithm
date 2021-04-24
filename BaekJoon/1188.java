import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int answer = 0;

        if (N % M == 0) {
            answer = 0;
        } else {
            if (N > M) {
                N %= M;
            }
            answer = M;
            int gcd = gcd(N, M);
            int n = N / gcd;
            int m = M / gcd;
            int lcm = lcm(n, m);
            answer -= M / (lcm / n);
        }

        System.out.println(answer);
    }

    private static int gcd(int a, int b) {
        int A = Math.max(a, b);
        int B = Math.min(a, b);
        if (A % B == 0) {
            return B;
        }
        return gcd(B, A % B);
    }

    private static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}
