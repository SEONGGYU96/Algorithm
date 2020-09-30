import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] paper = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] answer = {0, 0, 0};
        solution(paper, answer, N, 0, 0);
        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
    }

    private static void solution(int[][] paper, int[] answer, int size, int startRow, int startCol) {
        int temp = -2;
        boolean difference = false;
        for (int i = startRow; i < startRow + size; i++) {
            for (int j = startCol; j < startCol + size; j++) {
                if (temp == -2) {
                    temp = paper[i][j];
                    continue;
                }

                if (temp != paper[i][j]) {
                    difference = true;
                    break;
                }
            }
            if (difference) {
                break;
            }
        }

        if (difference) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int newSize = size / 3;
                    solution(paper, answer, newSize, startRow + newSize * i, startCol + newSize * j);
                }
            }
        } else {
            switch (temp) {
                case -1 :
                    answer[0]++;
                    break;
                case 0 :
                    answer[1]++;
                    break;
                case 1 :
                    answer[2]++;
                    break;
            }
        }
    }
}
