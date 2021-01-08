import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        int answer = 0;

        for (int i = 0; i < N; i++) {
            int[] queenOfRow = new int[N];
            Arrays.fill(queenOfRow, -1);
            queenOfRow[0] = i;
            answer += backtracking(1, N, queenOfRow);
        }

        System.out.println(answer);
    }

    private static int backtracking(int row, int N, int[] queenOfRow) {
        if (row == N) {
            //print(queenOfRow);
            return 1;
        }

        int count = 0;

        for (int i = 0; i < N; i++) {
            if (isPossible(queenOfRow, row, i, N)) {
                queenOfRow[row] = i;
                count += backtracking(row + 1, N, queenOfRow);
            }
        }

        return count;
    }

    private static void print(int[] queenOfRow) {
        System.out.println();
        for (int row : queenOfRow) {
            for (int col = 0; col < queenOfRow.length; col++) {
                if (row == col) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }

    private static boolean isPossible(int[] queenOfRow, int row, int col, int N) {
        for (int i = 0; i < row; i++) {
            if (queenOfRow[i] == col) {
                return false;
            }
            if (Math.abs(queenOfRow[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }
}
