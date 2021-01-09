import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int n = 9;
    private static final int ROW = 0;
    private static final int COL = 1;
    private static final int CELL = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] sudoku = new int[n][n];
        ArrayList<Point> blanks = new ArrayList<>();
        boolean[][][] duplicated = new boolean[3][n][n + 1];

        StringTokenizer st;
        for (int currentRow = 0; currentRow < n; currentRow++) {
            st = new StringTokenizer(br.readLine());
            for (int currentCol = 0; currentCol < n; currentCol++) {
                int value = Integer.parseInt(st.nextToken());
                sudoku[currentRow][currentCol] = value;

                if (value == 0) {
                    blanks.add(new Point(currentCol, currentRow));
                } else {
                    duplicated[ROW][currentRow][value] = true;
                    duplicated[COL][currentCol][value] = true;
                    duplicated[CELL][currentRow / 3 * 3 + currentCol / 3][value] = true;
                }
            }
        }

        backtracking(sudoku, duplicated, blanks, 0, blanks.size());

    }

    private static boolean backtracking(int[][] sudoku, boolean[][][] duplicated, ArrayList<Point> blanks, int depth, int end) {
        if (depth == end) {
            print(sudoku);
            return true;
        }

        Point current = blanks.get(depth);

        for (int i = 1; i <= n; i++) {
            int row = current.y;
            int col = current.x;
            if (isPossible(duplicated, current, i)) {
                sudoku[row][col] = i;
                duplicated[ROW][row][i] = true;
                duplicated[COL][col][i] = true;
                duplicated[CELL][row / 3 * 3 + col / 3][i] = true;
                if (backtracking(sudoku, duplicated, blanks, depth + 1, blanks.size())) {
                    return true;
                }
                duplicated[ROW][row][i] = false;
                duplicated[COL][col][i] = false;
                duplicated[CELL][row / 3 * 3 + col / 3][i] = false;
            }
        }
        return false;
    }

    private static void print(int[][] sudoku) {
        for (int[] row : sudoku) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }

    private static boolean isPossible(boolean[][][] duplicated, Point point, int value) {
        int row = point.y;
        int col = point.x;

        return !duplicated[ROW][row][value]
                && !duplicated[COL][col][value]
                && !duplicated[CELL][row / 3 * 3 + col / 3][value];
    }
}




