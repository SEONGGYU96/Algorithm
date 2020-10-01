import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] matrix = new char[N][N];
        solution(matrix, 0, 0, N);
        for (char[] row : matrix) {
            StringBuilder line = new StringBuilder();
            for (char col : row) {
                if (col != '*') {
                    line.append(' ');
                } else {
                    line.append(col);
                }
            }
            System.out.println(line.toString());
        }
    }

    private static void solution(char[][] matrix, int startRow, int startCol, int size) {
        int newSize = size / 3;
        for (int i = 0; i < 3; i++) {
            int newStartRow = startRow + newSize * i;
            for (int j = 0; j < 3; j++) {
                int newStartCol = startCol + newSize * j;

                if (i != 1 || j != 1) {
                    if (newSize == 1) {
                        matrix[newStartRow][newStartCol] = '*';
                    } else {
                        solution(matrix, newStartRow, newStartCol, newSize);
                    }
                }
            }
        }
    }
}
