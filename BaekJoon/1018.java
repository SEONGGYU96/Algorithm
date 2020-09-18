import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        scanner.nextLine();

        char[][] board = new char[N][];
        for (int i = 0; i < N; i++) {
            board[i] = scanner.nextLine().toCharArray();
        }

        System.out.println(solution(board));
    }

    private static int solution(char[][] board) {
        int min = 999;
        for (int startY = 0; startY < board.length - 7; startY++) {
            for (int startX = 0; startX < board[startY].length - 7; startX++) {
                int currentCount = getMinChangeCount(board, startX, startY);
                if (min > currentCount) {
                    min = currentCount;
                }
            }
        }

        return min;
    }

    private static int getMinChangeCount(char[][] board, int startX, int startY) {
        int count = 0;
        char previousColor = 0;
        for (int i = startY; i < startY + 8; i++) {
            for (int j = startX; j < startX + 8; j++) {
                char currentColor = board[i][j];
                if (i == 0 && j == 0) {
                    previousColor = currentColor;
                    continue;
                }

                if (previousColor == currentColor) {
                    count++;
                    currentColor = currentColor == 'B' ? 'W' : 'B';
                }

                previousColor = currentColor;
            }
            previousColor = previousColor == 'B' ? 'W' : 'B';
        }

        return Math.min(count, 64 - count);
    }
}
