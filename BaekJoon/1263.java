import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] workInfo = new int[N][2];
        int answer = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                workInfo[i][j] = Integer.parseInt(st.nextToken());
            }
            answer = Math.max(answer, workInfo[i][1]);
        }

        Arrays.sort(workInfo, Comparator.comparingInt(o -> o[1]));

        for (int i = N - 1; i >= 0; i--) {
            answer = Math.min(answer - workInfo[i][0], workInfo[i][1] - workInfo[i][0]);
        }

        System.out.println(answer >= 0 ? answer : -1);
    }
}
