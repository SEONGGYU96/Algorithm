import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        char[][] video = new char[N][];

        for (int i = 0; i < N; i++) {
            video[i] = br.readLine().toCharArray();
        }

        List<Character> answer = new ArrayList<>();
        solution(video, answer, N, 0, 0);
        for (char current : answer) {
            System.out.print(current);
        }
    }

    private static void solution(char[][] paper, List<Character> answer, int size, int startRow, int startCol) {
        char temp = 'n';
        boolean difference = false;
        for (int i = startRow; i < startRow + size; i++) {
            for (int j = startCol; j < startCol + size; j++) {
                if (temp == 'n') {
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
            answer.add('(');
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    int newSize = size / 2;
                    solution(paper, answer, newSize, startRow + newSize * i, startCol + newSize * j);
                }
            }
            answer.add(')');
        } else {
            answer.add(temp);
        }
    }
}
