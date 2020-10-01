import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        System.out.print(solution(N, r, c));
    }

    private static int solution(int N, int r, int c) {
        int size = (int) Math.pow(2, N);
        int center = size / 2;
        int count = 0;

        for (int i = 0; i < 2; i ++) {
            int minR = center * i;
            int maxR = center * (i + 1);
            if (minR <= r && r < maxR) {
                for (int j = 0; j < 2; j++) {
                    int minC = center * j;
                    int maxC = center * (j + 1);
                    if (minC <= c && c < maxC) {
                        if (i == r && j == c) {
                            return count;
                        }
                        int newR = r - minR;
                        int newC = c - minC;
                        return count + solution(N - 1, newR, newC);
                    } else {
                        int newSize = (int) Math.pow(2, N - 1);
                        count += newSize * newSize;
                    }
                }
            } else {
                int newSize = (int) Math.pow(2, N - 1);
                count += newSize * newSize * 2;
            }
        }
        return count;
    }
}
