import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        System.out.println(solution(A, B, C));
    }

    private static int solution(int A, int B, int C) {
        if (B == 0) {
            return 1;
        } else if (B == 1) {
            return A % C;
        }
        if (B % 2 == 0) {
            long result = solution(A, B / 2, C);
            return (int) ((result * result) % (long) C);
        } else {
            long result = solution(A, B - 1, C);
            return (int) ((result * A) % (long) C);
        }
    }
}
