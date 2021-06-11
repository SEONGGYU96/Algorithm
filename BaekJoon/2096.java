import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][][] window = new int[2][2][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                window[0][0][j] = window[0][1][j] = Integer.parseInt(st.nextToken());
                window[0][1][j] += Math.max(window[1][1][1], (j == 1) ? Math.max(window[1][1][0], window[1][1][2]) : window[1][1][j]);
                window[0][0][j] += Math.min(window[1][0][1], (j == 1) ? Math.min(window[1][0][0], window[1][0][2]) : window[1][0][j]);
            }
            System.arraycopy(window[0][0], 0, window[1][0], 0, 3);
            System.arraycopy(window[0][1], 0, window[1][1], 0, 3);
        }
        Arrays.sort(window[1][0]);
        Arrays.sort(window[1][1]);
        System.out.println(window[1][1][2] + " " + window[1][0][0]);
    }
}
