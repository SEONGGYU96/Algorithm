import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] sequence = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }
        int left = 0;
        int right = 0;
        int sum = 0;
        int minRange = N + 1;
        while (true) {
            if (sum >= S) {
                minRange = Math.min(minRange, right - left);
                sum -= sequence[left++];
            } else if (right == N) {
                break;
            } else {
                sum += sequence[right++];
            }
        }
        System.out.println(minRange == N + 1 ? 0 : minRange);
    }
}
