import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());

        int[][] tasks = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < tasks[i].length; j++) {
                tasks[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(tasks));
    }

    private static int solution(int[][] tasks) {
        Arrays.sort(tasks, ((o1, o2) -> o2[1] - o1[1]));
        int[] plan = new int[Arrays.stream(tasks).max(Comparator.comparingInt(o -> o[0])).get()[0] + 1];
        Arrays.fill(plan, 0);

        for (int[] task : tasks) {
            if (plan[task[0]] == 0) {
                plan[task[0]] = task[1];
            } else {
                for (int i = task[0]; i >= 1; i--) {
                    if (plan[i] == 0) {
                        plan[i] = task[1];
                        break;
                    }
                }
            }
        }

        return Arrays.stream(plan).sum();
    }
}
