import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] board = new char[R][];
        boolean[] isVisited = new boolean[26];
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }
        System.out.println(backtracking(board, isVisited, rowOffset, colOffset, 1, 0, 0));
    }

    private static int backtracking(char[][] board, boolean[] isVisited, int[] rowOffset, int[] colOffset, int depth, int row, int col) {
        char current = board[row][col];
        if (isVisited[current - 65]) {
            return depth - 1;
        }
        int max = depth;
        isVisited[current - 65] = true;

        for (int i = 0; i < rowOffset.length; i++) {
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];
            if (newRow < 0 || newRow >= board.length || newCol < 0 || newCol >= board[row].length) {
                continue;
            }
            max = Math.max(max, backtracking(board, isVisited, rowOffset, colOffset, depth + 1, newRow, newCol));
        }

        isVisited[current - 65] = false;
        return max;
    }
}
