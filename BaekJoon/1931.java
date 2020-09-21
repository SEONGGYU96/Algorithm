import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] time = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                time[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(time));
    }

    private static int solution(int[][] times) {
        int answer = 0;
        int previousEnd = 0;

        Arrays.sort(times, (o1, o2) -> {
            if (o1[1] > o2[1]) {
                return 1;
            } else if (o1[1] < o2[1]) {
                return -1;
            } else {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        for (int[] time : times) {
            if (time[0] >= previousEnd) {
                answer++;
                previousEnd = time[1];
            }
        }

        return answer;
    }
}
