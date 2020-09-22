import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[][] classes = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < classes[i].length; j++) {
                classes[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(classes));
    }

    private static int solution(int[][] classes) {
        PriorityQueue<Integer> waiting = new PriorityQueue<>();

        Arrays.sort(classes, ((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            } else {
                return o1[0] - o2[0];
            }
        }));

        for (int[] time : classes) {
            int startTime = time[0];
            int endTime = time[1];

            if (!waiting.isEmpty() && waiting.peek() <= startTime) {
                waiting.poll();
            }
            waiting.add(endTime);
        }

        return waiting.size();
    }
}
