import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] balloons = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            balloons[i] = Integer.parseInt(st.nextToken());
        }
        solution(N, balloons);
    }

    public static void solution(int N, int[] balloons) {
        boolean[] isVisited = new boolean[N];
        Arrays.fill(isVisited, false);
        int head = 0;
        int move;
        int count = 0;
        int direction = 1;

        for (int i = 0; i < N; i++) {
            while (count > 0) {
                head = tuningHead(N, head + direction);
                if (!isVisited[head]) {
                    count--;
                }
            }
            System.out.print(head + 1 + " ");
            isVisited[head] = true;
            move = balloons[head];
            count = Math.abs(move);
            direction = move >= 0 ? 1 : -1;
        }
    }

    private static int tuningHead(int N, int head) {
        boolean isPositive = head >= 0;
        head %= N;
        if (!isPositive) {
            head = N + head;
        }
        return head;
    }
}
