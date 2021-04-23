import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] lights = new String[N];

        for (int i = 0; i < N; i++) {
            lights[i] = br.readLine();
        }

        int K = Integer.parseInt(br.readLine());
        int answer = 0;

        for (int i = 0; i < N; i++) {
            int off = 0;
            for (int j = 0; j < M; j++) {
                if (lights[i].charAt(j) == '0') {
                    off++;
                }
            }
            int sum = 0;
            if (off <= K && off % 2 == K % 2) {
                for (int j = 0; j < N; j++) {
                    if (lights[i].equals(lights[j])) {
                        sum++;
                    }
                }
            }
            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }
}
