import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] selects = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                selects[j] = Integer.parseInt(st.nextToken());
            }
            bw.write(solution(n, selects) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int solution(int n, int[] selects) {
        count = 0;
        boolean[] isVisited = new boolean[n + 1];
        boolean[] isFinished = new boolean[n + 1];
        Arrays.fill(isVisited, false);
        Arrays.fill(isFinished, false);

        for (int i = 1; i < selects.length; i++) {
            if (!isVisited[i]) {
                dfs(selects, isVisited, isFinished, i);
            }
        }
        return n - count;
    }

    private static void dfs(int[] selects, boolean[] isVisited, boolean[] isFinished, int i) {
        isVisited[i] = true;
        int next = selects[i];
        if (!isVisited[next]) {
            dfs(selects, isVisited, isFinished, next);
        } else {
            if (!isFinished[next]) {
                count++;
                for (int temp = next; temp != i; temp = selects[temp]) {
                    count++;
                }
            }
        }
        isFinished[i] = true;
    }
}
