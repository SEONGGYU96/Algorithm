import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] maze = new char[N][M];

        for (int i = 0; i < N; i++) {
            maze[i] = br.readLine().toCharArray();
        }
        System.out.println(solution(N, M, maze));
    }

    private static int solution(int N, int M, char[][] maze) {
        int answer = 0;
        boolean[][] isVisited = new boolean[N][M];
        Queue<Point> queue = new LinkedList<>();

        queue.add(new Point(0, 0));
        isVisited[0][0] = true;

        while (!queue.isEmpty()) {
            answer++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point current = queue.poll();
                int row = current.x;
                int col = current.y;
                if (row == N - 1 && col == M - 1) {
                    return answer;
                }
                if (row + 1 < N && maze[row + 1][col] == '1' && !isVisited[row + 1][col]) {
                    queue.add(new Point(row + 1, col));
                    isVisited[row + 1][col] = true;
                }
                if (col + 1 < M && maze[row][col + 1] == '1' && !isVisited[row][col + 1]) {
                    queue.add(new Point(row, col + 1));
                    isVisited[row][col + 1] = true;
                }
                if (row - 1 >= 0 && maze[row - 1][col] == '1' && !isVisited[row - 1][col]) {
                    queue.add(new Point(row - 1, col));
                    isVisited[row - 1][col] = true;
                }
                if (col - 1 >= 0 && maze[row][col - 1] == '1' && !isVisited[row][col - 1]) {
                    queue.add(new Point(row, col - 1));
                    isVisited[row][col - 1] = true;
                }
            }
        }
        return -1;
    }
}
