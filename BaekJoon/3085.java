import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();
        char[][] board = new char[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = scanner.nextLine().toCharArray();
        }

        System.out.println(solution(board));
    }

    private static int solution(char[][] board) {
        int max = maxInBoard(board);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char currentColor = board[i][j];
                if (i + 1 < board.length && board[i + 1][j] != currentColor) {
                    board[i][j] = board[i + 1][j];
                    board[i + 1][j] = currentColor;
                    int currentMax = maxInBoard(board);
                    if (max < currentMax) {
                        max = currentMax;
                    }
                    board[i + 1][j] = board[i][j];
                    board[i][j] = currentColor;
                }

                if (j + 1 < board[i].length && board[i][j + 1] != currentColor) {
                    board[i][j] = board[i][j + 1];
                    board[i][j + 1] = currentColor;
                    int currentMax = maxInBoard(board);
                    if (max < currentMax) {
                        max = currentMax;
                    }
                    board[i][j + 1] = board[i][j];
                    board[i][j] = currentColor;
                }
            }
        }

        return max;
    }

    private static int maxInBoard(char[][] board) {
        int max = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char currentColor = board[i][j];
                int xHead = i;
                int yHead = j;

                int xCount = 1;
                while (++xHead < board.length && board[xHead][j] == currentColor) {
                    xCount++;
                }

                if (max < xCount) {
                    max = xCount;
                }

                int yCount = 1;
                while (++yHead < board[i].length && board[i][yHead] == currentColor) {
                    yCount++;
                }

                if (max < yCount) {
                    max = yCount;
                }
            }
        }
        return max;
    }
}
