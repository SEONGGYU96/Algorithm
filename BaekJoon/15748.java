import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long L = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        long rF = Integer.parseInt(st.nextToken());
        long rB = Integer.parseInt(st.nextToken());

        long[][] restStops = new long[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < restStops[i].length; j++) {
                restStops[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(rF, rB, restStops));
    }

    private static long solution(long rF, long rB, long[][] restStops) {
        long startPosition = 0;
        long answer = 0;

        Arrays.sort(restStops, ((o1, o2) -> (int) (o2[1] - o1[1])));

        for (long[] restStop : restStops) {
            long stopPosition = restStop[0] - startPosition;
            if (stopPosition <= 0) {
                continue;
            }
            long remainTime = stopPosition * (rF - rB);
            answer += remainTime * restStop[1];
            startPosition = restStop[0];
        }
        return answer;
    }
}
