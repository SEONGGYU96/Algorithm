import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] sequence = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        int head = 0;
        int tail = 0;
        int sum = 0;
        int answer = 0;
        while (true) {
            if (sum >= M) {
                sum -= sequence[head++];
            } else if (tail == N) {
                break;
            } else {
                sum += sequence[tail++];
            }
            if (sum == M) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}

