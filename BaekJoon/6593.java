import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            if (L == 0 && R == 0 && C == 0) {
                break;
            }

            char[][][] building = new char[L][R][C];
            Position start = null;

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String str = br.readLine();
                    if (start == null && str.contains("S")) {
                        start = new Position(i, j, str.indexOf('S'));
                    }
                    building[i][j] = str.toCharArray();
                }
                br.readLine();
            }
            int answer = solution(L, R, C, start, building);
            if (answer == -1) {
                bw.write("Trapped!\n");
            } else {
                bw.write("Escaped in " + answer + " minute(s).\n");
            }
        }
        bw.flush();
        bw.close();
    }

    private static int solution(int L, int R, int C, Position start, char[][][] building) {
        int answer = -1;
        boolean[][][] isVisited = new boolean[L][R][C];
        Queue<Position> queue = new LinkedList<>();

        queue.add(start);
        isVisited[start.floor][start.row][start.col] = true;

        while (!queue.isEmpty()) {
            answer++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Position current = queue.poll();
                int floor = current.floor;
                int row = current.row;
                int col = current.col;
                if (building[floor][row][col] == 'E') {
                    return answer;
                }
                if (row + 1 < R && building[floor][row + 1][col] != '#' && !isVisited[floor][row + 1][col]) {
                    queue.add(new Position(floor, row + 1, col));
                    isVisited[floor][row + 1][col] = true;
                }
                if (col + 1 < C && building[floor][row][col + 1] != '#' && !isVisited[floor][row][col + 1]) {
                    queue.add(new Position(floor, row, col + 1));
                    isVisited[floor][row][col + 1] = true;
                }
                if (row - 1 >= 0 && building[floor][row - 1][col] != '#' && !isVisited[floor][row - 1][col]) {
                    queue.add(new Position(floor, row - 1, col));
                    isVisited[floor][row - 1][col] = true;
                }
                if (col - 1 >= 0 && building[floor][row][col - 1] != '#' && !isVisited[floor][row][col - 1]) {
                    queue.add(new Position(floor, row, col - 1));
                    isVisited[floor][row][col - 1] = true;
                }
                if (floor + 1 < L && building[floor + 1][row][col] != '#' && !isVisited[floor + 1][row][col]) {
                    queue.add(new Position(floor + 1, row, col));
                    isVisited[floor + 1][row][col] = true;
                }
                if (floor - 1 >= 0 && building[floor - 1][row][col] != '#' && !isVisited[floor - 1][row][col]) {
                    queue.add(new Position(floor - 1, row, col));
                    isVisited[floor - 1][row][col] = true;
                }
            }
        }
        return -1;
    }

    static class Position {
        int floor;
        int row;
        int col;

        public Position(int floor, int row, int col) {
            this.floor = floor;
            this.row = row;
            this.col = col;
        }
        
        @Override
        public String toString() {
            return "floor : " + floor + " row : " + row + " col : " + col;
        }
    }
}
