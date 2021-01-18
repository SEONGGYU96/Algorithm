import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] maze = new char[N][M];
        State start = null;

        for (int row = 0; row < N; row++) {
            String rowString = br.readLine();
            for (int col = 0; col < M; col++) {
                maze[row][col] = rowString.charAt(col);
                if (maze[row][col] == '0') {
                    start = new State(row, col);
                }
            }
        }

        System.out.println(solution(maze, N, M, start));
    }

    private static int solution(char[][] maze, int N, int M, State start) {
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};
        boolean[][][] isVisited = new boolean[N][M][1 << 6];
        Queue<State> queue = new LinkedList<>();

        queue.add(start);
        isVisited[start.row][start.col][0] = true;

        int answer = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                State current = queue.poll();
                int row = current.row;
                int col = current.col;
                int keys = current.keys;

                //print(maze, row, col, keys);

                if (maze[row][col] == '1') {
                    return answer;
                }

                for (int j = 0; j < rowOffset.length; j++) {
                    int nextRow = row + rowOffset[j];
                    int nextCol = col + colOffset[j];
                    int nextKeys = keys;

                    if (nextRow >= N || nextRow < 0 || nextCol >= M || nextCol < 0 || isVisited[nextRow][nextCol][nextKeys]) {
                        continue;
                    }

                    char nextMaze = maze[nextRow][nextCol];

                    if (nextMaze == '#') {
                        continue;
                    } else if (nextMaze >= 65 && nextMaze <= 70) {
                        if ((keys & (1 << (nextMaze - 65))) == 0) {
                            continue;
                        }
                    } else if (nextMaze >= 97 && nextMaze <= 102) {
                        nextKeys |= (1 << (nextMaze - 97));
                    }

                    queue.add(new State(nextRow, nextCol, nextKeys));
                    isVisited[nextRow][nextCol][nextKeys] = true;
                }
            }
            answer++;
        }
        return -1;
    }


    private static void print(char[][] maze, int row, int col, int keys) {
        System.out.print("keys : ");
        for (int i = 0; i < 6; i++) {
            if ((keys & (1 << i)) == 0) {
                continue;
            }
            System.out.print(((char) (i + 97)) + ", ");
        }
        System.out.println();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (i == row && j == col) {
                    System.out.print("&");
                } else {
                    System.out.print(maze[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    static class State {
        int row;
        int col;
        int keys;

        public State(int row, int col) {
            this(row, col, 0);
        }

        public State(int row, int col, int keys) {
            this.row = row;
            this.col = col;
            this.keys = keys;
        }
    }
}

